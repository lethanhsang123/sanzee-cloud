package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class ServiceUnavailableFeedback extends Feedback {
    public ServiceUnavailableFeedback(String value) {
        super(value, 503);
    }
}
