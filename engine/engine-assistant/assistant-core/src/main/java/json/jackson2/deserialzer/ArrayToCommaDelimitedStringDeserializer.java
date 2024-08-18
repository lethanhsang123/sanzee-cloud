package json.jackson2.deserialzer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Deserializer để chuyển đổi giá trị JSON từ kiểu mảng thành chuỗi phân cách bởi dấu phẩy.
 */
public class ArrayToCommaDelimitedStringDeserializer extends StdDeserializer<String> {

    /**
     * Constructor mặc định cho ArrayToCommaDelimitedStringDeserializer.
     * Khởi tạo với kiểu dữ liệu chuỗi (String).
     */
    protected ArrayToCommaDelimitedStringDeserializer() {
        super(String.class);
    }

    /**
     * Trả về kiểu giá trị của các phần tử trong mảng.
     * @return JavaType của kiểu Set<String>
     */
    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(Set.class);
    }

    /**
     * Chuyển đổi giá trị từ JsonParser thành chuỗi phân cách bởi dấu phẩy.
     * Đọc mảng từ JSON, chuyển đổi thành tập hợp và sau đó thành chuỗi với các phần tử phân cách bởi dấu phẩy.
     * @param jsonParser Đối tượng JsonParser để đọc dữ liệu JSON
     * @param deserializationContext Ngữ cảnh deserialization
     * @return Chuỗi các phần tử phân cách bởi dấu phẩy, hoặc null nếu tập hợp rỗng
     * @throws IOException Nếu có lỗi trong quá trình đọc dữ liệu
     * @throws JacksonException Nếu có lỗi trong quá trình xử lý JSON
     */
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
        jsonParser.setCodec(objectMapper);

        // Đọc mảng từ JSON và chuyển đổi thành tập hợp
        Set<String> collection = (Set<String>)jsonParser.readValueAs(new TypeReference<Set<String>>() {
        });

        // Chuyển tập hợp thành chuỗi phân cách bởi dấu phẩy
        return CollectionUtils.isNotEmpty(collection) ? StringUtils.collectionToCommaDelimitedString(collection) : null;
    }
}

