package vi.legend.assistant.core.condition;

import vi.legend.assistant.core.context.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Lớp SwaggerEnabledCondition kiểm tra xem Swagger có được bật trong cấu hình hay không.
 */
public class SwaggerEnabledCondition implements Condition {
    private static final Logger log = LoggerFactory.getLogger(SwaggerEnabledCondition.class);

    /**
     * Constructor mặc định.
     */
    public SwaggerEnabledCondition() {
    }

    /**
     * Phương thức kiểm tra điều kiện, trả về true nếu Swagger được bật.
     *
     * @param conditionContext Context cung cấp thông tin về môi trường hiện tại.
     * @param metadata Metadata của phần tử được chú thích.
     * @return boolean Kết quả kiểm tra điều kiện.
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, "sanzee.swagger.enabled");
        log.debug("[Sanzee] |- Condition [Swagger Enabled] value is [{}]", result);
        return result;
    }
}

