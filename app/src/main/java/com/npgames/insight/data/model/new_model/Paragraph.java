package com.npgames.insight.data.model.new_model;

import com.npgames.insight.data.model.BlockArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 05.11.2017.
 */

public class Paragraph {
    public final List<Page> pages;

    public Paragraph() {
        pages = new ArrayList<>();
    }

    public Paragraph(final List<Page> pages) {
        this.pages = pages;
    }

    public List<BlockArea> getBlockAreas() {
        final List<BlockArea> blockAreas = new ArrayList<>();

        for (Page page : pages) {
            blockAreas.addAll(page.blocks);
        }

        return blockAreas;
    }
}
