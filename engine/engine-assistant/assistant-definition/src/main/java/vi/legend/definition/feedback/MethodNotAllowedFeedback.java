package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class MethodNotAllowedFeedback extends Feedback {
    public MethodNotAllowedFeedback(String value) {
        super(value, 405);
    }
}
