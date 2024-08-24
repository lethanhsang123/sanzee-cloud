package vi.legend.assistant.definition.domain;

import com.google.common.base.Objects;
import java.io.Serializable;
import org.dromara.hutool.core.lang.Assert;

public class Feedback implements Serializable {

    private static final int IS_NOT_CUSTOMIZED = 0;
    private final String message;
    private final int status;
    private final int custom;

    public Feedback(String message, int status) {
        this(message, status, 0);
    }

    public Feedback(String message, int status, int custom) {
        Assert.checkBetween(custom, 0, 9);
        this.message = message;
        this.status = status;
        this.custom = custom;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isCustom() {
        return this.custom != 0;
    }

    public int getCustom() {
        return this.custom;
    }

    public int getSequence() {
        return this.isCustom() ? this.custom * 10000 : this.status * 100;
    }

    public int getSequence(int index) {
        return this.getSequence() + index;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Feedback feedback = (Feedback)o;
            return Objects.equal(this.message, feedback.message);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.message});
    }

}
