package vi.legend.definition.domain;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Lớp này lưu trữ chi tiết lỗi phản hồi, chủ yếu dùng để tương thích với Validation.
 */
@Schema(
        title = "Chi tiết lỗi phản hồi",
        description = "Thực thể thông tin lỗi của Validation được thêm vào để tương thích với Validation"
)
public class Error implements Serializable {

    @Schema(
            title = "Thông tin đầy đủ về Exception",
            type = "string"
    )
    private String detail; // Thông tin chi tiết về lỗi

    @Schema(
            title = "Thông tin lỗi bổ sung, hiện tại chủ yếu là Message của Validation"
    )
    private String message; // Thông điệp lỗi bổ sung

    @Schema(
            title = "Mã lỗi bổ sung, hiện tại chủ yếu là Code của Validation"
    )
    private String code; // Mã lỗi bổ sung

    @Schema(
            title = "Trường lỗi bổ sung, hiện tại chủ yếu là Field của Validation"
    )
    private String field; // Trường lỗi bổ sung

    @Schema(
            title = "Thông tin lỗi stack trace"
    )
    private StackTraceElement[] stackTrace; // Thông tin stack trace lỗi

    // Constructor không tham số
    public Error() {
    }

    /**
     * Lấy thông tin chi tiết về lỗi.
     * @return Thông tin chi tiết về lỗi.
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * Đặt thông tin chi tiết về lỗi.
     * @param detail Thông tin chi tiết về lỗi.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Lấy thông điệp lỗi bổ sung.
     * @return Thông điệp lỗi bổ sung.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Đặt thông điệp lỗi bổ sung.
     * @param message Thông điệp lỗi bổ sung.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Lấy mã lỗi bổ sung.
     * @return Mã lỗi bổ sung.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Đặt mã lỗi bổ sung.
     * @param code Mã lỗi bổ sung.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Lấy trường lỗi bổ sung.
     * @return Trường lỗi bổ sung.
     */
    public String getField() {
        return this.field;
    }

    /**
     * Đặt trường lỗi bổ sung.
     * @param field Trường lỗi bổ sung.
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * Lấy thông tin stack trace lỗi.
     * @return Thông tin stack trace lỗi.
     */
    public StackTraceElement[] getStackTrace() {
        return this.stackTrace;
    }

    /**
     * Đặt thông tin stack trace lỗi.
     * @param stackTrace Thông tin stack trace lỗi.
     */
    public void setStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("detail", this.detail)
                .add("message", this.message)
                .add("code", this.code)
                .add("field", this.field)
                .toString();
    }
}

