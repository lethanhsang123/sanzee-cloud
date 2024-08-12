package vi.legend.sanzee.gateway.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.reactive.resource.ResourceUrlProvider;

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



    /**
     * 判断路径是否与路径模式匹配
     *
     * @param patterns 路径模式数组
     * @param path     url
     * @return 是否匹配
     */
    public static boolean isPathMatch(String[] patterns, String path) {
        return isPathMatch(Arrays.asList(patterns), path);
    }

    /**
     * 判断路径是否与路径模式匹配
     *
     * @param patterns 路径模式字符串List
     * @param path     url
     * @return 是否匹配
     */
    public static boolean isPathMatch(List<String> patterns, String path) {
        PathMatcher pathMatcher = getPathMatcher();
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }

}
