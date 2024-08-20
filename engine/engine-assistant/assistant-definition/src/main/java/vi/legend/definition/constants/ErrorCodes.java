package vi.legend.definition.constants;

import vi.legend.definition.feedback.OkFeedback;
import vi.legend.definition.feedback.CustomizeFeedback;
import vi.legend.definition.feedback.ForbiddenFeedback;
import vi.legend.definition.feedback.InternalServerErrorFeedback;
import vi.legend.definition.feedback.MethodNotAllowedFeedback;
import vi.legend.definition.feedback.NoContentFeedback;
import vi.legend.definition.feedback.NotAcceptableFeedback;
import vi.legend.definition.feedback.NotFoundFeedback;
import vi.legend.definition.feedback.NotImplementedFeedback;
import vi.legend.definition.feedback.PreconditionFailedFeedback;
import vi.legend.definition.feedback.ServiceUnavailableFeedback;
import vi.legend.definition.feedback.UnauthorizedFeedback;
import vi.legend.definition.feedback.UnsupportedMediaTypeFeedback;

public interface ErrorCodes {
    // Phản hồi thành công
    OkFeedback OK = new OkFeedback("Thành công");

    // Phản hồi không có nội dung
    NoContentFeedback NO_CONTENT = new NoContentFeedback("Không có nội dung");

    // Phản hồi không được ủy quyền
    UnauthorizedFeedback UNAUTHORIZED = new UnauthorizedFeedback("Chưa được ủy quyền");

    // Phản hồi từ chối truy cập
    UnauthorizedFeedback ACCESS_DENIED = new UnauthorizedFeedback("Bạn không có quyền truy cập, từ chối truy cập");

    // Phản hồi tài khoản bị vô hiệu hóa
    UnauthorizedFeedback ACCOUNT_DISABLED = new UnauthorizedFeedback("Tài khoản này đã bị vô hiệu hóa");

    // Phản hồi giới hạn điểm cuối tài khoản
    UnauthorizedFeedback ACCOUNT_ENDPOINT_LIMITED = new UnauthorizedFeedback("Bạn đã đăng nhập từ thiết bị khác, vui lòng thoát khỏi thiết bị khác trước");

    // Phản hồi tài khoản đã hết hạn
    UnauthorizedFeedback ACCOUNT_EXPIRED = new UnauthorizedFeedback("Tài khoản này đã hết hạn");

    // Phản hồi tài khoản bị khóa
    UnauthorizedFeedback ACCOUNT_LOCKED = new UnauthorizedFeedback("Tài khoản này đã bị khóa");

    // Phản hồi thông tin đăng nhập không hợp lệ
    UnauthorizedFeedback BAD_CREDENTIALS = new UnauthorizedFeedback("Tên đăng nhập hoặc mật khẩu không chính xác");

    // Phản hồi thông tin đăng nhập đã hết hạn
    UnauthorizedFeedback CREDENTIALS_EXPIRED = new UnauthorizedFeedback("Thông tin đăng nhập của tài khoản đã hết hạn");

    // Phản hồi khách hàng không hợp lệ
    UnauthorizedFeedback INVALID_CLIENT = new UnauthorizedFeedback("Xác thực khách hàng không thành công hoặc cơ sở dữ liệu chưa được khởi tạo");

    // Phản hồi mã thông báo không hợp lệ
    UnauthorizedFeedback INVALID_TOKEN = new UnauthorizedFeedback("Mã thông báo truy cập cung cấp đã hết hạn, bị thu hồi, định dạng không đúng hoặc không hợp lệ");

    // Phản hồi cấp quyền không hợp lệ
    UnauthorizedFeedback INVALID_GRANT = new UnauthorizedFeedback("Quyền cấp phép hoặc mã thông báo làm mới cung cấp không hợp lệ, đã hết hạn hoặc bị thu hồi");

    // Phản hồi khách hàng không được ủy quyền
    UnauthorizedFeedback UNAUTHORIZED_CLIENT = new UnauthorizedFeedback("Khách hàng không có quyền sử dụng phương thức này để yêu cầu mã cấp phép hoặc mã thông báo truy cập");

    // Phản hồi tên đăng nhập không tìm thấy
    UnauthorizedFeedback USERNAME_NOT_FOUND = new UnauthorizedFeedback("Tên đăng nhập hoặc mật khẩu không chính xác");

    // Phản hồi phiên làm việc đã hết hạn
    UnauthorizedFeedback SESSION_EXPIRED = new UnauthorizedFeedback("Phiên làm việc đã hết hạn, vui lòng làm mới trang trước khi sử dụng");

    // Phản hồi yêu cầu bị cấm
    ForbiddenFeedback FORBIDDEN = new ForbiddenFeedback("Yêu cầu bị cấm");

    // Phản hồi phạm vi không đủ
    ForbiddenFeedback INSUFFICIENT_SCOPE = new ForbiddenFeedback("Quyền hạn của TOKEN không đủ, bạn cần quyền cao hơn");

