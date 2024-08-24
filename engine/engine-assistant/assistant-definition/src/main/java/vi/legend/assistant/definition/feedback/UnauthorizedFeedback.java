package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class UnauthorizedFeedback extends Feedback {
    public UnauthorizedFeedback(String value) {
        super(value, 401);
    }
}