package com.npgames.insight.ui.book.armory;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    ArmoryEquipmentAdapter() {
        this.equipments = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_equipment_item, parent, false);
        return new ArmoryEquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(String.valueOf(equipments.get(position).getType()));
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            takeOnButton.setOnClickListener(this);
            takeOutButton.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.button_equipment_take_on:
                    this.takeOutButton.setEnabled(true);
                    this.takeOnButton.setEnabled(false);
                    onItemClickListener.onItemClick(v, getAdapterPosition(), ArmoryEquipmentAdapter.this);
                    break;
                case R.id.button_equipment_take_out:
                    this.takeOnButton.setEnabled(true);
                    this.takeOutButton.setEnabled(false);
                    break;
            }
        }
    }
}
