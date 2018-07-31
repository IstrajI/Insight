package com.npgames.insight.data.model.new_model;

import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Влад on 05.11.2017.
 */

public class Paragraph {
    public int paragraphNumber;
    public boolean wasActionPressed;
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

    public List<BlockButton> getJumps() {
        final List<BlockArea> blockAreas = getBlockAreas();
        final List<BlockButton> blockJumps = new ArrayList();

        for (final BlockArea blockArea: blockAreas) {
            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                blockJumps.add((BlockButton) blockArea);
            }
        }

        return blockJumps;
    }

    public List<BlockAction> getActions() {
        final List<BlockArea> blockAreas = getBlockAreas();
        final List<BlockAction> blockActions = new ArrayList();

        for (final BlockArea blockArea: blockAreas) {
            if (blockArea.type == BlockArea.BlockType.ACTION) {
                blockActions.add((BlockAction) blockArea);
            }
        }

        return blockActions;
    }

    public boolean hasActions() {
        return getActions().size() > 0;
    }
}
