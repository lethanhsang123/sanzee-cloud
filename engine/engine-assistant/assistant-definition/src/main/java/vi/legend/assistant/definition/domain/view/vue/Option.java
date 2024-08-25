package vi.legend.assistant.definition.domain.view.vue;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class Option implements Serializable {
    private String label;
    private String value;

    public Option() {
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("label", this.label).add("value", this.value).toString();
    }
}