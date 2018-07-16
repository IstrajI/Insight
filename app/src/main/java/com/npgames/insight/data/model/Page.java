package com.npgames.insight.data.model;

@Deprecated
public class Page {

    private String text;

    public Page(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