    // Phản hồi yêu cầu có nguy cơ SQL injection
    ForbiddenFeedback SQL_INJECTION_REQUEST = new ForbiddenFeedback("Yêu cầu nghi ngờ SQL injection");

    // Phản hồi không tìm thấy tài nguyên
    NotFoundFeedback NO_RESOURCE_FOUND_EXCEPTION = new NotFoundFeedback("Tài nguyên được chỉ định không tìm thấy");

    // Phản hồi phương thức không được phép
    MethodNotAllowedFeedback METHOD_NOT_ALLOWED = new MethodNotAllowedFeedback("Phương thức không được phép");

    // Phản hồi phương thức yêu cầu HTTP không được hỗ trợ
    MethodNotAllowedFeedback HTTP_REQUEST_METHOD_NOT_SUPPORTED = new MethodNotAllowedFeedback("Loại phương thức yêu cầu không được hỗ trợ");

    // Phản hồi yêu cầu không chấp nhận được
    NotAcceptableFeedback NOT_ACCEPTABLE = new NotAcceptableFeedback("Yêu cầu không chấp nhận được");

    // Phản hồi loại cấp phép không được hỗ trợ
    NotAcceptableFeedback UNSUPPORTED_GRANT_TYPE = new NotAcceptableFeedback("Máy chủ cấp phép không hỗ trợ loại cấp phép");

    // Phản hồi loại phản hồi không được hỗ trợ
    NotAcceptableFeedback UNSUPPORTED_RESPONSE_TYPE = new NotAcceptableFeedback("Máy chủ cấp phép không hỗ trợ phương pháp này để lấy mã cấp phép hoặc mã thông báo truy cập");

    // Phản hồi loại mã thông báo không được hỗ trợ
    NotAcceptableFeedback UNSUPPORTED_TOKEN_TYPE = new NotAcceptableFeedback("Máy chủ cấp phép không hỗ trợ thu hồi loại mã thông báo cung cấp");

    // Phản hồi điều kiện tiên quyết không hợp lệ
    PreconditionFailedFeedback PRECONDITION_FAILED = new PreconditionFailedFeedback("Điều kiện tiên quyết của thông tin yêu cầu không hợp lệ");

    // Phản hồi URI chuyển hướng không hợp lệ
    PreconditionFailedFeedback INVALID_REDIRECT_URI = new PreconditionFailedFeedback("Giá trị URI chuyển hướng OAuth2 không hợp lệ");

    // Phản hồi yêu cầu không hợp lệ
    PreconditionFailedFeedback INVALID_REQUEST = new PreconditionFailedFeedback("Yêu cầu không hợp lệ, tham số sử dụng không đúng hoặc không hợp lệ");

    // Phản hồi phạm vi không hợp lệ
    PreconditionFailedFeedback INVALID_SCOPE = new PreconditionFailedFeedback("Phạm vi cấp phép không hợp lệ");

    // Phản hồi tham số phương thức không hợp lệ
    PreconditionFailedFeedback METHOD_ARGUMENT_NOT_VALID = new PreconditionFailedFeedback("Kiểm tra tham số của API thất bại, tham số sử dụng không đúng hoặc không nhận được tham số");

    // Phản hồi loại phương tiện không được hỗ trợ
    UnsupportedMediaTypeFeedback UNSUPPORTED_MEDIA_TYPE = new UnsupportedMediaTypeFeedback("Máy chủ không thể xử lý định dạng phương tiện đính kèm yêu cầu");

    // Phản hồi loại phương tiện HTTP không chấp nhận được
    UnsupportedMediaTypeFeedback HTTP_MEDIA_TYPE_NOT_ACCEPTABLE = new UnsupportedMediaTypeFeedback("Loại Media không được hỗ trợ");

    // Phản hồi lỗi máy chủ nội bộ
    InternalServerErrorFeedback INTERNAL_SERVER_ERROR = new InternalServerErrorFeedback("Lỗi máy chủ nội bộ, không thể hoàn thành yêu cầu");

    // Phản hồi lỗi máy chủ
    InternalServerErrorFeedback SERVER_ERROR = new InternalServerErrorFeedback("Máy chủ cấp phép gặp phải tình huống không mong muốn, không thể đáp ứng yêu cầu");

    // Phản hồi lỗi không đọc được thông điệp HTTP
    InternalServerErrorFeedback HTTP_MESSAGE_NOT_READABLE_EXCEPTION = new InternalServerErrorFeedback("Lỗi khi phân tích chuỗi JSON thành thực thể");

    // Phản hồi lỗi tham số không hợp lệ
    InternalServerErrorFeedback ILLEGAL_ARGUMENT_EXCEPTION = new InternalServerErrorFeedback("Lỗi tham số không hợp lệ, vui lòng kiểm tra lại cách sử dụng tham số");

    // Phản hồi lỗi IO
    InternalServerErrorFeedback IO_EXCEPTION = new InternalServerErrorFeedback("Lỗi IO");

