package vi.legend.assistant.core.utils.http;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;

import java.util.List;

/**
 * Lớp HeaderUtils cung cấp các phương thức tiện ích để làm việc với các tiêu đề (header) trong yêu cầu HTTP.
 * Lớp này hỗ trợ lấy thông tin từ các tiêu đề cụ thể như Session ID, Tenant ID, Authorization, Cookie, v.v.
 */
public class HeaderUtils {

    // Các hằng số đại diện cho các tiêu đề tùy chỉnh được sử dụng trong ứng dụng.
    public static final String X_SANZEE_SESSION_ID = "X-Sanzee-Session-id";
    public static final String X_SANZEE_FROM_IN = "X-Sanzee-From-In";
    public static final String X_SANZEE_TENANT_ID = "X-Sanzee-Tenant-Id";
    public static final String X_SANZEE_OPEN_ID = "X-Sanzee-Open-Id";

    /**
     * Constructor mặc định.
     */
    public HeaderUtils() {
    }

    /**
     * Lấy danh sách các giá trị của một tiêu đề từ HttpHeaders.
     *
     * @param httpHeaders HttpHeaders chứa các tiêu đề.
     * @param name Tên của tiêu đề cần lấy giá trị.
     * @return Danh sách các giá trị của tiêu đề.
     */
    public static List<String> getHeaders(HttpHeaders httpHeaders, String name) {
        return httpHeaders.get(name);
    }

    /**
     * Lấy danh sách các giá trị của một tiêu đề từ yêu cầu ServerHttpRequest.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa các tiêu đề.
     * @param name Tên của tiêu đề cần lấy giá trị.
     * @return Danh sách các giá trị của tiêu đề.
     */
    public static List<String> getHeaders(ServerHttpRequest serverHttpRequest, String name) {
        return getHeaders(serverHttpRequest.getHeaders(), name);
    }

    /**
     * Lấy giá trị đầu tiên của một tiêu đề từ HttpHeaders.
     *
     * @param httpHeaders HttpHeaders chứa các tiêu đề.
     * @param name Tên của tiêu đề cần lấy giá trị.
     * @return Giá trị đầu tiên của tiêu đề, hoặc null nếu không có giá trị nào.
     */
    public static String getHeader(HttpHeaders httpHeaders, String name) {
        List<String> values = getHeaders(httpHeaders, name);
        return CollectionUtils.isNotEmpty(values) ? values.get(0) : null;
    }

    /**
     * Lấy giá trị đầu tiên của một tiêu đề từ yêu cầu ServerHttpRequest.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa các tiêu đề.
     * @param name Tên của tiêu đề cần lấy giá trị.
     * @return Giá trị đầu tiên của tiêu đề, hoặc null nếu không có giá trị nào.
     */
    public static String getHeader(ServerHttpRequest serverHttpRequest, String name) {
        return getHeader(serverHttpRequest.getHeaders(), name);
    }

    /**
     * Lấy giá trị của một tiêu đề từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa các tiêu đề.
     * @param name Tên của tiêu đề cần lấy giá trị.
     * @return Giá trị của tiêu đề, hoặc null nếu không có giá trị nào.
     */
    public static String getHeader(HttpServletRequest httpServletRequest, String name) {
        return httpServletRequest.getHeader(name);
    }

    /**
     * Kiểm tra xem một tiêu đề có tồn tại trong HttpHeaders hay không.
     *
     * @param httpHeaders HttpHeaders chứa các tiêu đề.
     * @param name Tên của tiêu đề cần kiểm tra.
     * @return True nếu tiêu đề tồn tại, false nếu không tồn tại.
     */
    public static boolean hasHeader(HttpHeaders httpHeaders, String name) {
        return httpHeaders.containsKey(name);
    }

    /**
     * Kiểm tra xem một tiêu đề có tồn tại trong yêu cầu HttpServletRequest hay không.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa các tiêu đề.
     * @param name Tên của tiêu đề cần kiểm tra.
     * @return True nếu tiêu đề tồn tại và không trống, false nếu không tồn tại hoặc trống.
     */
    public static Boolean hasHeader(HttpServletRequest httpServletRequest, String name) {
        return StringUtils.isNotBlank(getHeader(httpServletRequest, name));
    }

    /**
     * Kiểm tra xem một tiêu đề có tồn tại trong yêu cầu ServerHttpRequest hay không.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa các tiêu đề.
     * @param name Tên của tiêu đề cần kiểm tra.
     * @return True nếu tiêu đề tồn tại, false nếu không tồn tại.
     */
    public static Boolean hasHeader(ServerHttpRequest serverHttpRequest, String name) {
        return hasHeader(serverHttpRequest.getHeaders(), name);
    }

