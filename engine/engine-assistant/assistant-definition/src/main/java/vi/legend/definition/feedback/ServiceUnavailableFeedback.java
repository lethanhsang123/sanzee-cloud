package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class ServiceUnavailableFeedback extends Feedback {
    public ServiceUnavailableFeedback(String value) {
        super(value, 503);
    }
}
