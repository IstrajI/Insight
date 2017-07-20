package com.npgames.insight.ui.book.armory;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.npgames.insight.R;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.ui.all.adapters.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArmoryEquipmentAdapter extends BaseRecyclerAdapter<ArmoryEquipmentAdapter.ViewHolder> {

    private List<com.npgames.insight.data.model.equipment.Equipment> equipments;
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
        final String equipmentName = context.getResources().getString(equipment.getNameResource());

        int image = R.drawable.blaster;
        switch (position) {
            case 0: image = R.drawable.blaster;
                break;
            case 1: image = R.drawable.blaster2;
                break;
            case 2: image = R.drawable.blaster_black;
                break;
            case 3: image = R.drawable.heal_orange;
                break;
            case 4: image = R.drawable.blaster_orange;
                break;
            case 5 : image = R.drawable.granade_orange;
                break;
        }

        Glide.with(context).load(image).into(holder.pictureImageView);

        holder.textView.setText(equipmentName);
        if (equipments.get(position).getOwnedBy() == Equipment.Owner.ARRMORY) {
            holder.takeOnButton.setEnabled(true);
            holder.takeOutButton.setEnabled(false);
            return;
        }
        holder.takeOnButton.setEnabled(false);
        holder.takeOutButton.setEnabled(true);

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

        public ViewHolder(View itemView) {
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
                    Log.d("posj", ""+context.getResources().getDisplayMetrics().density);
                    Log.d("dsa", ""+pictureImageView.getHeight() +" " +pictureImageView.getWidth());
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;
                case R.id.button_equipment_take_on:
                    this.takeOutButton.setEnabled(true);
                    this.takeOnButton.setEnabled(false);
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);

                    break;
                case R.id.button_equipment_take_out:
                    this.takeOnButton.setEnabled(true);
                    this.takeOutButton.setEnabled(false);
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;
            }
        }
    }
}
