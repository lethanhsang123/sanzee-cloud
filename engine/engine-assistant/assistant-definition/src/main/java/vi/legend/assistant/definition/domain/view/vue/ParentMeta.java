package vi.legend.assistant.definition.domain.view.vue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentMeta extends BaseMeta {
    @JsonProperty("isHideAllChild")
    private Boolean hideAllChild = false;

    public ParentMeta() {
    }

    public Boolean getHideAllChild() {
        return this.hideAllChild;
    }

    public void setHideAllChild(Boolean hideAllChild) {
        this.hideAllChild = hideAllChild;
    }
}
