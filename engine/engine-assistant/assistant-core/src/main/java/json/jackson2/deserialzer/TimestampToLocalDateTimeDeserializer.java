package json.jackson2.deserialzer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Serializer để chuyển đổi chuỗi phân cách bởi dấu phẩy thành tập hợp.
 */
/**
 * Deserializer để chuyển đổi giá trị timestamp thành LocalDateTime.
 */
public class TimestampToLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    /**
     * Constructor mặc định cho TimestampToLocalDateTimeDeserializer.
     */
    public TimestampToLocalDateTimeDeserializer() {
    }

    /**
     * Chuyển đổi giá trị timestamp trong JsonParser thành LocalDateTime.
     * @param jsonParser Đối tượng JsonParser để đọc giá trị timestamp
     * @param deserializationContext Ngữ cảnh deserialization
     * @return LocalDateTime tương ứng với timestamp; trả về null nếu timestamp không hợp lệ
     * @throws IOException Nếu có lỗi trong quá trình đọc giá trị
     * @throws JacksonException Nếu có lỗi liên quan đến Jackson
     */
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // Lấy giá trị timestamp từ JsonParser
        long timestamp = jsonParser.getValueAsLong();

        // Nếu timestamp hợp lệ (lớn hơn 0), chuyển đổi thành LocalDateTime
        if (timestamp > 0L) {
            Instant instant = Instant.ofEpochMilli(timestamp);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        } else {
            // Nếu timestamp không hợp lệ, trả về null
            return null;
        }
    }
}


