package vi.legend.assistant.core.exception.properties;

import vi.legend.assistant.definition.constants.ErrorCodes;
import vi.legend.assistant.definition.domain.Feedback;
import vi.legend.assistant.definition.exception.PlatformRuntimeException;

public class UrlFormatIncorrectException extends PlatformRuntimeException {
    public UrlFormatIncorrectException() {
    }

    public UrlFormatIncorrectException(String message) {
        super(message);
    }

    public UrlFormatIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlFormatIncorrectException(Throwable cause) {
        super(cause);
    }

    protected UrlFormatIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.URL_FORMAT_INCORRECT_EXCEPTION;
    }
}
