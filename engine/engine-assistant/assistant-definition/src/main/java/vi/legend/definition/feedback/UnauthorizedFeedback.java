package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class UnauthorizedFeedback extends Feedback {
    public UnauthorizedFeedback(String value) {
        super(value, 401);
    }
}