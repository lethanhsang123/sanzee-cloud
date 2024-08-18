package utils.protect;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ResourceUtils;

/**
 * Lớp XssUtils cung cấp các phương thức để bảo vệ chống lại các cuộc tấn công XSS (Cross-Site Scripting).
 * Sử dụng thư viện AntiSamy để lọc và làm sạch HTML.
 */
public class XssUtils {

    // Logger để ghi lại thông tin nhật ký của lớp.
    private static final Logger log = LoggerFactory.getLogger(XssUtils.class);

    // Đối tượng duy nhất của lớp XssUtils, sử dụng mô hình Singleton.
    private static volatile XssUtils INSTANCE;

    // Đối tượng AntiSamy dùng để quét và làm sạch HTML.
    private final AntiSamy antiSamy;

    // Chuỗi HTML đại diện cho khoảng trắng không thể tách rời.
    private final String nbsp;

    // Chuỗi HTML đại diện cho dấu ngoặc kép.
    private final String quot;

    /**
     * Constructor mặc định, khởi tạo đối tượng AntiSamy và các chuỗi thay thế HTML.
     */
    private XssUtils() {
        Policy policy = this.createPolicy();
        this.antiSamy = ObjectUtils.isNotEmpty(policy) ? new AntiSamy(policy) : new AntiSamy();
        this.nbsp = this.cleanHtml("&nbsp;");
        this.quot = this.cleanHtml("\"");
    }

    /**
     * Trả về đối tượng duy nhất của lớp XssUtils, khởi tạo đối tượng này nếu chưa tồn tại.
     * Sử dụng mô hình Singleton với double-checked locking.
     *
     * @return Đối tượng XssUtils.
     */
    private static XssUtils getInstance() {
        if (ObjectUtils.isEmpty(INSTANCE)) {
            synchronized (XssUtils.class) {
                if (ObjectUtils.isEmpty(INSTANCE)) {
                    INSTANCE = new XssUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Làm sạch chuỗi HTML bị ô nhiễm để bảo vệ chống lại các cuộc tấn công XSS.
     *
     * @param taintedHTML Chuỗi HTML bị ô nhiễm.
     * @return Chuỗi HTML đã được làm sạch.
     */
    public static String cleaning(String taintedHTML) {
        String cleanHtml = StringEscapeUtils.unescapeHtml4(getInstance().cleanHtml(taintedHTML));
        String temp = cleanHtml.replaceAll(getInstance().nbsp, "");
        temp = temp.replaceAll(getInstance().quot, "\"");
        String result = temp.replaceAll("\n", "");
        log.trace("[Sanzee] |- Antisamy process value from [{}] to [{}]", taintedHTML, result);
        return result;
    }

    /**
     * Tạo một policy cho AntiSamy từ file cấu hình.
     *
     * @return Đối tượng Policy.
     */
    private Policy createPolicy() {
        try {
            URL url = ResourceUtils.getURL("classpath:antisamy/antisamy-anythinggoes.xml");
            return Policy.getInstance(url);
        } catch (PolicyException | IOException var2) {
            log.warn("[Sanzee] |- Antisamy create policy error! {}", var2.getMessage());
            return null;
        }
    }

    /**
     * Quét và làm sạch HTML bị ô nhiễm bằng AntiSamy.
     *
     * @param taintedHtml Chuỗi HTML bị ô nhiễm.
     * @return Kết quả làm sạch HTML.
     * @throws ScanException Nếu có lỗi khi quét HTML.
     * @throws PolicyException Nếu có lỗi với policy.
     */
    private CleanResults scan(String taintedHtml) throws ScanException, PolicyException {
        return this.antiSamy.scan(taintedHtml);
    }

    /**
     * Làm sạch HTML bị ô nhiễm bằng cách sử dụng AntiSamy.
     *
     * @param taintedHtml Chuỗi HTML bị ô nhiễm.
     * @return Chuỗi HTML đã được làm sạch.
     */
    private String cleanHtml(String taintedHtml) {
        try {
            CleanResults cleanResults = this.scan(taintedHtml);
            return cleanResults.getCleanHTML();
        } catch (PolicyException | ScanException var3) {
            log.error("[Sanzee] |- Antisamy scan catch error! {}", var3.getMessage());
            return taintedHtml;
        }
    }
}

