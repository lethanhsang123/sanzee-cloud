package vi.legend.assistant.definition.domain.oauth2;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;

public class AccessPrincipal {
    @Schema(
            name = "Tham số code được gửi lại sau khi truy cập AuthorizeUrl",
            title = "Tham số code được gửi lại sau khi truy cập AuthorizeUrl"
    )
    private String code;

    @Schema(
            name = "appId của ứng dụng nhỏ",
            title = "appId của ứng dụng nhỏ"
    )
    private String appId;

    @Schema(
            name = "Dữ liệu mã hóa",
            title = "Dữ liệu mã hóa của ứng dụng nhỏ WeChat"
    )
    private String encryptedData;

    @Schema(
            name = "Vector khởi tạo của thuật toán mã hóa",
            title = "Vector khởi tạo của thuật toán mã hóa trong ứng dụng nhỏ WeChat"
    )
    private String iv;

    @Schema(
            name = "openId của người dùng ứng dụng nhỏ",
            title = "openId của người dùng ứng dụng nhỏ"
    )
    private String openId;

    @Schema(
            name = "Khóa phiên",
            title = "Khóa phiên của ứng dụng nhỏ WeChat"
    )
    private String sessionKey;

    @Schema(
            name = "ID duy nhất",
            title = "ID duy nhất của WeChat"
    )
    private String unionId;

    @Schema(
            name = "Thông tin không nhạy cảm của người dùng",
            title = "Thông tin không nhạy cảm của người dùng ứng dụng nhỏ WeChat"
    )
    private String rawData;

    @Schema(
            name = "Chữ ký",
            title = "Chữ ký của ứng dụng nhỏ WeChat"
    )
    private String signature;

    @Schema(
            name = "Tham số auth_code được gửi lại sau khi truy cập",
            title = "Tham số này hiện chỉ được sử dụng cho đăng nhập Alipay"
    )
    private String auth_code;

    @Schema(
            name = "Tham số state được gửi lại sau khi truy cập",
            title = "Dùng để so sánh với state trước khi yêu cầu AuthorizeUrl, để ngăn chặn tấn công CSRF"
    )
    private String state;

    @Schema(
            name = "Tên tham số nhận code trong đăng nhập ủy quyền Huawei"
    )
    private String authorization_code;

    @Schema(
            name = "oauth_token được trả về sau khi gọi lại",
            title = "oauth_token được trả về sau khi gọi lại Twitter"
    )
    private String oauth_token;

    @Schema(
            name = "oauth_verifier được trả về sau khi gọi lại",
            title = "oauth_verifier được trả về sau khi gọi lại Twitter"
    )
    private String oauth_verifier;

    @Schema(
            name = "Số điện thoại",
            title = "Mã định danh duy nhất cho đăng nhập bằng tin nhắn điện thoại"
    )
    private String mobile;

    public AccessPrincipal() {
    }

    // Các phương thức getter và setter cho các thuộc tính

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEncryptedData() {
        return this.encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return this.iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return this.unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRawData() {
        return this.rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAuth_code() {
        return this.auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthorization_code() {
        return this.authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }

    public String getOauth_token() {
        return this.oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_verifier() {
        return this.oauth_verifier;
    }

    public void setOauth_verifier(String oauth_verifier) {
        this.oauth_verifier = oauth_verifier;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", this.code)
                .add("appId", this.appId)
                .add("encryptedData", this.encryptedData)
                .add("iv", this.iv)
                .add("openId", this.openId)
                .add("sessionKey", this.sessionKey)
                .add("unionId", this.unionId)
                .add("rawData", this.rawData)
                .add("signature", this.signature)
                .add("auth_code", this.auth_code)
                .add("state", this.state)
                .add("authorization_code", this.authorization_code)
                .add("oauth_token", this.oauth_token)
                .add("oauth_verifier", this.oauth_verifier)
                .add("mobile", this.mobile)
                .toString();
    }
}

