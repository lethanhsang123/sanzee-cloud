package utils.http;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lớp CookieUtils cung cấp các phương thức tiện ích để làm việc với cookie trong các yêu cầu HTTP.
 */
public class CookieUtils {

    /**
     * Constructor mặc định.
     */
    public CookieUtils() {
    }

    /**
     * Chuyển đổi chuỗi cookie thô thành một Map chứa các cặp khóa-giá trị.
     *
     * @param cookie Chuỗi cookie thô.
     * @return Map chứa các cặp khóa-giá trị từ chuỗi cookie.
     */
    private static Map<String, String> rawCookieToMap(String cookie) {
        return StringUtils.isNotBlank(cookie) ? (Map)Stream.of(cookie.split("; ")).map((pair) -> {
            return pair.split("=");
        }).collect(Collectors.toMap((kv) -> {
            return kv[0];
        }, (kv) -> {
            return kv[1];
        })) : Collections.emptyMap();
    }

    /**
     * Lấy danh sách giá trị của các tên cookie từ chuỗi cookie thô.
     *
     * @param cookie Chuỗi cookie thô.
     * @param name Danh sách tên cookie cần lấy giá trị.
     * @return Danh sách giá trị tương ứng với tên cookie.
     */
    public static List<String> get(String cookie, String... name) {
        Map<String, String> cookies = rawCookieToMap(cookie);
        return Stream.of(name).map(cookies::get).toList();
    }

    /**
     * Lấy giá trị của một trong các tên cookie từ chuỗi cookie thô.
     *
     * @param cookie Chuỗi cookie thô.
     * @param name Danh sách tên cookie cần lấy giá trị.
     * @return Giá trị của tên cookie đầu tiên tìm thấy, hoặc null nếu không có.
     */
    public static String getAny(String cookie, String... name) {
        List<String> result = get(cookie, name);
        return CollectionUtils.isNotEmpty(result) ? result.get(0) : null;
    }

    /**
     * Lấy giá trị của một tên cookie từ chuỗi cookie thô.
     *
     * @param cookie Chuỗi cookie thô.
     * @param name Tên cookie cần lấy giá trị.
     * @return Giá trị của cookie hoặc null nếu không tìm thấy.
     */
    public static String get(String cookie, String name) {
        Map<String, String> cookies = rawCookieToMap(cookie);
        return cookies.get(name);
    }

    /**
     * Lấy một đối tượng Cookie từ yêu cầu HTTP bằng tên cookie.
     *
     * @param httpServletRequest Yêu cầu HTTP.
     * @param name Tên cookie cần lấy.
     * @return Đối tượng Cookie hoặc null nếu không tìm thấy.
     */
    public static Cookie get(HttpServletRequest httpServletRequest, String name) {
        return WebUtils.getCookie(httpServletRequest, name);
    }

    /**
     * Lấy giá trị của một cookie từ yêu cầu HTTP.
     *
     * @param httpServletRequest Yêu cầu HTTP.
     * @param name Tên cookie cần lấy giá trị.
     * @return Giá trị của cookie hoặc null nếu không tìm thấy.
     */
    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = get(httpServletRequest, name);
        return ObjectUtils.isNotEmpty(cookie) ? cookie.getValue() : null;
    }

    /**
     * Lấy giá trị của một cookie từ tiêu đề của yêu cầu HTTP.
     *
     * @param httpServletRequest Yêu cầu HTTP.
     * @param name Tên cookie cần lấy giá trị.
     * @return Giá trị của cookie hoặc null nếu không tìm thấy.
     */
    public static String getFromHeader(HttpServletRequest httpServletRequest, String name) {
        String cookie = HeaderUtils.getCookie(httpServletRequest);
        return get(cookie, name);
    }

    /**
     * Lấy giá trị của một cookie từ tiêu đề của yêu cầu HTTP.
     *
     * @param serverHttpRequest Yêu cầu HTTP của server.
     * @param name Tên cookie cần lấy giá trị.
     * @return Giá trị của cookie hoặc null nếu không tìm thấy.
     */
    public static String getFromHeader(ServerHttpRequest serverHttpRequest, String name) {
        String cookie = HeaderUtils.getCookie(serverHttpRequest);
        return get(cookie, name);
    }

    /**
     * Lấy giá trị của một cookie từ tiêu đề của HTTP Input Message.
     *
     * @param httpInputMessage HTTP Input Message.
     * @param name Tên cookie cần lấy giá trị.
     * @return Giá trị của cookie hoặc null nếu không tìm thấy.
     */
    public static String getFromHeader(HttpInputMessage httpInputMessage, String name) {
        String cookie = HeaderUtils.getCookie(httpInputMessage);
        return get(cookie, name);
    }

    /**
     * Lấy giá trị của một trong các tên cookie từ tiêu đề của yêu cầu HTTP.
     *
     * @param httpServletRequest Yêu cầu HTTP.
     * @param name Danh sách tên cookie cần lấy giá trị.
     * @return Giá trị của tên cookie đầu tiên tìm thấy, hoặc null nếu không có.
     */
    public static String getAnyFromHeader(HttpServletRequest httpServletRequest, String... name) {
        String cookie = HeaderUtils.getCookie(httpServletRequest);
        return getAny(cookie, name);
    }

    /**
     * Lấy giá trị của một trong các tên cookie từ tiêu đề của yêu cầu HTTP.
     *
     * @param serverHttpRequest Yêu cầu HTTP của server.
     * @param name Danh sách tên cookie cần lấy giá trị.
     * @return Giá trị của tên cookie đầu tiên tìm thấy, hoặc null nếu không có.
     */
    public static String getAnyFromHeader(ServerHttpRequest serverHttpRequest, String... name) {
        String cookie = HeaderUtils.getCookie(serverHttpRequest);
        return getAny(cookie, name);
    }

    /**
     * Lấy giá trị của một trong các tên cookie từ tiêu đề của HTTP Input Message.
     *
     * @param httpInputMessage HTTP Input Message.
     * @param name Danh sách tên cookie cần lấy giá trị.
     * @return Giá trị của tên cookie đầu tiên tìm thấy, hoặc null nếu không có.
     */
    public static String getAnyFromHeader(HttpInputMessage httpInputMessage, String... name) {
        String cookie = HeaderUtils.getCookie(httpInputMessage);
        return getAny(cookie, name);
    }

    /**
     * Xóa cookie khỏi phản hồi HTTP.
     *
     * @param response Phản hồi HTTP.
     * @param key Tên cookie cần xóa.
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * Thiết lập cookie trong phản hồi HTTP.
     *
     * @param response Phản hồi HTTP.
     * @param name Tên cookie cần thiết lập.
     * @param value Giá trị của cookie.
     * @param maxAgeInSeconds Thời gian sống của cookie tính bằng giây.
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
