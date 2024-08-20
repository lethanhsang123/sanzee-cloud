package vi.legend.definition.exception;

import vi.legend.definition.domain.Feedback;
import vi.legend.definition.domain.Result;

public interface SanzeeException {
    Feedback getFeedback();

    Result<String> getResult();
}
