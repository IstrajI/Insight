package com.npgames.insight.data.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paragraph {
    private int id;
    private int textId;
    private List<Jump> jumps;
    private Map<Paragraph.ActionTypes, Integer> actions;
    public enum ActionTypes {COND_NFT_TIME, TIME, HP, AUR, DEX, PRC, AMN}

    public final static int INIT_PARAGRAPH = 500;

    public Paragraph(final int id, final List<Jump> jumps, final int paragraphTextId,
                     final Map<Paragraph.ActionTypes, Integer> actions) {
        this.id = id;
        this.jumps = jumps;
        this.textId = paragraphTextId;
        this.actions = actions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public List<Jump> getJumps() {
        return jumps;
    }

    public void setJumps(List<Jump> jumps) {
        this.jumps = jumps;
    }

    public Map<Paragraph.ActionTypes, Integer> getActions() {
        return actions;
    }

    public void setActions(Map<Paragraph.ActionTypes, Integer> actions) {
        this.actions = actions;
    }
}
