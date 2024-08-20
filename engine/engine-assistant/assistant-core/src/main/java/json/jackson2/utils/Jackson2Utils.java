package json.jackson2.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Lớp tiện ích cho việc xử lý JSON sử dụng Jackson.
 */
@Component
public class Jackson2Utils {
    private static final Logger logger = LoggerFactory.getLogger(Jackson2Utils.class);
    private static ObjectMapper OBJECT_MAPPER; // Đối tượng ObjectMapper dùng chung
    @Autowired
    private ObjectMapper objectMapper; // Đối tượng ObjectMapper được tiêm từ bên ngoài

    // Constructor mặc định
    public Jackson2Utils() {
    }

    /**
     * Lấy đối tượng ObjectMapper.
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * Đăng ký một module với ObjectMapper.
     * @param module Module cần đăng ký
     * @return ObjectMapper sau khi đăng ký module
     */
    public static ObjectMapper registerModule(Module module) {
        return getObjectMapper().registerModules(new Module[]{module});
    }

    /**
     * Chuyển đối tượng thành chuỗi JSON.
     * @param domain Đối tượng cần chuyển đổi
     * @param <T> Loại của đối tượng
     * @return Chuỗi JSON tương ứng với đối tượng
     */
    public static <T> String toJson(T domain) {
        try {
            return getObjectMapper().writeValueAsString(domain);
        } catch (JsonProcessingException var2) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi thành JSON! {}", var2.getMessage());
            return null;
        }
    }

    /**
     * Lấy TypeFactory từ ObjectMapper.
     * @return TypeFactory
     */
    public static TypeFactory getTypeFactory() {
        return getObjectMapper().getTypeFactory();
    }

    /**
     * Chuyển chuỗi JSON thành đối tượng.
     * @param content Chuỗi JSON
     * @param valueType Loại của đối tượng cần chuyển đổi
     * @param <T> Loại của đối tượng
     * @return Đối tượng chuyển đổi từ JSON
     */
    public static <T> T toObject(String content, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(content, valueType);
        } catch (JsonProcessingException var3) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi thành đối tượng với loại giá trị! {}", var3.getMessage());
            return null;
        }
    }

    /**
     * Chuyển đổi từ Map thành đối tượng.
     * @param content Map cần chuyển đổi
     * @param valueType Loại của đối tượng
     * @param <T> Loại của đối tượng
     * @return Đối tượng chuyển đổi từ Map
     */
    public static <T> T toObject(Map<String, Object> content, Class<T> valueType) {
        try {
            return getObjectMapper().convertValue(content, valueType);
        } catch (IllegalArgumentException var3) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi từ Map thành đối tượng với loại giá trị! {}", var3.getMessage());
            return null;
        }
    }

    /**
     * Chuyển chuỗi JSON thành đối tượng sử dụng TypeReference.
     * @param content Chuỗi JSON
     * @param typeReference TypeReference của đối tượng
     * @param <T> Loại của đối tượng
     * @return Đối tượng chuyển đổi từ JSON
     */
    public static <T> T toObject(String content, TypeReference<T> typeReference) {
        try {
            return getObjectMapper().readValue(content, typeReference);
        } catch (JsonProcessingException var3) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi thành đối tượng với TypeReference! {}", var3.getMessage());
            return null;
        }
    }

    /**
     * Chuyển chuỗi JSON thành đối tượng sử dụng JavaType.
     * @param content Chuỗi JSON
     * @param javaType JavaType của đối tượng
     * @param <T> Loại của đối tượng
     * @return Đối tượng chuyển đổi từ JSON
     */
    public static <T> T toObject(String content, JavaType javaType) {
        try {
            return getObjectMapper().readValue(content, javaType);
        } catch (JsonProcessingException var3) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi thành đối tượng với JavaType! {}", var3.getMessage());
            return null;
        }
    }

    /**
     * Chuyển chuỗi JSON thành danh sách đối tượng.
     * @param content Chuỗi JSON
     * @param clazz Loại của đối tượng trong danh sách
     * @param <T> Loại của đối tượng
     * @return Danh sách đối tượng chuyển đổi từ JSON
     */
    public static <T> List<T> toList(String content, Class<T> clazz) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(List.class, new Class[]{clazz});
        return (List)toObject(content, javaType);
    }

    /**
     * Chuyển chuỗi JSON thành bản đồ (Map) đối tượng.
     * @param content Chuỗi JSON
     * @param keyClass Loại của khóa
     * @param valueClass Loại của giá trị
     * @param <K> Loại của khóa
     * @param <V> Loại của giá trị
     * @return Bản đồ đối tượng chuyển đổi từ JSON
     */
    public static <K, V> Map<K, V> toMap(String content, Class<K> keyClass, Class<V> valueClass) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
        return (Map)toObject((String)content, (JavaType)javaType);
    }

    /**
     * Chuyển chuỗi JSON thành bản đồ (Map) với khóa là String và giá trị là Object.
     * @param content Chuỗi JSON
     * @return Bản đồ chuyển đổi từ JSON
     */
    public static Map<String, Object> toMap(String content) {
        return toMap(content, String.class, Object.class);
    }

    /**
     * Chuyển chuỗi JSON thành tập hợp (Set) đối tượng.
     * @param content Chuỗi JSON
     * @param clazz Loại của đối tượng trong tập hợp
     * @param <T> Loại của đối tượng
     * @return Tập hợp đối tượng chuyển đổi từ JSON
     */
    public static <T> Set<T> toSet(String content, Class<T> clazz) {
        JavaType javaType = getTypeFactory().constructCollectionLikeType(Set.class, clazz);
        return (Set)toObject((String)content, (JavaType)javaType);
    }

    /**
     * Chuyển chuỗi JSON thành mảng đối tượng.
     * @param content Chuỗi JSON
     * @param clazz Loại của đối tượng trong mảng
     * @param <T> Loại của đối tượng
     * @return Mảng đối tượng chuyển đổi từ JSON
     */
    public static <T> T[] toArray(String content, Class<T> clazz) {
        JavaType javaType = getTypeFactory().constructArrayType(clazz);
        return toObject((String)content, (JavaType)javaType);
    }

    /**
     * Chuyển chuỗi JSON thành mảng đối tượng (sử dụng TypeReference).
     * @param content Chuỗi JSON
     * @param <T> Loại của đối tượng trong mảng
     * @return Mảng đối tượng chuyển đổi từ JSON
     */
    public static <T> T[] toArray(String content) {
        return toObject(content, new TypeReference<T[]>() {});
    }

    /**
     * Chuyển chuỗi JSON thành JsonNode.
     * @param content Chuỗi JSON
     * @return JsonNode chuyển đổi từ JSON
     */
    public static JsonNode toNode(String content) {
        try {
            return getObjectMapper().readTree(content);
        } catch (JsonProcessingException var2) {
            logger.error("[Sanzee] |- Lỗi xử lý JSON với Jackson2, khi chuyển đổi thành node từ chuỗi! {}", var2.getMessage());
            return null;
        }
    }

    /**
     * Chuyển JsonParser thành JsonNode.
     * @param jsonParser JsonParser
     * @return JsonNode chuyển đổi từ JsonParser
     */
    public static JsonNode toNode(JsonParser jsonParser) {
        try {
            return (JsonNode)getObjectMapper().readTree(jsonParser);
        } catch (IOException var2) {
            logger.error("[Sanzee] |- Lỗi IO với Jackson2, khi chuyển đổi thành node từ JsonParser! {}", var2.getMessage());
            return null;
        }
    }

    /**
     * Tạo JsonParser từ chuỗi JSON.
     * @param content Chuỗi JSON
     * @return JsonParser được tạo từ chuỗi JSON
     */
    public static JsonParser createParser(String content) {
        try {
            return getObjectMapper().createParser(content);
        } catch (IOException var2) {
            logger.error("[Sanzee] |- Lỗi IO với Jackson2, khi tạo JsonParser! {}", var2.getMessage());
            return null;
        }
    }

    /**
     * Lặp qua các node trong JsonNode và áp dụng hàm.
     * @param jsonNode JsonNode cần lặp qua
     * @param function Hàm để áp dụng cho mỗi node
     * @param <R> Loại kết quả của hàm
     * @return Kết quả của hàm áp dụng cho node
     */
    public static <R> R loop(JsonNode jsonNode, Function<JsonNode, R> function) {
        Iterator it;
        if (jsonNode.isObject()) {
            it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = (Map.Entry)it.next();
                loop((JsonNode)entry.getValue(), function);
            }
        }
        if (jsonNode.isArray()) {
            it = jsonNode.iterator();
            while (it.hasNext()) {
                JsonNode node = (JsonNode)it.next();
                loop(node, function);
            }
        }
        return jsonNode.isValueNode() ? function.apply(jsonNode) : null;
    }

    /**
     * Khởi tạo đối tượng Jackson2Utils, thiết lập ObjectMapper.
     */
    @PostConstruct
    public void init() {
        if (ObjectUtils.isNotEmpty(this.objectMapper)) {
            OBJECT_MAPPER = this.objectMapper;
        } else {
            OBJECT_MAPPER = new ObjectMapper();
        }
    }
}


