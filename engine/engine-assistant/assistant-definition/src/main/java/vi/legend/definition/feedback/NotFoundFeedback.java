package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class NotFoundFeedback extends Feedback {
    public NotFoundFeedback(String message) {
        super(message, 404);
    }
}
