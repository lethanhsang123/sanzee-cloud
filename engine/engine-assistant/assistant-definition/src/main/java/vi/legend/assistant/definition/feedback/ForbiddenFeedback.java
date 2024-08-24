package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class ForbiddenFeedback extends Feedback {
    public ForbiddenFeedback(String value) {
        super(value, 403);
    }
}
