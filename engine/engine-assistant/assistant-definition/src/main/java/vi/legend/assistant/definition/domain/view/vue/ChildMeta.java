package vi.legend.assistant.definition.domain.view.vue;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildMeta extends BaseMeta {
    @JsonProperty("isDetailContent")
    private Boolean detailContent;

    public ChildMeta() {
    }

    public Boolean getDetailContent() {
        return this.detailContent;
    }

    public void setDetailContent(Boolean detailContent) {
        this.detailContent = detailContent;
    }
}
