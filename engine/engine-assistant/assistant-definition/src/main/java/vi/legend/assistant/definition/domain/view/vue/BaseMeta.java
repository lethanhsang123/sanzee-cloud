package vi.legend.assistant.definition.domain.view.vue;

import com.fasterxml.jackson.annotation.JsonProperty;
import vi.legend.assistant.definition.domain.base.Entity;

public class BaseMeta implements Entity {
    private String title;
    private String icon;
    @JsonProperty("isNotKeepAlive")
    private Boolean notKeepAlive = false;
    @JsonProperty("isIgnoreAuth")
    private Boolean ignoreAuth = false;

    public BaseMeta() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getNotKeepAlive() {
        return this.notKeepAlive;
    }

    public void setNotKeepAlive(Boolean notKeepAlive) {
        this.notKeepAlive = notKeepAlive;
    }

    public Boolean getIgnoreAuth() {
        return this.ignoreAuth;
    }

    public void setIgnoreAuth(Boolean ignoreAuth) {
        this.ignoreAuth = ignoreAuth;
    }
}
