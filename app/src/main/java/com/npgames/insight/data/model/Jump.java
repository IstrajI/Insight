package com.npgames.insight.data.model;

import java.io.Serializable;

@Deprecated
public class Jump implements Serializable{
    private String id;
    private String text;
    private boolean status;

    public Jump(final String id, final String text) {
        this.id = id;
        this.text = text;
        this.status = true;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
