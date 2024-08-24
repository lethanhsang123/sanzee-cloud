package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class InternalServerErrorFeedback extends Feedback {
    public InternalServerErrorFeedback(String value) {
        super(value, 500);
    }
}
