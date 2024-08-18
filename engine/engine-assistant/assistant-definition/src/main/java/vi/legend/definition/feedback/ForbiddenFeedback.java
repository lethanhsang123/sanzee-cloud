package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class ForbiddenFeedback extends Feedback {
    public ForbiddenFeedback(String value) {
        super(value, 403);
    }
}
