package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class NotAcceptableFeedback extends Feedback {
    public NotAcceptableFeedback(String value) {
        super(value, 406);
    }
}
