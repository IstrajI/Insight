package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

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
        if (leftItemPosition > 0) {
            leftItemPosition--;
            middleItemPosition--;
            rightItemPosition--;

            final int leftImage = getDrawable(leftItemPosition);
            final int middleImage = getDrawable(middleItemPosition);
            final int rightImage = getDrawable(rightItemPosition);

            listener.updatePanel(leftImage, middleImage, rightImage);
        } else {
            listener.blockLeftButtom();
        }
    }

    public void goRight() {
        if (rightItemPosition < equipments.size() - 1) {
            leftItemPosition++;
            middleItemPosition++;
            rightItemPosition++;

            final int leftImage = getDrawable(leftItemPosition);
            final int middleImage = getDrawable(middleItemPosition);
            final int rightImage = getDrawable(rightItemPosition);

            listener.updatePanel(leftImage, middleImage, rightImage);
        } else {
            listener.blockRightButton();
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

            case Equipment.TYPE.FlAK_JACKET:
                image = R.drawable.jaket;
                break;

            case Equipment.TYPE.POWER_SHIELD:
                image = R.drawable.powershiled_8;
                break;

            case Equipment.TYPE.TARGETTER:
                image = R.drawable.targetter_4_xxx;
                break;
        }

        return image;
    }

    public void update(final List<Equipment> equipments) {
        this.equipments.clear();
        this.equipments.addAll(equipments);

        leftItemPosition = 0;
        middleItemPosition = 1;
        rightItemPosition = 2;

        final int leftImage = getDrawable(leftItemPosition);
        final int middleImage = getDrawable(middleItemPosition);
        final int rightImage = getDrawable(rightItemPosition);

        listener.updatePanel(leftImage, middleImage, rightImage);

/*        if (equipments.size() >= 3) {
            leftItemPosition = 0;
            middleItemPosition = 1;
            rightItemPosition = 2;
        } else if (equipments.size() >= 2){
            leftItemPosition = 0;
            middleItemPosition = 1;
        } else if (equipments.size() >= 1) {
            leftItemPosition = 0;
        }*/
    }

    public interface InventoryPanelListener {
        void updatePanel(final int leftItem, final int middleItem, final int rightItem);
        void blockLeftButtom();
        void blockRightButton();
    }
}
