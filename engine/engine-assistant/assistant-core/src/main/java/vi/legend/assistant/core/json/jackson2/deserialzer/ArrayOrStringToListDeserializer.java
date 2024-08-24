package vi.legend.assistant.core.json.jackson2.deserialzer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Deserializer để chuyển đổi giá trị JSON từ kiểu mảng hoặc chuỗi thành danh sách chuỗi (List<String>).
 */
public class ArrayOrStringToListDeserializer extends StdDeserializer<List<String>> {

    /**
     * Constructor mặc định cho ArrayOrStringToListDeserializer.
     * Khởi tạo với kiểu dữ liệu danh sách chuỗi (List<String>).
     */
    public ArrayOrStringToListDeserializer() {
        super(List.class);
    }

    /**
     * Trả về kiểu giá trị của các phần tử trong danh sách.
     * @return JavaType của kiểu String
     */
    public JavaType getValueType() {
        return TypeFactory.defaultInstance().constructType(String.class);
    }

    /**
     * Chuyển đổi giá trị từ JsonParser thành danh sách chuỗi (List<String>).
     * Nếu giá trị là một chuỗi, nó sẽ được chuyển đổi thành danh sách sau khi thay thế các khoảng trắng bằng dấu phẩy.
     * Nếu giá trị là mảng, nó sẽ được chuyển đổi trực tiếp thành danh sách.
     * @param jsonParser Đối tượng JsonParser để đọc dữ liệu JSON
     * @param deserializationContext Ngữ cảnh deserialization
     * @return Danh sách chuỗi (List<String>) sau khi deserialization
     * @throws IOException Nếu có lỗi trong quá trình đọc dữ liệu
     * @throws JacksonException Nếu có lỗi trong quá trình xử lý JSON
     */
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonToken token = jsonParser.getCurrentToken();
        if (token.isScalarValue()) {
            String value = jsonParser.getText();
            value = value.replaceAll("\\s+", ","); // Thay thế khoảng trắng bằng dấu phẩy
            return new ArrayList<>(Arrays.asList(StringUtils.commaDelimitedListToStringArray(value)));
        } else {
            return (List<String>)jsonParser.readValueAs(new TypeReference<List<String>>() {
            });
        }
    }
}

