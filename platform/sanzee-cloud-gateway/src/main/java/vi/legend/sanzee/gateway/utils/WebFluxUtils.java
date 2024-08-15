package vi.legend.sanzee.gateway.utils;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.reactive.resource.ResourceUrlProvider;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

public class WebFluxUtils {

    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private static final ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();

    public static PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public static ResourceUrlProvider getResourceUrlProvider() {
        return resourceUrlProvider;
    }


    public static boolean isPathMatch(String[] patterns, String path) {
        return isPathMatch(Arrays.asList(patterns), path);
    }

    public static boolean isPathMatch(List<String> patterns, String path) {
        PathMatcher pathMatcher = getPathMatcher();
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }

//    public static Mono<Void> writeJsonResponse(ServerHttpResponse response, Result<String> result) {
//        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.setStatusCode(HttpStatus.valueOf(result.getStatus()));
//
//        String jsonResult = Jackson2Utils.toJson(result);
//        byte[] bytes = jsonResult.getBytes(StandardCharsets.UTF_8);
//
//        DataBuffer buffer = response.bufferFactory().wrap(bytes);
//        return response.writeWith(Flux.just(buffer));
//    }


}
