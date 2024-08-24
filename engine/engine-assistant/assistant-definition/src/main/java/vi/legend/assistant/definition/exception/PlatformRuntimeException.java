package vi.legend.assistant.definition.exception;

import vi.legend.assistant.definition.constants.ErrorCodes;
import vi.legend.assistant.definition.domain.Feedback;

public class PlatformRuntimeException extends AbstractRuntimeException {
    public PlatformRuntimeException() {
    }

    public PlatformRuntimeException(String message) {
        super(message);
    }

    public PlatformRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformRuntimeException(Throwable cause) {
        super(cause);
    }

    protected PlatformRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.INTERNAL_SERVER_ERROR;
    }
}

