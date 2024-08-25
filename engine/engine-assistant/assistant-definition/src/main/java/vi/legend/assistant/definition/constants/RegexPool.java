package vi.legend.assistant.definition.constants;

public interface RegexPool extends org.dromara.hutool.core.regex.RegexPool {

    String BRACES_AND_CONTENT = "\\{([^}])*\\}";
    String ALL_CHARACTERS = "(?!^)";
    String SINGLE_QUOTE_STRING_EQUATION = "(\\w+)\\s*=\\s*'(.*?)'";
    String DNS_COMPATIBLE = "^[a-z0-9][a-z0-9\\.\\-]+[a-z0-9]$";

}
