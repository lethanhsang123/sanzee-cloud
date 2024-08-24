package vi.legend.assistant.core.json.jackson2.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp tiện ích cho việc xử lý YAML sử dụng Jackson.
 */
public class Jackson2YamlUtils {
    private static final Logger log = LoggerFactory.getLogger(Jackson2YamlUtils.class); // Logger để ghi lại thông tin lỗi
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory()); // Đối tượng ObjectMapper được cấu hình cho YAML

    // Constructor mặc định
    public Jackson2YamlUtils() {
    }

    /**
     * Lấy đối tượng ObjectMapper cấu hình cho YAML.
     * @return ObjectMapper
     */
    private static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Chuyển đối tượng thành chuỗi YAML.
     * @param entity Đối tượng cần chuyển đổi
     * @param <T> Loại của đối tượng
     * @return Chuỗi YAML tương ứng với đối tượng
     */
    public static <T> String writeAsString(T entity) {
        return writeAsString(entity, true);
    }

    /**
     * Chuyển đối tượng thành chuỗi YAML, có thể loại bỏ dấu ngoặc kép.
     * @param domain Đối tượng cần chuyển đổi
     * @param removeQuote Có loại bỏ dấu ngoặc kép không
     * @param <D> Loại của đối tượng
     * @return Chuỗi YAML tương ứng với đối tượng
     */
    public static <D> String writeAsString(D domain, boolean removeQuote) {
        try {
            String yaml = getObjectMapper().writeValueAsString(domain);
            // Loại bỏ dấu ngoặc kép nếu cần
            return StringUtils.isNotBlank(yaml) && removeQuote ? StringUtils.remove(yaml, "\"") : yaml;
        } catch (JsonProcessingException var3) {
            log.error("[Herodotus] |- Lỗi xử lý khi chuyển đổi thành chuỗi YAML! {}", var3.getMessage());
            return null;
        }
    }

    // Khối static để cấu hình ObjectMapper
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // Loại bỏ các trường giá trị null khi chuyển đổi thành YAML
    }
}