    /**
     * Lấy giá trị của tiêu đề "X-Herodotus-Session-id" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "X-Herodotus-Session-id", hoặc null nếu không có giá trị nào.
     */
    public static String getHerodotusSessionId(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, X_SANZEE_SESSION_ID);
    }

    /**
     * Lấy giá trị của tiêu đề "X-Herodotus-Session-id" từ yêu cầu ServerHttpRequest.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "X-Herodotus-Session-id", hoặc null nếu không có giá trị nào.
     */
    public static String getHerodotusSessionId(ServerHttpRequest serverHttpRequest) {
        return getHeader(serverHttpRequest, X_SANZEE_SESSION_ID);
    }

    /**
     * Lấy giá trị của tiêu đề "X-Herodotus-Session-id" từ HttpInputMessage.
     *
     * @param httpInputMessage HttpInputMessage chứa tiêu đề.
     * @return Giá trị của tiêu đề "X-Herodotus-Session-id", hoặc null nếu không có giá trị nào.
     */
    public static String getHerodotusSessionId(HttpInputMessage httpInputMessage) {
        return getHeader(httpInputMessage.getHeaders(), X_SANZEE_SESSION_ID);
    }

    /**
     * Lấy giá trị của tiêu đề "X-Herodotus-Tenant-Id" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "X-Herodotus-Tenant-Id", hoặc null nếu không có giá trị nào.
     */
    public static String getHerodotusTenantId(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, X_SANZEE_TENANT_ID);
    }

    /**
     * Lấy giá trị của tiêu đề "X-Herodotus-From-In" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "X-Herodotus-From-In", hoặc null nếu không có giá trị nào.
     */
    public static String getHerodotusFromIn(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, X_SANZEE_FROM_IN);
    }

    /**
     * Kiểm tra xem tiêu đề "X-Herodotus-Session-id" có tồn tại trong yêu cầu HttpServletRequest hay không.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return True nếu tiêu đề tồn tại, false nếu không tồn tại.
     */
    public static boolean hasHerodotusSessionIdHeader(HttpServletRequest httpServletRequest) {
        return hasHeader(httpServletRequest, X_SANZEE_SESSION_ID);
    }

    /**
     * Kiểm tra xem tiêu đề "X-Herodotus-Session-id" có tồn tại trong yêu cầu ServerHttpRequest hay không.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa tiêu đề.
     * @return True nếu tiêu đề tồn tại, false nếu không tồn tại.
     */
    public static boolean hasHerodotusSessionIdHeader(ServerHttpRequest serverHttpRequest) {
        return hasHeader(serverHttpRequest, X_SANZEE_SESSION_ID);
    }

    /**
     * Kiểm tra xem tiêu đề "X-Herodotus-Session-id" có tồn tại trong HttpInputMessage hay không.
     *
     * @param httpInputMessage HttpInputMessage chứa tiêu đề.
     * @return True nếu tiêu đề tồn tại, false nếu không tồn tại.
     */
    public static boolean hasHerodotusSessionIdHeader(HttpInputMessage httpInputMessage) {
        return hasHeader(httpInputMessage.getHeaders(), X_SANZEE_SESSION_ID);
    }

    /**
     * Lấy giá trị của tiêu đề "Cookie" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "Cookie", hoặc null nếu không có giá trị nào.
     */
    public static String getCookie(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, HttpHeaders.COOKIE);
    }

    /**
     * Lấy giá trị của tiêu đề "Cookie" từ yêu cầu ServerHttpRequest.
     *
     * @param serverHttpRequest Yêu cầu ServerHttpRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "Cookie", hoặc null nếu không có giá trị nào.
     */
    public static String getCookie(ServerHttpRequest serverHttpRequest) {
        return getHeader(serverHttpRequest, HttpHeaders.COOKIE);
    }

    /**
     * Lấy giá trị của tiêu đề "Cookie" từ HttpInputMessage.
     *
     * @param httpInputMessage HttpInputMessage chứa tiêu đề.
     * @return Giá trị của tiêu đề "Cookie", hoặc null nếu không có giá trị nào.
     */
    public static String getCookie(HttpInputMessage httpInputMessage) {
        return getHeader(httpInputMessage.getHeaders(), HttpHeaders.COOKIE);
    }

    /**
     * Lấy giá trị của tiêu đề "Authorization" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "Authorization", hoặc null nếu không có giá trị nào.
     */
    public static String getAuthorization(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, HttpHeaders.AUTHORIZATION);
    }

    /**
     * Lấy Bearer token từ tiêu đề "Authorization" trong yêu cầu HttpServletRequest.
     *
     * @param request Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Bearer token nếu tiêu đề có định dạng "Bearer ", hoặc null nếu không có.
     */
    public static String getBearerToken(HttpServletRequest request) {
        String header = getAuthorization(request);
        return StringUtils.isNotBlank(header) && StringUtils.startsWith(header, "Bearer ") ?
                StringUtils.remove(header, "Bearer ")
                : null;
    }

    /**
     * Lấy giá trị của tiêu đề "Origin" từ yêu cầu HttpServletRequest.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa tiêu đề.
     * @return Giá trị của tiêu đề "Origin", hoặc null nếu không có giá trị nào.
     */
    public static String getOrigin(HttpServletRequest httpServletRequest) {
        return getHeader(httpServletRequest, HttpHeaders.ORIGIN);
    }
}

