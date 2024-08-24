package vi.legend.assistant.starter.autoconfigure.customizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import vi.legend.assistant.core.json.jackson2.modules.EncapsulationClassJackson2Module;

import java.util.ArrayList;
import java.util.List;

public class Jackson2DefaultObjectMapperBuilderCustomizer implements BaseObjectMapperBuilderCustomizer{

    public Jackson2DefaultObjectMapperBuilderCustomizer() {
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToEnable(new Object[]{
                SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,
                JsonParser.Feature.ALLOW_SINGLE_QUOTES,
                JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature()
        });
        builder.featuresToDisable(new Object[]{
                SerializationFeature.FAIL_ON_EMPTY_BEANS,
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
        });
        builder.modulesToInstall((modules) -> {
            List<Module> install = new ArrayList(modules);
            install.add(new EncapsulationClassJackson2Module());
            install.add(new Jdk8Module());
            install.add(new JavaTimeModule());
            builder.modulesToInstall(this.toArray(install));
        });
        builder.findModulesViaServiceLoader(true);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
