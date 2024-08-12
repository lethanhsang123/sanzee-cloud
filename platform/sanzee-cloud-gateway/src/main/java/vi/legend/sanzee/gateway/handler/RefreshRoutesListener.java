package vi.legend.sanzee.gateway.handler;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RefreshRoutesListener implements ApplicationListener<RefreshRoutesEvent> {

    private static final Logger log = LoggerFactory.getLogger(RefreshRoutesListener.class);

    public static final String API_URI = "/v3/api-docs";

    @Value("${spring.application.name}")
    private String self;
    private RouteLocator routeLocator;
    private SwaggerUiConfigParameters swaggerUiConfigParameters;
    private SwaggerUiConfigProperties swaggerUiConfigProperties;

    public void setRouteLocator(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    public void setSwaggerUiConfigParameters(SwaggerUiConfigParameters swaggerUiConfigParameters) {
        this.swaggerUiConfigParameters = swaggerUiConfigParameters;
    }

    public void setSwaggerUiConfigProperties(SwaggerUiConfigProperties swaggerUiConfigProperties) {
        this.swaggerUiConfigProperties = swaggerUiConfigProperties;
    }

    /**
     *
     * @param event An event that triggers the refresh of the routes defined in the gateway
     *              When this event is published, the gateway reloads its route definitions,
     *              which is particularly useful when routes are dynamically updated at runtime.
     */
    @Override
    public void onApplicationEvent(RefreshRoutesEvent event) {
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes()
                .filter(route -> route.getUri().getHost() != null && Objects.equals(route.getUri().getScheme(), "lb") && !self.equalsIgnoreCase(route.getUri().getHost()))
                .subscribe(route -> routes.add(route.getUri().getHost()));

        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> swaggerUrls = routes.stream().map(this::createSwaggerUrl).collect(Collectors.toSet());

        if (ObjectUtils.isNotEmpty(swaggerUiConfigParameters)) {
            log.debug("[Herodotus] |- Services is Changed, update Urls");
            swaggerUiConfigParameters.setUrls(swaggerUrls);
            swaggerUiConfigProperties.setUrls(swaggerUrls);
        }
    }

    private AbstractSwaggerUiConfigProperties.SwaggerUrl createSwaggerUrl(String service) {

        String data = API_URI;

        if (StringUtils.containsIgnoreCase(service, "bpmn")) {
            data = "/openapi.json";
        }

        String url = "/" + service.toLowerCase() + data;

        log.debug("[Herodotus] |- Create Swagger Url - Name: {}, Location {}.", service, url);

        AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
        swaggerUrl.setUrl(url);
        swaggerUrl.setName(service);
        return swaggerUrl;
    }


}
