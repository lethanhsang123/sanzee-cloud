package vi.legend.assistant.definition.support;

import org.apache.commons.collections4.MapUtils;
import vi.legend.assistant.definition.constants.ErrorCodes;
import vi.legend.assistant.definition.domain.ErrorCodeMapper;
import vi.legend.assistant.definition.domain.Feedback;
import vi.legend.assistant.definition.feedback.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorCodeMapperBuilder {
    private final Map<Feedback, Integer> unauthorizedConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.UNAUTHORIZED, ErrorCodes.UNAUTHORIZED.getSequence());
        }
    };
    private final Map<Feedback, Integer> forbiddenConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.FORBIDDEN, ErrorCodes.FORBIDDEN.getSequence());
        }
    };
    private final Map<Feedback, Integer> methodNotAllowedConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.METHOD_NOT_ALLOWED, ErrorCodes.METHOD_NOT_ALLOWED.getSequence());
        }
    };
    private final Map<Feedback, Integer> notAcceptableConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.NOT_ACCEPTABLE, ErrorCodes.NOT_ACCEPTABLE.getSequence());
        }
    };
    private final Map<Feedback, Integer> preconditionFailedConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.PRECONDITION_FAILED, ErrorCodes.PRECONDITION_FAILED.getSequence());
        }
    };
    private final Map<Feedback, Integer> unsupportedMediaTypeConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.PRECONDITION_FAILED, ErrorCodes.PRECONDITION_FAILED.getSequence());
        }
    };
    private final Map<Feedback, Integer> internalServerErrorConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.INTERNAL_SERVER_ERROR, ErrorCodes.INTERNAL_SERVER_ERROR.getSequence());
        }
    };
    private final Map<Feedback, Integer> notImplementedConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED.getSequence());
        }
    };
    private final Map<Feedback, Integer> serviceUnavailableConfigs = new LinkedHashMap<Feedback, Integer>() {
        {
            this.put(ErrorCodes.SERVICE_UNAVAILABLE, ErrorCodes.SERVICE_UNAVAILABLE.getSequence());
            this.put(ErrorCodes.OPEN_API_REQUEST_FAILURE, ErrorCodes.OPEN_API_REQUEST_FAILURE.getSequence());
        }
    };
    private final Map<Integer, Map<Feedback, Integer>> customizeConfigs = new LinkedHashMap();

    public ErrorCodeMapperBuilder() {
    }

    private ErrorCodeMapperBuilder create(Map<Feedback, Integer> container, Feedback... items) {
        Feedback[] var3 = items;
        int var4 = items.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Feedback item = var3[var5];
            container.put(item, item.getSequence(container.size()));
        }

        return this;
    }

    public ErrorCodeMapperBuilder unauthorized(UnauthorizedFeedback... items) {
        return this.create(this.unauthorizedConfigs, items);
    }

    public ErrorCodeMapperBuilder forbidden(ForbiddenFeedback... items) {
        return this.create(this.forbiddenConfigs, items);
    }

    public ErrorCodeMapperBuilder methodNotAllowed(MethodNotAllowedFeedback... items) {
        return this.create(this.methodNotAllowedConfigs, items);
    }

    public ErrorCodeMapperBuilder notAcceptable(NotAcceptableFeedback... items) {
        return this.create(this.notAcceptableConfigs, items);
    }

    public ErrorCodeMapperBuilder preconditionFailed(PreconditionFailedFeedback... items) {
        return this.create(this.preconditionFailedConfigs, items);
    }

    public ErrorCodeMapperBuilder unsupportedMediaType(UnsupportedMediaTypeFeedback... items) {
        return this.create(this.unsupportedMediaTypeConfigs, items);
    }

    public ErrorCodeMapperBuilder internalServerError(InternalServerErrorFeedback... items) {
        return this.create(this.internalServerErrorConfigs, items);
    }

    public ErrorCodeMapperBuilder notImplemented(NotImplementedFeedback... items) {
        return this.create(this.notImplementedConfigs, items);
    }

    public ErrorCodeMapperBuilder serviceUnavailable(ServiceUnavailableFeedback... items) {
        return this.create(this.serviceUnavailableConfigs, items);
    }

    public ErrorCodeMapperBuilder customize(CustomizeFeedback... items) {
        CustomizeFeedback[] var2 = items;
        int var3 = items.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Feedback item = var2[var4];
            if (item.isCustom()) {
                Map<Feedback, Integer> config = (Map) this.customizeConfigs.get(item.getCustom());
                if (MapUtils.isEmpty((Map)config)) {
                    config = new LinkedHashMap();
                }

                ((Map)config).put(item, item.getSequence(((Map)config).size()));
                this.customizeConfigs.put(item.getCustom(), config);
            }
        }

        return this;
    }

    public ErrorCodeMapper build() {
        ErrorCodeMapper errorCodeMapper = ErrorCodeMapper.getInstance();
        errorCodeMapper.append(this.unauthorizedConfigs);
        errorCodeMapper.append(this.forbiddenConfigs);
        errorCodeMapper.append(this.methodNotAllowedConfigs);
        errorCodeMapper.append(this.notAcceptableConfigs);
        errorCodeMapper.append(this.preconditionFailedConfigs);
        errorCodeMapper.append(this.unsupportedMediaTypeConfigs);
        errorCodeMapper.append(this.internalServerErrorConfigs);
        errorCodeMapper.append(this.notImplementedConfigs);
        errorCodeMapper.append(this.serviceUnavailableConfigs);
        this.customizeConfigs.forEach((key, feedbacks) -> {
            errorCodeMapper.append(feedbacks);
        });
        return errorCodeMapper;
    }
}