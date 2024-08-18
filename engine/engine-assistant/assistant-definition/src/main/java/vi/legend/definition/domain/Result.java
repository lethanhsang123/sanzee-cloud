package vi.legend.definition.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import vi.legend.definition.constants.ErrorCodes;

/**
 * Lớp đại diện cho đối tượng phản hồi đồng nhất.
 * Định nghĩa đối tượng trả về đồng nhất cho tất cả các API REST.
 * Ví dụ: new Result<T>().ok().message("XXX")
 */
@Schema(
        title = "Đối tượng phản hồi đồng nhất",
        description = "Định nghĩa đối tượng trả về đồng nhất cho tất cả các API REST",
        example = "new Result<T>().ok().message(\"XXX\")"
)
public class Result<T> implements Serializable {
    @Schema(
            title = "Dấu thời gian phản hồi",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private final Date timestamp = new Date();

    @Schema(
            title = "Thông tin lỗi kiểm tra"
    )
    private final Error error = new Error();

    @Schema(
            title = "Mã phản hồi tùy chỉnh"
    )
    private int code = 0;

    @Schema(
            title = "Thông tin phản hồi"
    )
    private String message;

    @Schema(
            title = "Đường dẫn yêu cầu"
    )
    private String path;

    @Schema(
            title = "Dữ liệu phản hồi"
    )
    private T data;

    @Schema(
            title = "Mã trạng thái HTTP"
    )
    private int status;

    @Schema(
            title = "TraceId theo dõi liên kết"
    )
    private String traceId;

    public Result() {
    }

    /**
     * Tạo đối tượng Result với các tham số cụ thể.
     *
     * @param message Thông tin phản hồi.
     * @param detail Chi tiết lỗi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @param data Dữ liệu phản hồi.
     * @param stackTrace Trace stack của lỗi.
     * @return Đối tượng Result được tạo.
     */
    private static <T> Result<T> create(String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
        Result<T> result = new Result<>();
        if (StringUtils.isNotBlank(message)) {
            result.message(message);
        }

        if (StringUtils.isNotBlank(detail)) {
            result.detail(detail);
        }

        result.code(code);
        result.status(status);
        if (ObjectUtils.isNotEmpty(data)) {
            result.data(data);
        }

        if (ArrayUtils.isNotEmpty(stackTrace)) {
            result.stackTrace(stackTrace);
        }

        return result;
    }

    /**
     * Tạo đối tượng Result thành công với thông tin phản hồi và mã trạng thái HTTP.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> success(String message, int code, int status, T data) {
        return create(message, (String)null, code, status, data, (StackTraceElement[])null);
    }

    /**
     * Tạo đối tượng Result thành công với thông tin phản hồi và mã trạng thái HTTP là 200.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> success(String message, int code, T data) {
        return success(message, code, 200, data);
    }

    /**
     * Tạo đối tượng Result thành công với thông tin phản hồi và mã trạng thái HTTP là 200.
     *
     * @param message Thông tin phản hồi.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> success(String message, T data) {
        return success(message, ErrorCodes.OK.getSequence(), data);
    }

    /**
     * Tạo đối tượng Result thành công với thông tin phản hồi.
     *
     * @param message Thông tin phản hồi.
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> success(String message) {
        return success(message, null);
    }

    /**
     * Tạo đối tượng Result thành công với thông tin phản hồi mặc định là "操作成功！".
     *
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> success() {
        return success("操作成功！");
    }

    /**
     * Tạo đối tượng Result thành công với dữ liệu.
     *
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi thành công.
     */
    public static <T> Result<T> content(T data) {
        return success("操作成功！", data);
    }

    /**
     * Tạo đối tượng Result lỗi với các tham số cụ thể.
     *
     * @param message Thông tin phản hồi.
     * @param detail Chi tiết lỗi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @param data Dữ liệu phản hồi.
     * @param stackTrace Trace stack của lỗi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, String detail, int code, int status, T data, StackTraceElement[] stackTrace) {
        return create(message, detail, code, status, data, stackTrace);
    }

    /**
     * Tạo đối tượng Result lỗi với các tham số cụ thể, không có trace stack.
     *
     * @param message Thông tin phản hồi.
     * @param detail Chi tiết lỗi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, String detail, int code, int status, T data) {
        return failure(message, detail, code, status, data, (StackTraceElement[])null);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi và mã trạng thái HTTP.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, int code, int status, T data) {
        return failure(message, message, code, status, data);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi và mã trạng thái HTTP mặc định là 500.
     *
     * @param message Thông tin phản hồi.
     * @param detail Chi tiết lỗi.
     * @param code Mã phản hồi.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, String detail, int code, T data) {
        return failure(message, detail, code, 500, data);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi và mã phản hồi.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, int code, T data) {
        return failure(message, message, code, data);
    }

    /**
     * Tạo đối tượng Result lỗi từ đối tượng Feedback.
     *
     * @param feedback Đối tượng Feedback.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(Feedback feedback) {
        return failure((Feedback)feedback, null);
    }

    /**
     * Tạo đối tượng Result lỗi từ đối tượng Feedback với dữ liệu.
     *
     * @param feedback Đối tượng Feedback.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(Feedback feedback, T data) {
        Feedback result = ObjectUtils.isNotEmpty(feedback) ? feedback : ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION;
        Integer code = ErrorCodeMapper.get((Feedback)result);
        return failure(feedback.getMessage(), code, feedback.getStatus(), data);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi và mã lỗi mặc định là mã lỗi của máy chủ nội bộ.
     *
     * @param message Thông tin phản hồi.
     * @param data Dữ liệu phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message, T data) {
        return failure(message, ErrorCodes.INTERNAL_SERVER_ERROR.getSequence(), data);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi.
     *
     * @param message Thông tin phản hồi.
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure(String message) {
        return failure((String)message, null);
    }

    /**
     * Tạo đối tượng Result lỗi với thông tin phản hồi mặc định là "操作失败！".
     *
     * @return Đối tượng Result với phản hồi lỗi.
     */
    public static <T> Result<T> failure() {
        return failure("操作失败！");
    }

    /**
     * Tạo đối tượng Result trống với thông tin phản hồi, mã lỗi và mã trạng thái HTTP.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @param status Mã trạng thái HTTP.
     * @return Đối tượng Result trống.
     */
    public static <T> Result<T> empty(String message, int code, int status) {
        return create(message, (String)null, code, status, null, null);
    }

    /**
     * Tạo đối tượng Result trống với thông tin phản hồi và mã lỗi.
     *
     * @param message Thông tin phản hồi.
     * @param code Mã phản hồi.
     * @return Đối tượng Result trống.
     */
    public static <T> Result<T> empty(String message, int code) {
        return empty(message, code, ErrorCodes.NO_CONTENT.getStatus());
    }

    /**
     * Tạo đối tượng Result trống từ đối tượng Feedback.
     *
     * @param feedback Đối tượng Feedback.
     * @return Đối tượng Result trống.
     */
    public static <T> Result<T> empty(Feedback feedback) {
        int code = ErrorCodeMapper.get(feedback);
        return empty(feedback.getMessage(), code, feedback.getStatus());
    }

    /**
     * Tạo đối tượng Result trống với thông tin phản hồi.
     *
     * @param message Thông tin phản hồi.
     * @return Đối tượng Result trống.
     */
    public static <T> Result<T> empty(String message) {
        return empty(message, ErrorCodes.NO_CONTENT.getSequence());
    }

    /**
     * Tạo đối tượng Result trống với thông tin phản hồi mặc định là "未查询到相关内容！".
     *
     * @return Đối tượng Result trống.
     */
    public static <T> Result<T> empty() {
        return empty("未查询到相关内容！");
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public T getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public Error getError() {
        return this.error;
    }

    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public Result<T> path(String path) {
        this.path = path;
        return this;
    }

    public Result<T> type(Feedback feedback) {
        this.code = ErrorCodeMapper.get(feedback);
        this.message = feedback.getMessage();
        this.status = feedback.getStatus();
        return this;
    }

    public Result<T> status(int httpStatus) {
        this.status = httpStatus;
        return this;
    }

    public Result<T> traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public Result<T> stackTrace(StackTraceElement[] stackTrace) {
        this.error.setStackTrace(stackTrace);
        return this;
    }

    public Result<T> detail(String detail) {
        this.error.setDetail(detail);
        return this;
    }

    public Result<T> validation(String message, String code, String field) {
        this.error.setMessage(message);
        this.error.setCode(code);
        this.error.setField(field);
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("code", this.code).add("message", this.message).add("path", this.path).add("data", this.data).add("status", this.status).add("timestamp", this.timestamp).add("error", this.error).toString();
    }

    public Map<String, Object> toModel() {
        Map<String, Object> result = new HashMap(8);
        result.put("code", this.code);
        result.put("message", this.message);
        result.put("path", this.path);
        result.put("data", this.data);
        result.put("status", this.status);
        result.put("timestamp", this.timestamp);
        result.put("error", this.error);
        return result;
    }
}
