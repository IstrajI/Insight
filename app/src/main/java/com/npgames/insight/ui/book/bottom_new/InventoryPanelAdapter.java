package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;

import com.npgames.insight.data.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class InventoryPanelAdapter {
    private List<Equipment> equipments = new ArrayList<>();
    private Context context;

    private int leftItemPosition;
    private int middleItemPosition;
    private int rightItemPosition;

    private InventoryPanelListener listener;

    InventoryPanelAdapter(final InventoryPanelListener inventoryPanelListener) {
        listener = inventoryPanelListener;
    }

    public void goLeft() {
        leftItemPosition--;
        middleItemPosition--;
        rightItemPosition--;

        listener.updatePanelLeft(equipments.get(leftItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        listener.updatePanelMiddle(equipments.get(middleItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        listener.updatePanelRight(equipments.get(rightItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));

        checkVisualState();
    }

    public void goRight() {
        leftItemPosition++;
        middleItemPosition++;
        rightItemPosition++;

        listener.updatePanelLeft(equipments.get(leftItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        listener.updatePanelMiddle(equipments.get(middleItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        listener.updatePanelRight(equipments.get(rightItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));

        checkVisualState();
    }

    private void checkVisualState() {
        if (leftItemPosition == 0) {
            listener.blockLeftButton();
        } else {
            listener.unblockLeftButton();
        }

        if (rightItemPosition == equipments.size() - 1 || rightItemPosition == 0){
            listener.blockRightButton();
        } else {
            listener.unblockRightButton();
        }
    }

    public void update(final List<Equipment> equipments) {
        this.equipments.clear();
        this.equipments.addAll(equipments);

        this.leftItemPosition = 0;
        this.middleItemPosition = 0;
        this.rightItemPosition = 0;

        listener.resetPanelDrawables();

        if (equipments.size() > 0) {
            leftItemPosition = 0;
            listener.updatePanelLeft(this.equipments.get(leftItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));

            listener.hideEmptyInventoryMessage();
        } else {
            listener.showEmptyInventoryMessage();
        }

        if (equipments.size() > 1) {
            middleItemPosition = 1;
            listener.updatePanelMiddle(this.equipments.get(middleItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        }

        if (equipments.size() > 2) {
            rightItemPosition = 2;
            listener.updatePanelRight(this.equipments.get(rightItemPosition).getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR));
        }

        checkVisualState();
    }

    public Equipment getLeftItemPosition() {
        return equipments.get(leftItemPosition);
    }

    public Equipment getRightItemPosition() {
        return equipments.get(rightItemPosition);
    }

    public Equipment getMiddleItemPosition() {
        return equipments.get(middleItemPosition);
    }

    public interface InventoryPanelListener {
        //void updatePanel(final int leftItem, final int middleItem, final int rightItem);
        void updatePanelLeft(final int leftDrawable);
        void updatePanelMiddle(final int middleDrawable);
        void updatePanelRight(final int rightDrawable);
        void showEmptyInventoryMessage();
        void hideEmptyInventoryMessage();
        void resetPanelDrawables();
        void blockLeftButton();
        void blockRightButton();
        void unblockLeftButton();
        void unblockRightButton();
    }
}
