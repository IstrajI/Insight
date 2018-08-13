package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomPanelView extends RelativeLayout implements View.OnClickListener{
    @BindView(R.id.button_bottom_panel_find)
    protected ImageView findButton;
    @BindView(R.id.button_bottom_panel_station)
    protected ImageView stationButton;
    @BindView(R.id.button_bottom_panel_med_bay)
    protected ImageView medBayButton;
    @BindView(R.id.button_bottom_panel_armory)
    protected ImageView armoryButton;
    @BindView(R.id.inventory_panel_items_recycler_view)
    protected RecyclerView itemsRecyclerView;
    @BindView(R.id.button_bottom_panel_open_hide_inventory)
    protected ImageView openHideButton;

    private InventoryPanelAdapter inventoryPanelAdapter;
    private BottomPanelClickListener onClickListener;

    public BottomPanelView(Context context) {
        this(context, null);
    }

    public BottomPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        final View rootLayout = LayoutInflater.from(context).inflate(R.layout.view_bottom_panel, this, true);
        ButterKnife.bind(this, rootLayout);

        openHideButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
        stationButton.setOnClickListener(this);
        medBayButton.setOnClickListener(this);
        armoryButton.setOnClickListener(this);

        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        inventoryPanelAdapter = new InventoryPanelAdapter(getContext());
        itemsRecyclerView.setAdapter(inventoryPanelAdapter);
    }

    public void addClickListener(final BottomPanelClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void moveYTo(final float y) {
        setY(y);
        invalidate();
    }

    public void updateEquipment(final List<Equipment> equipments) {
        inventoryPanelAdapter.update(equipments);
        Log.d("TestPish", "equipments = "+ equipments.size());
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_bottom_panel_open_hide_inventory:
                onClickListener.bottomPanelClick();
                break;

            case R.id.button_bottom_panel_find:
                onClickListener.bottomPanelFindClick();
                break;

            case R.id.button_bottom_panel_station:
                onClickListener.bottomPanelStationClick();
                break;

            case R.id.button_bottom_panel_med_bay:
                onClickListener.bottomPanelMedBayClick();
                break;

            case R.id.button_bottom_panel_armory:
                onClickListener.bottomPanelArmoryClick();
                break;
        }
    }

    public interface BottomPanelClickListener {
        void bottomPanelClick();
        void bottomPanelFindClick();
        void bottomPanelStationClick();
        void bottomPanelMedBayClick();
        void bottomPanelArmoryClick();
    }
}
