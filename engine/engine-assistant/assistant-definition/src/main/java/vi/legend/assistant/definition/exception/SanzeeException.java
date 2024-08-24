package vi.legend.assistant.definition.exception;

import vi.legend.assistant.definition.domain.Feedback;
import vi.legend.assistant.definition.domain.Result;

public interface SanzeeException {
    Feedback getFeedback();

    Result<String> getResult();
}
