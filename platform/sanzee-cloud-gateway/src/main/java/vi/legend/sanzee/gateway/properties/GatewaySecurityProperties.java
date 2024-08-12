package vi.legend.sanzee.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "sanzee-cloud.gateway")
public class GatewaySecurityProperties {

    private List<String> whiteList;

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

//    public static class Trace implements Serializable {
//        /**
//         * Trace key 在Redis中有效时间，单位秒, 默认5分钟
//         */
//        private long expired = 60 * 5;
//        /**
//         * Trace key 在Redis中效时间还是剩余多长时间，就需要进行更新，单位秒, 默认1分钟
//         * 即，如果Trace key在Redis中过期时间小于60秒，那么就重新创建Trace key
//         */
//        private long threshold = 60;
//
//        public long getExpired() {
//            return expired;
//        }
//
//        public void setExpired(long expired) {
//            this.expired = expired;
//        }
//
//        public long getThreshold() {
//            return threshold;
//        }
//
//        public void setThreshold(long threshold) {
//            this.threshold = threshold;
//        }
//    }

}
