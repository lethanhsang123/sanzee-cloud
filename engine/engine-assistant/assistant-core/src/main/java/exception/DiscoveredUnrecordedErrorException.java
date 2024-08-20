package exception;

import vi.legend.definition.constants.ErrorCodes;
import vi.legend.definition.domain.Feedback;
import vi.legend.definition.exception.PlatformRuntimeException;

public class DiscoveredUnrecordedErrorException extends PlatformRuntimeException {
    public DiscoveredUnrecordedErrorException() {
    }

    public DiscoveredUnrecordedErrorException(String message) {
        super(message);
    }

    public DiscoveredUnrecordedErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscoveredUnrecordedErrorException(Throwable cause) {
        super(cause);
    }

    protected DiscoveredUnrecordedErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION;
    }
}
