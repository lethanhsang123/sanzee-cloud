package vi.legend.definition.feedback;

import vi.legend.definition.domain.Feedback;

public class CustomizeFeedback extends Feedback {
    public CustomizeFeedback(String value, int custom) {
        super(value, 500, custom);
    }
}
