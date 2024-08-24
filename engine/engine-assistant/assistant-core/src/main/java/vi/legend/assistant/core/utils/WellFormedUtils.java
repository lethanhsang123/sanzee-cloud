package vi.legend.assistant.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import vi.legend.assistant.core.enums.Protocol;
import vi.legend.assistant.core.exception.properties.PropertyValueIsNotSetException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;


/**
 * Lớp WellFormedUtils cung cấp các phương thức tiện ích để làm việc với URL và địa chỉ mạng.
 */
public class WellFormedUtils {

    // Tham chiếu kiểu dữ liệu Map<String, Object> với Generic Type Reference.
    public static final ParameterizedTypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new ParameterizedTypeReference<Map<String, Object>>() {
    };

    // Logger để ghi lại thông tin nhật ký của lớp.
    private static final Logger log = LoggerFactory.getLogger(WellFormedUtils.class);

    /**
     * Constructor mặc định.
     */
    public WellFormedUtils() {
    }

    /**
     * Đảm bảo URL kết thúc bằng dấu gạch chéo ('/').
     *
     * @param url Chuỗi URL cần kiểm tra.
     * @return URL đã được chuẩn hóa.
     */
    public static String url(String url) {
        return StringUtils.endsWith(url, "/") ? url : url + "/";
    }

    /**
     * Đặt ID cha về giá trị mặc định nếu nó là chuỗi rỗng hoặc null.
     *
     * @param parentId ID của phần tử cha.
     * @return ID của phần tử cha hoặc "0" nếu giá trị bị thiếu.
     */
    public static String parentId(String parentId) {
        return StringUtils.isBlank(parentId) ? "0" : parentId;
    }

    /**
     * Chuyển đổi địa chỉ thành URI với định dạng protocol và tùy chọn thêm dấu gạch chéo phía cuối.
     *
     * @param address Địa chỉ cần chuyển đổi.
     * @param protocol Protocol cần thêm vào nếu địa chỉ không bắt đầu bằng nó.
     * @param endWithForwardSlash True nếu địa chỉ cần kết thúc bằng dấu gạch chéo.
     * @return URI đã được chuẩn hóa.
     */
    public static String addressToUri(String address, Protocol protocol, boolean endWithForwardSlash) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!StringUtils.startsWith(address, protocol.getFormat())) {
            stringBuilder.append(protocol.getFormat());
        }

        if (endWithForwardSlash) {
            stringBuilder.append(url(address));
        } else {
            stringBuilder.append(address);
        }

        return stringBuilder.toString();
    }

    /**
     * Chuyển đổi địa chỉ thành URI với protocol HTTP và tùy chọn thêm dấu gạch chéo phía cuối.
     *
     * @param address Địa chỉ cần chuyển đổi.
     * @param endWithForwardSlash True nếu địa chỉ cần kết thúc bằng dấu gạch chéo.
     * @return URI đã được chuẩn hóa.
     */
    public static String addressToUri(String address, boolean endWithForwardSlash) {
        return addressToUri(address, Protocol.HTTP, endWithForwardSlash);
    }

    /**
     * Chuyển đổi địa chỉ thành URI với protocol HTTP và không thêm dấu gạch chéo phía cuối.
     *
     * @param address Địa chỉ cần chuyển đổi.
     * @return URI đã được chuẩn hóa.
     */
    public static String addressToUri(String address) {
        return addressToUri(address, false);
    }

    /**
     * Lấy địa chỉ IP của máy chủ hiện tại.
     *
     * @return Địa chỉ IP của máy chủ hoặc null nếu không thể lấy được.
     */
    public static String getHostAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException var2) {
            log.error("[Herodotus] |- Get host address error: {}", var2.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Xây dựng URI của dịch vụ với tên dịch vụ và URI của gateway.
     *
     * @param serviceUri URI của dịch vụ.
     * @param serviceName Tên dịch vụ.
     * @param gatewayServiceUri URI của gateway.
     * @param abbreviation Ký hiệu của dịch vụ.
     * @return URI của dịch vụ hoặc ném lỗi nếu các thông tin cần thiết không được cung cấp.
     */
    public static String serviceUri(String serviceUri, String serviceName, String gatewayServiceUri, String abbreviation) {
        if (StringUtils.isNotBlank(serviceUri)) {
            return serviceUri;
        } else if (StringUtils.isBlank(serviceName)) {
            log.error("[Herodotus] |- Property [{} Service Name] is not set or property format is incorrect!", abbreviation);
            throw new PropertyValueIsNotSetException();
        } else if (StringUtils.isBlank(gatewayServiceUri)) {
            log.error("[Herodotus] |- Property [gateway-service-uri] is not set or property format is incorrect!");
            throw new PropertyValueIsNotSetException();
        } else {
            String var10000 = url(gatewayServiceUri);
            return var10000 + serviceName;
        }
    }

    /**
     * Xây dựng URI SAS với điểm cuối và URI của issuer.
     *
     * @param uri URI hiện tại.
     * @param endpoint Điểm cuối cần thêm vào URI.
     * @param issuerUri URI của issuer.
     * @return URI SAS hoặc ném lỗi nếu URI của issuer không được cung cấp.
     */
    public static String sasUri(String uri, String endpoint, String issuerUri) {
        if (StringUtils.isNotBlank(uri)) {
            return uri;
        } else if (StringUtils.isBlank(issuerUri)) {
            log.error("[Herodotus] |- Property [issuer-uri] is not set or property format is incorrect!");
            throw new PropertyValueIsNotSetException();
        } else {
            return issuerUri + endpoint;
        }
    }
}

