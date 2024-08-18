package vi.legend.definition.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import vi.legend.definition.constants.ErrorCodes;

/**
 * Lớp này thực hiện ánh xạ mã lỗi từ các đối tượng phản hồi (Feedback) tới mã lỗi số nguyên.
 */
public class ErrorCodeMapper {

    // Biến instance duy nhất của lớp (singleton)
    private static volatile ErrorCodeMapper instance;

    // Bản đồ lưu trữ ánh xạ phản hồi tới mã lỗi
    private final Map<Feedback, Integer> dictionary = new LinkedHashMap<Feedback, Integer>() {
        {
            // Khởi tạo ánh xạ phản hồi thành công và không có nội dung
            this.put(ErrorCodes.OK, ErrorCodes.OK.getSequence());
            this.put(ErrorCodes.NO_CONTENT, ErrorCodes.NO_CONTENT.getSequence());
        }
    };

    // Constructor riêng, không cho phép tạo đối tượng ngoài lớp này
    private ErrorCodeMapper() {
    }

    /**
     * Lấy đối tượng instance duy nhất của lớp ErrorCodeMapper (singleton).
     * @return Đối tượng ErrorCodeMapper duy nhất.
     */
    public static ErrorCodeMapper getInstance() {
        if (ObjectUtils.isEmpty(instance)) {
            Class<?> var0 = ErrorCodeMapper.class;
            synchronized(ErrorCodeMapper.class) {
                if (ObjectUtils.isEmpty(instance)) {
                    instance = new ErrorCodeMapper();
                }
            }
        }

        return instance;
    }

    /**
     * Lấy mã lỗi tương ứng với phản hồi.
     * @param feedback Phản hồi cần tra cứu mã lỗi.
     * @return Mã lỗi tương ứng với phản hồi.
     */
    private Integer getErrorCode(Feedback feedback) {
        return this.dictionary.get(feedback);
    }

    /**
     * Thêm các ánh xạ phản hồi và mã lỗi vào bản đồ.
     * @param indexes Bản đồ chứa các ánh xạ phản hồi và mã lỗi mới.
     */
    public void append(Map<Feedback, Integer> indexes) {
        if (MapUtils.isNotEmpty(indexes)) {
            this.dictionary.putAll(indexes);
        }
    }

    /**
     * Lấy mã lỗi tương ứng với phản hồi từ singleton instance.
     * @param feedback Phản hồi cần tra cứu mã lỗi.
     * @return Mã lỗi tương ứng với phản hồi.
     */
    public static Integer get(Feedback feedback) {
        return getInstance().getErrorCode(feedback);
    }
}

