package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class MethodNotAllowedFeedback extends Feedback {
    public MethodNotAllowedFeedback(String value) {
        super(value, 405);
    }
}
