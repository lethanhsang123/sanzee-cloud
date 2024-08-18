package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class UnsupportedMediaTypeFeedback extends Feedback {
    public UnsupportedMediaTypeFeedback(String value) {
        super(value, 415);
    }
}

