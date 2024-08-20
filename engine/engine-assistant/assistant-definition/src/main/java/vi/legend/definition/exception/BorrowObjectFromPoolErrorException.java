package vi.legend.definition.exception;

import vi.legend.definition.constants.ErrorCodes;
import vi.legend.definition.domain.Feedback;

public class BorrowObjectFromPoolErrorException extends PlatformRuntimeException {
    public BorrowObjectFromPoolErrorException() {
    }

    public BorrowObjectFromPoolErrorException(String message) {
        super(message);
    }

    public BorrowObjectFromPoolErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BorrowObjectFromPoolErrorException(Throwable cause) {
        super(cause);
    }

    protected BorrowObjectFromPoolErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.BORROW_OBJECT_FROM_POOL_ERROR_EXCEPTION;
    }
}

