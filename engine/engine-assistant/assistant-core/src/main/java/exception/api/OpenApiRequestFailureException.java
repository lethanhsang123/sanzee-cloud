package exception.api;

import vi.legend.definition.domain.Feedback;
import vi.legend.definition.exception.PlatformRuntimeException;

public class OpenApiRequestFailureException extends PlatformRuntimeException {
    public OpenApiRequestFailureException() {
    }

    public OpenApiRequestFailureException(String message) {
        super(message);
    }

    public OpenApiRequestFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenApiRequestFailureException(Throwable cause) {
        super(cause);
    }

    protected OpenApiRequestFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return super.getFeedback();
    }
}
