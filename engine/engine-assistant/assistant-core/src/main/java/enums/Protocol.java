package enums;

public enum Protocol {
    HTTP("http://", "http"),
    HTTPS("https://", "https"),
    REDIS("redis://", "redis"),
    REDISS("rediss://", "rediss");

    private final String format;
    private final String prefix;

    private Protocol(String format, String prefix) {
        this.format = format;
        this.prefix = prefix;
    }

    public String getFormat() {
        return this.format;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