    // Phản hồi thiếu tham số yêu cầu servlet
    InternalServerErrorFeedback MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = new InternalServerErrorFeedback("Sử dụng tham số API không đúng hoặc thiếu tham số cần thiết, vui lòng kiểm tra tài liệu API");

    // Phản hồi lỗi con trỏ null
    InternalServerErrorFeedback NULL_POINTER_EXCEPTION = new InternalServerErrorFeedback("Có giá trị null trong quá trình thực thi mã phía sau");

    // Phản hồi lỗi không tương thích kiểu
    InternalServerErrorFeedback TYPE_MISMATCH_EXCEPTION = new InternalServerErrorFeedback("Không tương thích kiểu");

    // Phản hồi lỗi lấy đối tượng từ pool
    InternalServerErrorFeedback BORROW_OBJECT_FROM_POOL_ERROR_EXCEPTION = new InternalServerErrorFeedback("Lỗi khi lấy đối tượng từ pool");

    // Phản hồi chưa được triển khai
    NotImplementedFeedback NOT_IMPLEMENTED = new NotImplementedFeedback("Máy chủ không hỗ trợ chức năng yêu cầu, không thể hoàn thành yêu cầu");

    // Phản hồi lỗi chưa được ghi nhận
    NotImplementedFeedback DISCOVERED_UNRECORDED_ERROR_EXCEPTION = new NotImplementedFeedback("Phát hiện lỗi chưa được ghi nhận, khuyến nghị thêm vào hệ thống lỗi hoặc báo lỗi cho tác giả gốc");

    // Phản hồi giá trị thuộc tính không được thiết lập
    NotImplementedFeedback PROPERTY_VALUE_IS_NOT_SET_EXCEPTION = new NotImplementedFeedback("Giá trị thuộc tính cần thiết chưa được thiết lập");

    // Phản hồi định dạng URL không chính xác
    NotImplementedFeedback URL_FORMAT_INCORRECT_EXCEPTION = new NotImplementedFeedback("Định dạng URL không chính xác hoặc thiếu tiêu đề giao thức HTTP");

    // Phản hồi khóa đối xứng không hợp lệ
    NotImplementedFeedback ILLEGAL_SYMMETRIC_KEY = new NotImplementedFeedback("Khóa mã hóa AES tĩnh không hợp lệ");

    // Phản hồi dịch vụ không có sẵn
    ServiceUnavailableFeedback SERVICE_UNAVAILABLE = new ServiceUnavailableFeedback("Dịch vụ không có sẵn");

    // Phản hồi đánh cắp cookie
    ServiceUnavailableFeedback COOKIE_THEFT = new ServiceUnavailableFeedback("Thông tin cookie không an toàn");

    // Phản hồi cookie không hợp lệ
    ServiceUnavailableFeedback INVALID_COOKIE = new ServiceUnavailableFeedback("Thông tin cookie không hợp lệ");

    // Phản hồi nhà cung cấp không tìm thấy
    ServiceUnavailableFeedback PROVIDER_NOT_FOUND = new ServiceUnavailableFeedback("Lỗi cấu hình logic mã của máy chủ cấp phép");

    // Phản hồi tạm thời không có sẵn
    ServiceUnavailableFeedback TEMPORARILY_UNAVAILABLE = new ServiceUnavailableFeedback("Do máy chủ tạm thời quá tải hoặc bảo trì, máy chủ cấp phép hiện không thể xử lý yêu cầu");

    // Phản hồi lỗi tìm kiếm vị trí IP
    ServiceUnavailableFeedback SEARCH_IP_LOCATION = new ServiceUnavailableFeedback("Lỗi khi đọc vị trí IP, máy chủ hiện không thể xử lý yêu cầu");

    // Phản hồi lỗi yêu cầu API mở
    ServiceUnavailableFeedback OPEN_API_REQUEST_FAILURE = new ServiceUnavailableFeedback("Yêu cầu gọi API cơ sở hạ tầng thất bại");

    // Phản hồi lỗi giao dịch dữ liệu
    CustomizeFeedback TRANSACTION_ROLLBACK = new CustomizeFeedback("Xử lý giao dịch dữ liệu thất bại, dữ liệu đã được hoàn tác", 6);

    // Phản hồi lỗi cú pháp SQL
    CustomizeFeedback BAD_SQL_GRAMMAR = new CustomizeFeedback("Lỗi cú pháp SQL cơ bản, kiểm tra xem SQL có chạy đúng không hoặc tên trường có chính xác không", 6);

    // Phản hồi vi phạm tính toàn vẹn dữ liệu
    CustomizeFeedback DATA_INTEGRITY_VIOLATION = new CustomizeFeedback("Dữ liệu đang được tham chiếu bởi dữ liệu khác, vui lòng xóa mối quan hệ tham chiếu trước khi thực hiện thao tác xóa dữ liệu", 6);

    // Phản hồi lệnh không hợp lệ trong pipeline Redis
    CustomizeFeedback PIPELINE_INVALID_COMMANDS = new CustomizeFeedback("Pipeline Redis chứa một hoặc nhiều lệnh không hợp lệ", 7);
}

