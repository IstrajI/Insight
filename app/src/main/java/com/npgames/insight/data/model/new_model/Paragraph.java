package com.npgames.insight.data.model.new_model;

import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 05.11.2017.
 */

public class Paragraph {
    @Retention(RetentionPolicy.SOURCE)
    public @interface AvailableActions {
        String DISABLED_ALL = "DISABLED_ALL";
        String AVAILABLE_ALL = "AVAILABLE_ALL";
        String AVAILABLE_FIND = "AVAILABLE_FIND";
        String DISABLED_ARMORY = "DISABLED_ARMORY";
        String DISABLED_MEDBAY = "DISABLED_MEDBAY";
    }

    public int paragraphNumber;
    public boolean wasActionPressed;
    public final List<Page> pages;
    public @AvailableActions String availableState;

    public Paragraph() {
        pages = new ArrayList<>();
    }

    public Paragraph(final List<Page> pages) {
        this.pages = pages;
    }

    public boolean isEmpty() {
        return pages == null || pages.isEmpty();
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

        for (final BlockArea blockArea : blockAreas) {
            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                blockJumps.add((BlockButton) blockArea);
            }
        }

        return blockJumps;
    }

    public List<BlockAction> getActions() {
        final List<BlockAction> blockActions = new ArrayList<>();

        for (final BlockArea blockArea : getBlockAreas()) {
            if (blockArea.type == BlockArea.BlockType.ACTION) {
                blockActions.add((BlockAction) blockArea);
            }
        }

        return blockActions;
    }

    public boolean hasActions() {
        for (final BlockArea blockArea : getBlockAreas()) {
            if (blockArea.type == BlockArea.BlockType.ACTION || blockArea.type == BlockArea.BlockType.CREATE_PLAYER_DEX) {
                return true;
            }
        }

        return false;
    }
}
