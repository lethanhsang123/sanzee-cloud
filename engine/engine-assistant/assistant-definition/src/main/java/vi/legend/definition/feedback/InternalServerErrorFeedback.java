package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class InternalServerErrorFeedback extends Feedback {
    public InternalServerErrorFeedback(String value) {
        super(value, 500);
    }
}
