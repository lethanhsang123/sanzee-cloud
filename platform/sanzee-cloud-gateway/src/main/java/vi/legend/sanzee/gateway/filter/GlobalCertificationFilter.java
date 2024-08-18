package vi.legend.sanzee.gateway.filter;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vi.legend.sanzee.gateway.properties.GatewaySecurityProperties;
import vi.legend.sanzee.gateway.utils.WebFluxUtils;

import java.util.List;

@Component
public class GlobalCertificationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(GlobalCertificationFilter.class);

    @Resource
    private GatewaySecurityProperties gatewaySecurityProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. Ignore filter in white list
        String url = exchange.getRequest().getURI().getPath();
        List<String> whiteList = gatewaySecurityProperties.getWhiteList();
        if (WebFluxUtils.isPathMatch(whiteList, url)) {
            return chain.filter(exchange);
        }

        //  2. Check Bearer token
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!tokenWellFormed(token)) {
            log.warn("[Sanzee Cloud] |- Token is not Well Formed!");
//            return WebFluxUtils.
        }

        return chain.filter(exchange);
    }

    private boolean tokenWellFormed(String token) {
        return !Strings.isBlank(token) && StringUtils.containsOnly(token, "Bearer");
    }

    @Override
    public int getOrder() {
        return FilterOrder.GLOBAL_CERTIFICATION_FILTER_ORDER;
    }
}
