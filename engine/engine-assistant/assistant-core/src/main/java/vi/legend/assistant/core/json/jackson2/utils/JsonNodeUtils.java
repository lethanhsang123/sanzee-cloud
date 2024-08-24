package vi.legend.assistant.core.json.jackson2.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * Lớp tiện ích để thao tác với đối tượng JsonNode.
 */
public class JsonNodeUtils {
    public static final TypeReference<Instant> INSTANT = new TypeReference<Instant>() {
    }; // Tham chiếu kiểu cho Instant
    public static final TypeReference<Set<String>> STRING_SET = new TypeReference<Set<String>>() {
    }; // Tham chiếu kiểu cho Set<String>
    public static final TypeReference<Map<String, Object>> STRING_OBJECT_MAP = new TypeReference<Map<String, Object>>() {
    }; // Tham chiếu kiểu cho Map<String, Object>

    // Constructor mặc định
    public JsonNodeUtils() {
    }

    /**
     * Tìm giá trị kiểu chuỗi từ một trường trong JsonNode.
     * @param jsonNode Đối tượng JsonNode cần tìm kiếm
     * @param fieldName Tên trường cần tìm
     * @return Giá trị chuỗi nếu tìm thấy, ngược lại trả về null
     */
    public static String findStringValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        } else {
            JsonNode value = jsonNode.findValue(fieldName);
            return value != null && value.isTextual() ? value.asText() : null;
        }
    }

    /**
     * Tìm giá trị kiểu boolean từ một trường trong JsonNode.
     * @param jsonNode Đối tượng JsonNode cần tìm kiếm
     * @param fieldName Tên trường cần tìm
     * @return Giá trị boolean nếu tìm thấy, ngược lại trả về false
     */
    public static boolean findBooleanValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return false;
        } else {
            JsonNode value = jsonNode.findValue(fieldName);
            return value != null && value.isBoolean() && value.asBoolean();
        }
    }

    /**
     * Tìm giá trị từ một trường trong JsonNode và chuyển đổi thành kiểu dữ liệu tương ứng.
     * @param jsonNode Đối tượng JsonNode cần tìm kiếm
     * @param fieldName Tên trường cần tìm
     * @param valueTypeReference Tham chiếu kiểu dữ liệu để chuyển đổi
     * @param mapper Đối tượng ObjectMapper để chuyển đổi giá trị
     * @param <T> Loại dữ liệu của giá trị cần tìm
     * @return Giá trị đã chuyển đổi nếu tìm thấy, ngược lại trả về null
     */
    public static <T> T findValue(JsonNode jsonNode, String fieldName, TypeReference<T> valueTypeReference, ObjectMapper mapper) {
        if (jsonNode == null) {
            return null;
        } else {
            JsonNode value = jsonNode.findValue(fieldName);
            return value != null && value.isContainerNode() ? mapper.convertValue(value, valueTypeReference) : null;
        }
    }

    /**
     * Tìm và trả về đối tượng JsonNode từ một trường trong JsonNode.
     * @param jsonNode Đối tượng JsonNode cần tìm kiếm
     * @param fieldName Tên trường cần tìm
     * @return Đối tượng JsonNode nếu tìm thấy và là kiểu đối tượng, ngược lại trả về null
     */
    public static JsonNode findObjectNode(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        } else {
            JsonNode value = jsonNode.findValue(fieldName);
            return value != null && value.isObject() ? value : null;
        }
    }

    /**
     * Đọc một trường JsonNode từ JsonNode gốc.
     * @param jsonNode Đối tượng JsonNode gốc
     * @param field Tên trường cần đọc
     * @return Đối tượng JsonNode của trường đọc được hoặc MissingNode nếu trường không tồn tại
     */
    public static JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return (JsonNode)(jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance());
    }
}

