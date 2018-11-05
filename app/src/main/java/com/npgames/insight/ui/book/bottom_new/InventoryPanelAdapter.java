package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.util.Log;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.model.Player;

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

        listener.updatePanelLeft(getDrawable(leftItemPosition));
        listener.updatePanelMiddle(getDrawable(middleItemPosition));
        listener.updatePanelRight(getDrawable(rightItemPosition));

        checkVisualState();
    }

    public void goRight() {
        leftItemPosition++;
        middleItemPosition++;
        rightItemPosition++;

        listener.updatePanelLeft(getDrawable(leftItemPosition));
        listener.updatePanelMiddle(getDrawable(middleItemPosition));
        listener.updatePanelRight(getDrawable(rightItemPosition));

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

    private int getDrawable(final int itemPosition) {
        if (itemPosition > equipments.size()) {
            return -1;
        }

        int image = R.drawable.blaster;
        final Equipment equipment = equipments.get(itemPosition);

        switch (equipment.getType()) {
            case Equipment.TYPE.BLASTER:
                image = R.drawable.blaster3;
                break;

            case Equipment.TYPE.BEAM:
                image = R.drawable.laser_2;
                break;

            case Equipment.TYPE.ELECTROSHOCK:
                image = R.drawable.shoker_2;
                break;

            case Equipment.TYPE.AID_KIT:
                image = R.drawable.medkit_3;
                break;

            case Equipment.TYPE.OPEN_SPACE_EQUIPMENT:
                image = R.drawable.helmet_11_xxx;
                break;

            case Equipment.TYPE.GRENADE:
                image = R.drawable.grenade_1;
                break;

            case Equipment.TYPE.FLAK_JACKET:
                image = R.drawable.jaket;
                break;

            case Equipment.TYPE.POWER_SHIELD:
                image = R.drawable.powershiled_8;
                break;

            case Equipment.TYPE.TARGETTER:
                image = R.drawable.powershiled_8;
                break;
        }

        return image;
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
            listener.updatePanelLeft(this.equipments.get(leftItemPosition).getDrawable());

            listener.hideEmptyInventoryMessage();
        } else {
            listener.showEmptyInventoryMessage();
        }

        if (equipments.size() > 1) {
            middleItemPosition = 1;
            listener.updatePanelMiddle(this.equipments.get(middleItemPosition).getDrawable());
        }

        if (equipments.size() > 2) {
            rightItemPosition = 2;
            listener.updatePanelRight(this.equipments.get(rightItemPosition).getDrawable());
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
