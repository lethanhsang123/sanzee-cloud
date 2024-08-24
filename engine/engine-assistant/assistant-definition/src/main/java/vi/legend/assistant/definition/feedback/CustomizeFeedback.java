package vi.legend.assistant.definition.feedback;

import vi.legend.assistant.definition.domain.Feedback;

public class CustomizeFeedback extends Feedback {
    public CustomizeFeedback(String value, int custom) {
        super(value, 500, custom);
    }
}
