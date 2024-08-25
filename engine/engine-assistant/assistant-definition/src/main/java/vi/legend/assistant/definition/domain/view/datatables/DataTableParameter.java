package vi.legend.assistant.definition.domain.view.datatables;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class DataTableParameter implements Serializable {
    private String name;
    private Object value;

    public DataTableParameter() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("value", this.value).toString();
    }
}

