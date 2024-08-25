package vi.legend.assistant.starter.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import vi.legend.assistant.starter.autoconfigure.customizer.Jackson2DefaultObjectMapperBuilderCustomizer;

@AutoConfiguration
public class Jackson2AutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(Jackson2AutoConfiguration.class);

    public Jackson2AutoConfiguration() {
    }

    @PostConstruct
    public void postConstruct() {
        log.info("[Herodotus] |- Module [Jackson2] Auto Configure.");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer defaultObjectMapperBuilderCustomizer() {
        Jackson2DefaultObjectMapperBuilderCustomizer customizer = new Jackson2DefaultObjectMapperBuilderCustomizer();
        log.debug("[Herodotus] |- Strategy [Jackson2 Default ObjectMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        log.trace("[Herodotus] |- Bean [Jackson2 Http Message Converter] Auto Configure.");
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ComponentScan({"cn.herodotus.engine.assistant.core.json.jackson2.utils"})
    static class JacksonUtilsConfiguration {
        JacksonUtilsConfiguration() {
        }

        @PostConstruct
        public void postConstruct() {
            Jackson2AutoConfiguration.log.debug("[Herodotus] |- SDK [Assistant Jackson2 Utils] Auto Configure.");
        }
    }
}
