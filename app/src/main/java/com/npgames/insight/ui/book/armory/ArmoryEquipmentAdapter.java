package com.npgames.insight.ui.book.armory;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArmoryEquipmentAdapter extends BaseRecyclerAdapter<ArmoryEquipmentAdapter.ViewHolder> {

    private List<Equipment> equipments;
    private Context context;
    private Wearable wearable;
    private Resources resources;

    ArmoryEquipmentAdapter(final Context context) {
        this.equipments = new ArrayList<>();
        this.context = context;
        this.resources = context.getResources();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_equipment_item, parent, false);
        return new ArmoryEquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Equipment equipment = equipments.get(position);
        holder.textView.setText(equipment.getName().toUpperCase());

        int image = R.drawable.blaster;

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
                image = R.drawable.powershiled_8 ;
                break;

            case Equipment.TYPE.TARGETTER:
                image = R.drawable.targetter_4_xxx;
                break;
        }

        holder.pictureImageView.setImageDrawable(resources.getDrawable(image));
    }

    @Override
    public int getItemCount() {
        return equipments.size();
    }

    public void update(final List<Equipment> equipments) {
        this.equipments.clear();
        this.equipments.addAll(equipments);
        notifyDataSetChanged();
    }

    public void setWearable(final Wearable wearable) {
        this.wearable = wearable;
    }

    public Equipment getEquipmentByPosition(final int position) {
        return equipments.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.equipment_item_title_text_view)
        protected TextView textView;
        @BindView(R.id.image_view_equipment_item_picture)
        protected ImageView pictureImageView;
        @BindView(R.id.equipment_item_wear_button_text_view)
        protected TextView wearButtonTextView;
        @BindView(R.id.equipment_item_bottom_background_layout)
        protected LinearLayout itemBottomBackgroundLayout;
        @BindView(R.id.equipment_item_wear_button_layout)
        protected FrameLayout itemWearButtonLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            pictureImageView.setOnClickListener(this);
            textView.setOnClickListener(this);
            wearButtonTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.image_view_equipment_item_picture:
                    wearable.showDetailedInfo(equipments.get(getAdapterPosition()));
                    break;

                case R.id.equipment_item_wear_button_text_view:
                    final Equipment clickedEquipment = equipments.get(getAdapterPosition());
                    if (clickedEquipment.getOwnedBy().equals(Equipment.Owner.PLAYER)) {
                        wearButtonTextView.setText("Надеть");
                        pictureImageView.setImageDrawable(resources.getDrawable(R.drawable.laser_2));
                        itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_test_pish));
                        itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text));
                    } else if (clickedEquipment.getOwnedBy().equals(Equipment.Owner.ARRMORY)) {
                        wearButtonTextView.setText("Снять");
                        pictureImageView.setImageDrawable(resources.getDrawable(R.drawable.laser_taked_on_test));
                        itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_test_pish_taked_on));
                        itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text_taked_on));
                    }

                    wearable.takeOnOffEquipment(clickedEquipment);
/*                case R.id.button_equipment_take_on:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);

                    break;
                case R.id.button_equipment_take_out:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;*/
            }
        }
    }


    public interface Wearable {
        void takeOnOffEquipment(final Equipment equipment);
        void showDetailedInfo(final Equipment equipment);
    }
}
