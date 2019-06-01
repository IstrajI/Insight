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
        holder.pictureImageView.setImageResource(equipment.getDrawable(Equipment.DRAWABLE_COLOR_MODEL.DEFAULT));

        if (!equipment.getCanWear()) {
            holder.wearButtonTextView.setText("НАДЕТЬ");
            holder.itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_test_disabled));
            holder.itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text_disabled));
        } else if (equipment.getOwnedBy().equals(Equipment.Owner.ARRMORY) ) {
            holder.wearButtonTextView.setText("НАДЕТЬ");
            holder.itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_test_pish));
            holder.itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text));
        } else if (equipment.getOwnedBy().equals(Equipment.Owner.PLAYER)) {
            holder.wearButtonTextView.setText("СНЯТЬ");
            holder.itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_test_pish_taked_on));
            holder.itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text_taked_on));
        } else if (equipment.getOwnedBy().equals(Equipment.Owner.TRASH)) {
            holder.wearButtonTextView.setText("УТЕРЯН");
            holder.wearButtonTextView.setEnabled(false);
            holder.itemBottomBackgroundLayout.setBackground(resources.getDrawable(R.drawable.armory_item_dropped));
            holder.itemWearButtonLayout.setBackground(resources.getDrawable(R.drawable.armory_item_text_taked_on));

        }
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemBottomBackgroundLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.image_view_equipment_item_picture:
                    wearable.showDetailedInfo(equipments.get(getAdapterPosition()));
                    break;

                case R.id.equipment_item_bottom_background_layout:
                    final Equipment clickedEquipment = equipments.get(getAdapterPosition());
                    wearable.takeOnOffEquipment(clickedEquipment);
                    notifyItemChanged(getAdapterPosition());
            }
        }
    }


    public interface Wearable {
        void takeOnOffEquipment(final Equipment equipment);
        void showDetailedInfo(final Equipment equipment);
    }
}
