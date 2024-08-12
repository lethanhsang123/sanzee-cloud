package vi.legend.sanzee.gateway.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnSwaggerEnabledCondition extends SpringBootCondition {
    private static final String SWAGGER_ENABLED_PROPERTY = "springdoc.api-docs.enabled";

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        boolean swaggerEnabled = env.getProperty(SWAGGER_ENABLED_PROPERTY, Boolean.class, true);

        if (swaggerEnabled) {
            return ConditionOutcome.match("Swagger is enabled");
        } else {
            return ConditionOutcome.noMatch("Swagger is disabled");
        }
    }
}
