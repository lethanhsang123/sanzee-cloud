package utils.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;

/**
 * Lớp SessionUtils cung cấp các phương thức tiện ích để quản lý và thao tác với Session trong ứng dụng web.
 * Lớp này hỗ trợ lấy và phân tích Session ID từ các yêu cầu HTTP và tiêu đề (header).
 */
public class SessionUtils {

    // Mảng hằng số chứa các tên tiêu đề có thể chứa Session ID.
    private static final String[] SESSION_IDS = new String[]{"JSESSIONID, SESSION"};

    /**
     * Constructor mặc định.
     */
    public SessionUtils() {
    }

    /**
     * Lấy HttpSession từ yêu cầu HttpServletRequest, với tùy chọn tạo mới nếu chưa tồn tại.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session.
     * @param create True nếu muốn tạo session mới khi chưa tồn tại, false nếu không.
     * @return HttpSession nếu tồn tại hoặc được tạo mới, hoặc null nếu không.
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest, boolean create) {
        return httpServletRequest.getSession(create);
    }

    /**
     * Lấy HttpSession từ yêu cầu HttpServletRequest mà không tạo mới nếu chưa tồn tại.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session.
     * @return HttpSession nếu tồn tại, hoặc null nếu không.
     */
    public static HttpSession getSession(HttpServletRequest httpServletRequest) {
        return getSession(httpServletRequest, false);
    }

    /**
     * Lấy Session ID từ yêu cầu HttpServletRequest, với tùy chọn tạo mới session nếu cần.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session.
     * @param create True nếu muốn tạo session mới khi chưa tồn tại, false nếu không.
     * @return Session ID nếu tồn tại, hoặc null nếu không.
     */
    public static String getSessionId(HttpServletRequest httpServletRequest, boolean create) {
        HttpSession httpSession = getSession(httpServletRequest, create);
        return ObjectUtils.isNotEmpty(httpSession) ? httpSession.getId() : null;
    }

    /**
     * Lấy Session ID từ yêu cầu HttpServletRequest mà không tạo mới nếu chưa tồn tại.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session.
     * @return Session ID nếu tồn tại, hoặc null nếu không.
     */
    public static String getSessionId(HttpServletRequest httpServletRequest) {
        return getSessionId(httpServletRequest, false);
    }

    /**
     * Lấy Session ID từ tiêu đề của HttpInputMessage.
     *
     * @param httpInputMessage HttpInputMessage chứa tiêu đề.
     * @return Session ID nếu tìm thấy, hoặc null nếu không.
     */
    public static String getSessionIdFromHeader(HttpInputMessage httpInputMessage) {
        return CookieUtils.getAnyFromHeader(httpInputMessage, SESSION_IDS);
    }

    /**
     * Lấy Session ID từ tiêu đề của ServerHttpRequest.
     *
     * @param serverHttpRequest ServerHttpRequest chứa tiêu đề.
     * @return Session ID nếu tìm thấy, hoặc null nếu không.
     */
    public static String getSessionIdFromHeader(ServerHttpRequest serverHttpRequest) {
        return CookieUtils.getAnyFromHeader(serverHttpRequest, SESSION_IDS);
    }

    /**
     * Phân tích và lấy Session ID từ yêu cầu HttpServletRequest.
     * Nếu Session ID không có sẵn trong session, nó sẽ được lấy từ tiêu đề X-Herodotus-Session-id.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session hoặc tiêu đề.
     * @return Session ID nếu tìm thấy, hoặc null nếu không.
     */
    public static String analyseSessionId(HttpServletRequest httpServletRequest) {
        String sessionId = getSessionId(httpServletRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSessionId(httpServletRequest);
        }

        return sessionId;
    }

    /**
     * Phân tích và lấy Session ID từ ServerHttpRequest.
     * Nếu Session ID không có sẵn trong tiêu đề, nó sẽ được lấy từ tiêu đề X-Herodotus-Session-id.
     *
     * @param serverHttpRequest ServerHttpRequest chứa tiêu đề.
     * @return Session ID nếu tìm thấy, hoặc null nếu không.
     */
    public static String analyseSessionId(ServerHttpRequest serverHttpRequest) {
        String sessionId = getSessionIdFromHeader(serverHttpRequest);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSessionId(serverHttpRequest);
        }

        return sessionId;
    }

    /**
     * Phân tích và lấy Session ID từ HttpInputMessage.
     * Nếu Session ID không có sẵn trong tiêu đề, nó sẽ được lấy từ tiêu đề X-Herodotus-Session-id.
     *
     * @param httpInputMessage HttpInputMessage chứa tiêu đề.
     * @return Session ID nếu tìm thấy, hoặc null nếu không.
     */
    public static String analyseSessionId(HttpInputMessage httpInputMessage) {
        String sessionId = getSessionIdFromHeader(httpInputMessage);
        if (StringUtils.isBlank(sessionId)) {
            sessionId = HeaderUtils.getHerodotusSessionId(httpInputMessage);
        }

        return sessionId;
    }

    /**
     * Kiểm tra xem mã hóa (encryption) có được bật cho phiên làm việc (session) trong yêu cầu HttpServletRequest hay không.
     *
     * @param httpServletRequest Yêu cầu HttpServletRequest chứa session.
     * @param sessionId Session ID cần kiểm tra.
     * @return True nếu mã hóa được bật, false nếu không.
     */
    public static boolean isCryptoEnabled(HttpServletRequest httpServletRequest, String sessionId) {
        return HeaderUtils.hasHerodotusSessionIdHeader(httpServletRequest) && StringUtils.isNotBlank(sessionId);
    }

    /**
     * Kiểm tra xem mã hóa (encryption) có được bật cho phiên làm việc (session) trong HttpInputMessage hay không.
     *
     * @param httpInputMessage HttpInputMessage chứa session.
     * @param sessionId Session ID cần kiểm tra.
     * @return True nếu mã hóa được bật, false nếu không.
     */
    public static boolean isCryptoEnabled(HttpInputMessage httpInputMessage, String sessionId) {
        return HeaderUtils.hasHerodotusSessionIdHeader(httpInputMessage) && StringUtils.isNotBlank(sessionId);
    }
}

