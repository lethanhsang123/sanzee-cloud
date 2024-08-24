package vi.legend.assistant.core.json.jackson2.deserialzer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.util.StringUtils;

/**
 * Deserializer để chuyển đổi giá trị JSON từ kiểu mảng hoặc chuỗi thành tập hợp chuỗi (Set<String>).
 */
public class ArrayOrStringToSetDeserializer extends StdDeserializer<Set<String>> {

    /**
     * Constructor mặc định cho ArrayOrStringToSetDeserializer.
     * Khởi tạo với kiểu dữ liệu tập hợp chuỗi (Set<String>).
     */
    public ArrayOrStringToSetDeserializer() {
        super(Set.class);
    }

    /**
     * Trả về kiểu giá trị của các phần tử trong tập hợp.
     * @return JavaType của kiểu String
     */
    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(String.class);
    }

    /**
     * Chuyển đổi giá trị từ JsonParser thành tập hợp chuỗi (Set<String>).
     * Nếu giá trị là một chuỗi, nó sẽ được chuyển đổi thành tập hợp sau khi thay thế các khoảng trắng bằng dấu phẩy.
     * Nếu giá trị là mảng, nó sẽ được chuyển đổi trực tiếp thành tập hợp.
     * @param jsonParser Đối tượng JsonParser để đọc dữ liệu JSON
     * @param deserializationContext Ngữ cảnh deserialization
     * @return Tập hợp chuỗi (Set<String>) sau khi deserialization
     * @throws IOException Nếu có lỗi trong quá trình đọc dữ liệu
     * @throws JacksonException Nếu có lỗi trong quá trình xử lý JSON
     */
    public Set<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonToken token = jsonParser.getCurrentToken();
        if (token.isScalarValue()) {
            String value = jsonParser.getText();
            value = value.replaceAll("\\s+", ","); // Thay thế khoảng trắng bằng dấu phẩy
            return new LinkedHashSet<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(value)));
        } else {
            return (Set<String>)jsonParser.readValueAs(new TypeReference<Set<String>>() {
            });
        }
    }
}

