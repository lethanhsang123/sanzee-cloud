package json.gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Lớp GsonUtils cung cấp các phương thức tiện ích để làm việc với JSON
 * sử dụng thư viện Gson, bao gồm việc chuyển đổi giữa các đối tượng Java và JSON.
 */
public class GsonUtils {

    // GsonBuilder để cấu hình và tạo đối tượng Gson duy nhất.
    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

    // Đối tượng Gson duy nhất, được khởi tạo theo mô hình Singleton.
    private static volatile Gson instance;

    /**
     * Constructor mặc định, được định nghĩa là private để ngăn chặn tạo đối tượng bên ngoài.
     */
    private GsonUtils() {
    }

    /**
     * Trả về đối tượng Gson duy nhất, khởi tạo đối tượng này nếu chưa tồn tại.
     * Sử dụng mô hình Singleton với double-checked locking.
     *
     * @return Đối tượng Gson.
     */
    public static Gson getInstance() {
        if (ObjectUtils.isEmpty(instance)) {
            synchronized(GSON_BUILDER) {
                if (ObjectUtils.isEmpty(instance)) {
                    instance = GSON_BUILDER.create();
                }
            }
        }
        return instance;
    }

    /**
     * Chuyển đổi chuỗi JSON thành đối tượng JsonElement.
     *
     * @param content Chuỗi JSON cần chuyển đổi.
     * @return Đối tượng JsonElement.
     */
    public static JsonElement toJsonElement(String content) {
        return JsonParser.parseString(content);
    }

    /**
     * Chuyển đổi chuỗi JSON thành đối tượng JsonArray.
     *
     * @param content Chuỗi JSON cần chuyển đổi.
     * @return Đối tượng JsonArray.
     */
    public static JsonArray toJsonArray(String content) {
        return toJsonElement(content).getAsJsonArray();
    }

    /**
     * Chuyển đổi chuỗi JSON thành đối tượng JsonObject.
     *
     * @param content Chuỗi JSON cần chuyển đổi.
     * @return Đối tượng JsonObject.
     */
    public static JsonObject toJsonObject(String content) {
        return toJsonElement(content).getAsJsonObject();
    }

    /**
     * Chuyển đổi đối tượng Java thành chuỗi JSON.
     *
     * @param <T> Kiểu của đối tượng.
     * @param domain Đối tượng cần chuyển đổi.
     * @return Chuỗi JSON tương ứng với đối tượng.
     */
    public static <T> String toJson(T domain) {
        return getInstance().toJson(domain);
    }

    /**
     * Chuyển đổi chuỗi JSON thành đối tượng Java.
     *
     * @param <T> Kiểu của đối tượng.
     * @param content Chuỗi JSON cần chuyển đổi.
     * @param valueType Lớp của đối tượng cần chuyển đổi.
     * @return Đối tượng Java tương ứng với chuỗi JSON.
     */
    public static <T> T toObject(String content, Class<T> valueType) {
        return getInstance().fromJson(content, valueType);
    }

    /**
     * Chuyển đổi chuỗi JSON thành đối tượng Java với kiểu dữ liệu phức tạp (generic).
     *
     * @param <T> Kiểu của đối tượng.
     * @param content Chuỗi JSON cần chuyển đổi.
     * @param typeOfT Kiểu dữ liệu phức tạp của đối tượng cần chuyển đổi.
     * @return Đối tượng Java tương ứng với chuỗi JSON.
     */
    public static <T> T toObject(String content, Type typeOfT) {
        return getInstance().fromJson(content, typeOfT);
    }

    /**
     * Chuyển đổi chuỗi JSON thành danh sách các đối tượng Java.
     *
     * @param <T> Kiểu của đối tượng trong danh sách.
     * @param content Chuỗi JSON cần chuyển đổi.
     * @param valueType Lớp của đối tượng cần chuyển đổi.
     * @return Danh sách các đối tượng Java.
     */
    public static <T> T toList(String content, Class<T> valueType) {
        return getInstance().fromJson(content, new TypeToken<List<T>>(){}.getType());
    }

    /**
     * Chuyển đổi chuỗi JSON thành danh sách các Map với kiểu giá trị tùy ý.
     *
     * @param <T> Kiểu của giá trị trong Map.
     * @param content Chuỗi JSON cần chuyển đổi.
     * @return Danh sách các Map.
     */
    public static <T> List<Map<String, T>> toListMap(String content) {
        return getInstance().fromJson(content, new TypeToken<List<Map<String, String>>>(){}.getType());
    }

    /**
     * Chuyển đổi chuỗi JSON thành Map với kiểu giá trị tùy ý.
     *
     * @param <T> Kiểu của giá trị trong Map.
     * @param gsonString Chuỗi JSON cần chuyển đổi.
     * @return Map chứa các giá trị tương ứng.
     */
    public static <T> Map<String, T> toMaps(String gsonString) {
        return getInstance().fromJson(gsonString, new TypeToken<Map<String, T>>(){}.getType());
    }

    // Khối static được sử dụng để cấu hình GSON_BUILDER ngay khi lớp được nạp.
    static {
        GSON_BUILDER.enableComplexMapKeySerialization();
        GSON_BUILDER.serializeNulls();
        GSON_BUILDER.setDateFormat("yyyy-MM-dd HH:mm:ss");
        GSON_BUILDER.disableHtmlEscaping();
    }
}

