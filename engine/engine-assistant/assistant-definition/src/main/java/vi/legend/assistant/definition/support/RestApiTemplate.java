package vi.legend.assistant.definition.support;


import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.MsgConvertor;
import cn.zhxu.okhttps.jackson.JacksonMsgConvertor;

public interface RestApiTemplate {
    String getBaseUrl();

    default HTTP http() {
        return HTTP.builder().baseUrl(this.getBaseUrl()).addMsgConvertor(this.getMsgConvertor()).build();
    }

    default MsgConvertor getMsgConvertor() {
        return new JacksonMsgConvertor();
    }
}

