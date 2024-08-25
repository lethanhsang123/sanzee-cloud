package vi.legend.sanzee.gateway.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import vi.legend.sanzee.gateway.handler.RefreshRoutesListener;

@Configuration(proxyBeanMethods = false)
public class GatewayCorsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GatewayCorsConfiguration.class);
    private static final String MAX_AGE = "18000L";

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
                HttpHeaders headers = response.getHeaders();
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
                if (requestMethod != null) {
                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
                }
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

    @Configuration(proxyBeanMethods = false)
//    @ConditionalOnSwaggerEnabled
    static class GatewaySwaggerConfiguration {

        @Bean
        public RefreshRoutesListener refreshRoutesListener(RouteLocator routeLocator,
                                                           SwaggerUiConfigParameters swaggerUiConfigParameters,
                                                           SwaggerUiConfigProperties swaggerUiConfigProperties) {
            RefreshRoutesListener refreshRoutesListener = new RefreshRoutesListener();
            refreshRoutesListener.setRouteLocator(routeLocator);
            refreshRoutesListener.setSwaggerUiConfigParameters(swaggerUiConfigParameters);
            refreshRoutesListener.setSwaggerUiConfigProperties(swaggerUiConfigProperties);
            log.trace("[Herodotus] |- Bean [Refresh Routes Listener] in AliyunScanConfiguration Auto Configure.");
            return refreshRoutesListener;
        }
    }
}
