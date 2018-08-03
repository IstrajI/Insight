package com.npgames.insight.ui.book.armory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    ArmoryEquipmentAdapter(final Context context) {
        this.equipments = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_equipment_item, parent, false);
        return new ArmoryEquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Equipment equipment = equipments.get(position);
        holder.textView.setText(equipment.getName());

        if (Equipment.Owner.ARRMORY.equals(equipment.getOwnedBy()) && equipment.getCanWear()) {
            holder.takeOnButton.setEnabled(true);
            holder.takeOutButton.setEnabled(false);
        } else if (Equipment.Owner.PLAYER.equals(equipment.getOwnedBy())) {
            holder.takeOnButton.setEnabled(false);
            holder.takeOutButton.setEnabled(true);
        } else {
            holder.takeOnButton.setEnabled(false);
            holder.takeOutButton.setEnabled(false);
        }

        int image = R.drawable.blaster;

        switch (equipment.getType()) {
            case Equipment.TYPE.BLASTER:
                image = R.drawable.blaster;
                break;

            case Equipment.TYPE.BEAM:
                image = R.drawable.laset_orange;
                break;

            case Equipment.TYPE.ELECTROSHOCK:
                image = R.drawable.blaster_black;
                break;

            case Equipment.TYPE.AID_KIT:
                image = R.drawable.heal_orange;
                break;

            case Equipment.TYPE.OPEN_SPACE_EQUIPMENT:
                image = R.drawable.blaster_orange;
                break;

            case Equipment.TYPE.GRENADE:
                image = R.drawable.granade_orange;
                break;

            case Equipment.TYPE.FlAK_JACKET:
                image = R.drawable.flack_jakcet_black;
                break;

            case Equipment.TYPE.POWER_SHIELD:
                image = R.drawable.flack_jakcet_orange;
                break;

            case Equipment.TYPE.TARGETTER:
                image = R.drawable.flack_jakcet_black;
                break;
        }

        Glide.with(context).load(image).into(holder.pictureImageView);
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

    public Equipment getEquipmentByPosition(final int position) {
        return equipments.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.text_view_equipment_item_name)
        protected TextView textView;
        @BindView(R.id.button_equipment_take_on)
        protected Button takeOnButton;
        @BindView(R.id.button_equipment_take_out)
        protected Button takeOutButton;
        @BindView(R.id.image_view_equipment_item_picture)
        protected ImageView pictureImageView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            takeOnButton.setOnClickListener(this);
            takeOutButton.setOnClickListener(this);
            pictureImageView.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.text_view_equipment_item_name:
                case R.id.image_view_equipment_item_picture:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;
                case R.id.button_equipment_take_on:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);

                    break;
                case R.id.button_equipment_take_out:
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;
            }
        }
    }
}
