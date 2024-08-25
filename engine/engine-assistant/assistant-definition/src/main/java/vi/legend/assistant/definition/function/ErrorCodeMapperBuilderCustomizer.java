package vi.legend.assistant.definition.function;

import vi.legend.assistant.definition.support.ErrorCodeMapperBuilder;

@FunctionalInterface
public interface ErrorCodeMapperBuilderCustomizer {
    void customize(ErrorCodeMapperBuilder builder);
}
