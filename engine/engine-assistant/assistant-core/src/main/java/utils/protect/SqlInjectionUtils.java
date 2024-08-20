package utils.protect;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import json.gson.GsonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.dromara.hutool.core.net.url.UrlDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Lớp SqlInjectionUtils cung cấp các phương thức để phát hiện và ngăn chặn các cuộc tấn công SQL Injection
 * thông qua việc kiểm tra các chuỗi đầu vào.
 */
public class SqlInjectionUtils {

    // Logger để ghi lại thông tin và lỗi liên quan đến kiểm tra SQL Injection.
    private static final Logger log = LoggerFactory.getLogger(SqlInjectionUtils.class);

    // Biểu thức chính quy dùng để phát hiện các từ khóa và mẫu cú pháp có thể liên quan đến SQL Injection.
    private static final String SQL_REGEX = "\\b(and|or)\\b.{1,6}?(=|>|<|\\bin\\b|\\blike\\b)|\\/\\*.+?\\*\\/|<\\s*script\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)";

    // Pattern được biên dịch từ biểu thức chính quy để sử dụng trong việc so khớp chuỗi.
    private static final Pattern SQL_PATTERN = Pattern.compile(SQL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * Constructor mặc định.
     */
    public SqlInjectionUtils() {
    }

    /**
     * Kiểm tra xem chuỗi đầu vào có khớp với các từ khóa hoặc mẫu cú pháp có thể gây ra SQL Injection không.
     *
     * @param lowerValue Chuỗi đã được chuyển sang chữ thường để kiểm tra.
     * @param param Chuỗi đầu vào cần kiểm tra.
     * @return True nếu phát hiện thấy từ khóa liên quan đến SQL Injection, ngược lại trả về False.
     */
    private static boolean matching(String lowerValue, String param) {
        if (SQL_PATTERN.matcher(param).find()) {
            log.error("[Herodotus] |- Tham số chứa từ khóa {} không cho phép SQL!", lowerValue);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Chuyển đổi đối tượng thành chuỗi và chuyển đổi chuỗi đó thành chữ thường.
     *
     * @param obj Đối tượng cần chuyển đổi.
     * @return Chuỗi chữ thường của đối tượng.
     */
    private static String toLowerCase(Object obj) {
        return Optional.ofNullable(obj).map(Object::toString).map(String::toLowerCase).orElse("");
    }

    /**
     * Kiểm tra một giá trị đầu vào xem có chứa từ khóa liên quan đến SQL Injection không.
     *
     * @param value Giá trị cần kiểm tra.
     * @return True nếu phát hiện thấy từ khóa liên quan đến SQL Injection, ngược lại trả về False.
     */
    private static boolean checking(Object value) {
        String lowerValue = toLowerCase(value);
        return matching(lowerValue, lowerValue);
    }

    /**
     * Kiểm tra một chuỗi URL được mã hóa để phát hiện SQL Injection trong các yêu cầu GET.
     *
     * @param value Chuỗi URL cần kiểm tra.
     * @return True nếu phát hiện thấy từ khóa liên quan đến SQL Injection, ngược lại trả về False.
     */
    public static boolean checkForGet(String value) {
        String lowerValue = UrlDecoder.decode(value, StandardCharsets.UTF_8).toLowerCase();
        return Stream.of(lowerValue.split("\\&"))
                .map(kp -> kp.substring(kp.indexOf("=") + 1))
                .parallel()
                .anyMatch(param -> matching(lowerValue, param));
    }

    /**
     * Kiểm tra một chuỗi JSON để phát hiện SQL Injection trong các yêu cầu POST.
     *
     * @param value Chuỗi JSON cần kiểm tra.
     * @return True nếu phát hiện thấy từ khóa liên quan đến SQL Injection, ngược lại trả về False.
     */
    public static boolean checkForPost(String value) {
        List<JsonElement> result = new ArrayList<>();
        JsonElement jsonElement = GsonUtils.toJsonElement(value);
        iterator(jsonElement, result);
        return CollectionUtils.isNotEmpty(result);
    }

    /**
     * Duyệt qua cấu trúc JSON để kiểm tra từng phần tử và phát hiện SQL Injection.
     *
     * @param jsonElement Phần tử JSON cần kiểm tra.
     * @param result Danh sách chứa các phần tử JSON phát hiện có SQL Injection.
     */
    private static void iterator(JsonElement jsonElement, List<JsonElement> result) {
        if (!jsonElement.isJsonNull()) {
            if (jsonElement.isJsonPrimitive()) {
                boolean hasInjection = checking(jsonElement.toString());
                if (hasInjection) {
                    result.add(jsonElement);
                }

            } else {
                Iterator var3;
                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();
                    if (ObjectUtils.isNotEmpty(jsonArray)) {
                        var3 = jsonArray.iterator();

                        while(var3.hasNext()) {
                            JsonElement je = (JsonElement)var3.next();
                            iterator(je, result);
                        }
                    }

                } else {
                    if (jsonElement.isJsonObject()) {
                        Set<Map.Entry<String, JsonElement>> es = jsonElement.getAsJsonObject().entrySet();
                        var3 = es.iterator();

                        while(var3.hasNext()) {
                            Map.Entry<String, JsonElement> en = (Map.Entry)var3.next();
                            iterator((JsonElement)en.getValue(), result);
                        }
                    }

                }
            }
        }
    }
}

