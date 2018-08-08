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

public class InventoryPanelAdapter extends BaseRecyclerAdapter<InventoryPanelAdapter.ViewHolder> {
    private List<Equipment> equipments = new ArrayList<>();
    private Context context;

    public InventoryPanelAdapter(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(final @NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_inventory_item,  viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder viewHolder, final int i) {
        int image = R.drawable.blaster;
        final Equipment equipment = equipments.get(i);

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

        Glide.with(context).load(image).into(viewHolder.equipmentImageView);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_inventory_item)
        ImageView equipmentImageView;

        ViewHolder(final @NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
