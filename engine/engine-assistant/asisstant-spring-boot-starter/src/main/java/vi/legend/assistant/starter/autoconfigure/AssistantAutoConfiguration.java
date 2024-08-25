package vi.legend.assistant.starter.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import vi.legend.assistant.definition.domain.ErrorCodeMapper;
import vi.legend.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import vi.legend.assistant.definition.support.ErrorCodeMapperBuilder;
import vi.legend.assistant.starter.autoconfigure.customizer.StandardErrorCodeMapperBuilderCustomizer;

import java.util.Iterator;
import java.util.List;

@AutoConfiguration
@Import({SpringUtil.class})
public class AssistantAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(AssistantAutoConfiguration.class);

    public AssistantAutoConfiguration() {
    }

    @PostConstruct
    public void postConstruct() {
        log.info("[Sanzee] |- Module [Assistant Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer standardErrorCodeMapperBuilderCustomizer() {
        StandardErrorCodeMapperBuilderCustomizer customizer = new StandardErrorCodeMapperBuilderCustomizer();
        log.debug("[Sanzee] |- Strategy [Standard ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilder errorCodeMapperBuilder(List<ErrorCodeMapperBuilderCustomizer> customizers) {
        ErrorCodeMapperBuilder builder = new ErrorCodeMapperBuilder();
        this.customize(builder, customizers);
        log.debug("[Sanzee] |- Bean [ErrorCodeMapper Builder] Auto Configure.");
        return builder;
    }

    private void customize(ErrorCodeMapperBuilder builder, List<ErrorCodeMapperBuilderCustomizer> customizers) {
        Iterator var3 = customizers.iterator();

        while(var3.hasNext()) {
            ErrorCodeMapperBuilderCustomizer customizer = (ErrorCodeMapperBuilderCustomizer)var3.next();
            customizer.customize(builder);
        }

    }

    @Bean
    public ErrorCodeMapper errorCodeMapper(ErrorCodeMapperBuilder builder) {
        ErrorCodeMapper mapper = builder.build();
        log.debug("[Sanzee] |- Bean [ErrorCodeMapper] Auto Configure.");
        return mapper;
    }
}
