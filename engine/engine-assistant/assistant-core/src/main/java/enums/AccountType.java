package enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;
import me.zhyd.oauth.config.AuthDefaultSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Schema(
        title = "Loại tài khoản"
)
@JsonFormat(
        shape = JsonFormat.Shape.OBJECT
)
public enum AccountType {
    INSTITUTION("INSTITUTION", "", "Nhân viên tổ chức"),
    SMS("SMS", "PHONE_NUMBER", "Mã xác nhận điện thoại"),
    WXAPP("WXAPP", "WECHAT_MINI_APP", "Ứng dụng nhỏ của WeChat"),
    // ...
    DINGTALK(AuthDefaultSource.DINGTALK.name(), "JUST_AUTH", "DingTalk"),
    DINGTALK_ACCOUNT(AuthDefaultSource.DINGTALK_ACCOUNT.name(), "JUST_AUTH", "Tài khoản DingTalk"),
    ALIYUN(AuthDefaultSource.ALIYUN.name(), "JUST_AUTH", "Aliyun"),
    TAOBAO(AuthDefaultSource.TAOBAO.name(), "JUST_AUTH", "Taobao"),
    ALIPAY(AuthDefaultSource.ALIPAY.name(), "JUST_AUTH", "Alipay"),
    TEAMBITION(AuthDefaultSource.TEAMBITION.name(), "JUST_AUTH", "Teambition"),
    HUAWEI(AuthDefaultSource.HUAWEI.name(), "JUST_AUTH", "Huawei"),
    FEISHU(AuthDefaultSource.FEISHU.name(), "JUST_AUTH", "Feishu"),
    JD(AuthDefaultSource.JD.name(), "JUST_AUTH", "Jingdong"),
    DOUYIN(AuthDefaultSource.DOUYIN.name(), "JUST_AUTH", "Douyin"),
    TOUTIAO(AuthDefaultSource.TOUTIAO.name(), "JUST_AUTH", "Toutiao"),
    MI(AuthDefaultSource.MI.name(), "JUST_AUTH", "Xiaomi"),
    RENREN(AuthDefaultSource.RENREN.name(), "JUST_AUTH", "Renren"),
    MEITUAN(AuthDefaultSource.MEITUAN.name(), "JUST_AUTH", "Meituan"),
    ELEME(AuthDefaultSource.ELEME.name(), "JUST_AUTH", "Eleme"),
    KUJIALE(AuthDefaultSource.KUJIALE.name(), "JUST_AUTH", "Kujiale"),
    XMLY(AuthDefaultSource.XMLY.name(), "JUST_AUTH", "Ximalaya"),
    // ...
    GOOGLE(AuthDefaultSource.GOOGLE.name(), "JUST_AUTH", "Google"),
    MICROSOFT(AuthDefaultSource.MICROSOFT.name(), "JUST_AUTH", "Microsoft"),
    FACEBOOK(AuthDefaultSource.FACEBOOK.name(), "JUST_AUTH", "Facebook"),
    LINKEDIN(AuthDefaultSource.LINKEDIN.name(), "JUST_AUTH", "LinkedIn"),
    TWITTER(AuthDefaultSource.TWITTER.name(), "JUST_AUTH", "Twitter"),
    AMAZON(AuthDefaultSource.AMAZON.name(), "JUST_AUTH", "Amazon"),
    SLACK(AuthDefaultSource.SLACK.name(), "JUST_AUTH", "Slack"),
    LINE(AuthDefaultSource.LINE.name(), "JUST_AUTH", "Line"),
    OKTA(AuthDefaultSource.OKTA.name(), "JUST_AUTH", "Okta"),
    PINTEREST(AuthDefaultSource.PINTEREST.name(), "JUST_AUTH", "Pinterest");

    public static final String JUST_AUTH_HANDLER = "JUST_AUTH";
    public static final String PHONE_NUMBER_HANDLER = "PHONE_NUMBER";
    public static final String WECHAT_MINI_APP_HANDLER = "WECHAT_MINI_APP";
    private static final Map<String, AccountType> INDEX_MAP = new HashMap();
    private static final List<Map<String, Object>> JSON_STRUCT = new ArrayList();

    @Schema(
            title = "Giá trị enum"
    )
    private final String key;
    @Schema(
            title = "Trình xử lý"
    )
    private final String handler;
    @Schema(
            title = "Mô tả"
    )
    private final String description;

    private AccountType(String key, String handler, String description) {
        this.key = key;
        this.handler = handler;
        this.description = description;
    }

    public static AccountType getAccountType(String key) {
        return (AccountType)INDEX_MAP.get(key);
    }

    public static List<Map<String, Object>> getJsonStruct() {
        return JSON_STRUCT;
    }

    @JsonValue
    public String getKey() {
        return this.key;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHandler() {
        return this.handler;
    }

    static {
        AccountType[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            AccountType accountType = var0[var2];
            INDEX_MAP.put(accountType.getKey(), accountType);
            JSON_STRUCT.add(accountType.ordinal(), ImmutableMap.<String, Object>builder().put("value", accountType.ordinal()).put("key", accountType.name()).put("text", accountType.getDescription()).build());
        }

    }
}

