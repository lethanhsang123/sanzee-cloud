package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class NotAcceptableFeedback extends Feedback {
    public NotAcceptableFeedback(String value) {
        super(value, 406);
    }
}
