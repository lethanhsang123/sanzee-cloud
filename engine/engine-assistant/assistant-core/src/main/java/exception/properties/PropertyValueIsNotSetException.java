package exception.properties;

import vi.legend.definition.constants.ErrorCodes;
import vi.legend.definition.domain.Feedback;
import vi.legend.definition.exception.PlatformRuntimeException;

public class PropertyValueIsNotSetException extends PlatformRuntimeException {
    public PropertyValueIsNotSetException() {
    }

    public PropertyValueIsNotSetException(String message) {
        super(message);
    }

    public PropertyValueIsNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyValueIsNotSetException(Throwable cause) {
        super(cause);
    }

    protected PropertyValueIsNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.PROPERTY_VALUE_IS_NOT_SET_EXCEPTION;
    }
}

