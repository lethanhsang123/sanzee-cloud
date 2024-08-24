package vi.legend.assistant.definition.exception;

import vi.legend.assistant.definition.domain.Result;

/**
 * Lớp trừu tượng đại diện cho các ngoại lệ thời gian chạy trong hệ thống.
 * Kế thừa từ `RuntimeException` và thực thi giao diện `HerodotusException`.
 */
public abstract class AbstractRuntimeException extends RuntimeException implements SanzeeException {

    /**
     * Constructor không tham số.
     */
    public AbstractRuntimeException() {
    }

    /**
     * Constructor với thông điệp lỗi.
     * @param message Thông điệp lỗi.
     */
    public AbstractRuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor với thông điệp lỗi và nguyên nhân lỗi.
     * @param message Thông điệp lỗi.
     * @param cause Nguyên nhân lỗi.
     */
    public AbstractRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor với nguyên nhân lỗi.
     * @param cause Nguyên nhân lỗi.
     */
    public AbstractRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor với thông điệp lỗi, nguyên nhân lỗi, và các thông số khác.
     * @param message Thông điệp lỗi.
     * @param cause Nguyên nhân lỗi.
     * @param enableSuppression Cho phép hay không việc ngoại lệ bị bỏ qua.
     * @param writableStackTrace Cho phép hay không việc stack trace có thể ghi.
     */
    protected AbstractRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Tạo đối tượng `Result` từ ngoại lệ này.
     * Lưu trữ thông tin lỗi, stack trace và chi tiết của ngoại lệ vào đối tượng `Result`.
     * @return Đối tượng `Result` chứa thông tin lỗi.
     */
    public Result<String> getResult() {
        Result<String> result = Result.failure(this.getFeedback());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}

