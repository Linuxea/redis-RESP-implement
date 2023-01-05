package com.linuxea.rediscli;

public class TruncateString {

    private final String before;
    private final String after;

    public TruncateString(String before, String after) {
        this.before = before;
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }
}
