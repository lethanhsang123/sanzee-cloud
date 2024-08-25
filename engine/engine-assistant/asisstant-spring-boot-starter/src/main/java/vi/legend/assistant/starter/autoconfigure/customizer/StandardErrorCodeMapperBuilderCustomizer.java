package vi.legend.assistant.starter.autoconfigure.customizer;

import org.springframework.core.Ordered;
import vi.legend.assistant.definition.constants.ErrorCodes;
import vi.legend.assistant.definition.feedback.*;
import vi.legend.assistant.definition.function.ErrorCodeMapperBuilderCustomizer;
import vi.legend.assistant.definition.support.ErrorCodeMapperBuilder;

public class StandardErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    public StandardErrorCodeMapperBuilderCustomizer() {
    }

    public void customize(ErrorCodeMapperBuilder builder) {
        builder.unauthorized(new UnauthorizedFeedback[]{
                ErrorCodes.ACCESS_DENIED,
                ErrorCodes.ACCOUNT_DISABLED,
                ErrorCodes.ACCOUNT_ENDPOINT_LIMITED,
                ErrorCodes.ACCOUNT_EXPIRED,
                ErrorCodes.ACCOUNT_LOCKED,
                ErrorCodes.BAD_CREDENTIALS,
                ErrorCodes.CREDENTIALS_EXPIRED,
                ErrorCodes.INVALID_CLIENT,
                ErrorCodes.INVALID_TOKEN,
                ErrorCodes.INVALID_GRANT,
                ErrorCodes.UNAUTHORIZED_CLIENT,
                ErrorCodes.USERNAME_NOT_FOUND,
                ErrorCodes.SESSION_EXPIRED
        })
                .forbidden(new ForbiddenFeedback[]{
                        ErrorCodes.INSUFFICIENT_SCOPE,
                        ErrorCodes.SQL_INJECTION_REQUEST
                })
                .methodNotAllowed(new MethodNotAllowedFeedback[]{
                        ErrorCodes.HTTP_REQUEST_METHOD_NOT_SUPPORTED
                }).notAcceptable(new NotAcceptableFeedback[]{
                        ErrorCodes.UNSUPPORTED_GRANT_TYPE,
                        ErrorCodes.UNSUPPORTED_RESPONSE_TYPE,
                        ErrorCodes.UNSUPPORTED_TOKEN_TYPE
                }).preconditionFailed(new PreconditionFailedFeedback[]{
                        ErrorCodes.INVALID_REDIRECT_URI,
                        ErrorCodes.INVALID_REQUEST,
                        ErrorCodes.INVALID_SCOPE,
                        ErrorCodes.METHOD_ARGUMENT_NOT_VALID
                }).unsupportedMediaType(new UnsupportedMediaTypeFeedback[]{
                        ErrorCodes.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE
                }).internalServerError(new InternalServerErrorFeedback[]{
                        ErrorCodes.SERVER_ERROR,
                        ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION,
                        ErrorCodes.ILLEGAL_ARGUMENT_EXCEPTION,
                        ErrorCodes.IO_EXCEPTION,
                        ErrorCodes.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION,
                        ErrorCodes.NULL_POINTER_EXCEPTION,
                        ErrorCodes.TYPE_MISMATCH_EXCEPTION
                }).notImplemented(new NotImplementedFeedback[]{
                        ErrorCodes.PROPERTY_VALUE_IS_NOT_SET_EXCEPTION,
                        ErrorCodes.URL_FORMAT_INCORRECT_EXCEPTION,
                        ErrorCodes.ILLEGAL_SYMMETRIC_KEY,
                        ErrorCodes.DISCOVERED_UNRECORDED_ERROR_EXCEPTION
                }).serviceUnavailable(new ServiceUnavailableFeedback[]{
                        ErrorCodes.COOKIE_THEFT,
                        ErrorCodes.INVALID_COOKIE,
                        ErrorCodes.PROVIDER_NOT_FOUND,
                        ErrorCodes.TEMPORARILY_UNAVAILABLE,
                        ErrorCodes.SEARCH_IP_LOCATION
                }).customize(new CustomizeFeedback[]{
                        ErrorCodes.TRANSACTION_ROLLBACK,
                        ErrorCodes.BAD_SQL_GRAMMAR,
                        ErrorCodes.DATA_INTEGRITY_VIOLATION,
                        ErrorCodes.PIPELINE_INVALID_COMMANDS
                });
    }

    public int getOrder() {
        return 0;
    }
}
