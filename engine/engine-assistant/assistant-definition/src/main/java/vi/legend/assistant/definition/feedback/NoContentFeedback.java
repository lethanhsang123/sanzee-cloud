package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class NoContentFeedback extends Feedback {
    public NoContentFeedback(String value) {
        super(value, 204);
    }
}
