package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class NotFoundFeedback extends Feedback {
    public NotFoundFeedback(String message) {
        super(message, 404);
    }
}
