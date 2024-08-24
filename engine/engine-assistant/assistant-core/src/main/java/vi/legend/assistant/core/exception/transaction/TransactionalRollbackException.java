package vi.legend.assistant.core.exception.transaction;

import vi.legend.assistant.definition.constants.ErrorCodes;
import vi.legend.assistant.definition.domain.Feedback;
import vi.legend.assistant.definition.exception.PlatformRuntimeException;

public class TransactionalRollbackException extends PlatformRuntimeException {
    public TransactionalRollbackException() {
    }

    public TransactionalRollbackException(String message) {
        super(message);
    }

    public TransactionalRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionalRollbackException(Throwable cause) {
        super(cause);
    }

    protected TransactionalRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Feedback getFeedback() {
        return ErrorCodes.TRANSACTION_ROLLBACK;
    }
}
