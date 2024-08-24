package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class UnsupportedMediaTypeFeedback extends Feedback {
    public UnsupportedMediaTypeFeedback(String value) {
        super(value, 415);
    }
}

