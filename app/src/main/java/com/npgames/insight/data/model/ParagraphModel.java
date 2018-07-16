package com.npgames.insight.data.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 22.10.2017.
 */

public class ParagraphModel {
    private List<String> pages;
    private List<List<Jump>> pagJumps;

    public final static int INIT_PARAGRAPH = 500;

    public ParagraphModel(final List<List<Jump>> pagJumps, final List<String> pages) {
        this.pages = pages;
        this.pagJumps = pagJumps;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public List<List<Jump>> getPagJumps() {
        return pagJumps;
    }

    public void setPagJumps(List<List<Jump>> pagJumps) {
        this.pagJumps = pagJumps;
    }
}
