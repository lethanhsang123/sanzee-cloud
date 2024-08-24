package vi.legend.assistant.core.json.jackson2.deserialzer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.util.StringUtils;


/**
 * Serializer để chuyển đổi chuỗi phân cách bởi dấu phẩy thành tập hợp.
 */
public class CommaDelimitedStringToSetSerializer extends StdSerializer<String> {

    /**
     * Constructor mặc định cho CommaDelimitedStringToSetSerializer.
     * Khởi tạo với kiểu dữ liệu chuỗi (String).
     */
    public CommaDelimitedStringToSetSerializer() {
        super(String.class);
    }

    /**
     * Chuyển đổi chuỗi phân cách bởi dấu phẩy thành tập hợp và ghi vào JsonGenerator.
     * @param value Chuỗi cần chuyển đổi thành tập hợp
     * @param gen Đối tượng JsonGenerator để ghi dữ liệu JSON
     * @param provider Provider để cung cấp các tính năng serialization
     * @throws IOException Nếu có lỗi trong quá trình ghi dữ liệu
     */
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Set<String> collection = new HashSet<>();

        // Nếu chuỗi không rỗng, chuyển đổi chuỗi thành tập hợp
        if (StringUtils.hasText(value)) {
            if (org.apache.commons.lang3.StringUtils.contains(value, ",")) {
                collection = StringUtils.commaDelimitedListToSet(value);
            } else {
                collection.add(value);
            }
        }

        int len = collection.size();
        gen.writeStartArray(collection, len);

        // Serialize các phần tử trong tập hợp
        this.serializeContents(collection, gen, provider);
        gen.writeEndArray();
    }

    /**
     * Serialize các phần tử trong tập hợp thành các chuỗi JSON.
     * @param value Tập hợp các chuỗi cần serialize
     * @param g Đối tượng JsonGenerator để ghi dữ liệu JSON
     * @param provider Provider để cung cấp các tính năng serialization
     * @throws IOException Nếu có lỗi trong quá trình ghi dữ liệu
     */
    private void serializeContents(Set<String> value, JsonGenerator g, SerializerProvider provider) throws IOException {
        int i = 0;

        try {
            // Lặp qua từng phần tử trong tập hợp và ghi chúng vào JsonGenerator
            for (Iterator<String> var5 = value.iterator(); var5.hasNext(); ++i) {
                String str = var5.next();
                if (str == null) {
                    provider.defaultSerializeNull(g);
                } else {
                    g.writeString(str);
                }
            }
        } catch (Exception var7) {
            // Bắt lỗi và ném lại ngoại lệ với thông tin thêm
            this.wrapAndThrow(provider, var7, value, i);
        }
    }
}

