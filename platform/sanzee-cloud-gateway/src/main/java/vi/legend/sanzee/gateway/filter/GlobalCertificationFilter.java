package vi.legend.sanzee.gateway.filter;

import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vi.legend.sanzee.gateway.properties.GatewaySecurityProperties;
import vi.legend.sanzee.gateway.utils.WebFluxUtils;

import java.util.List;

@Component
public class GlobalCertificationFilter implements GlobalFilter, Ordered {

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

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return FilterOrder.GLOBAL_CERTIFICATION_FILTER_ORDER;
    }
}
