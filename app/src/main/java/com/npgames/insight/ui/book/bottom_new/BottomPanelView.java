package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.book.page.GamePageAdapter;

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
    protected Button openHideButton;

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

    /*final Drawable armoryPressedDrawable = getResources().getDrawable(R.drawable.action_armory_pressed);
    final Drawable armoryDefaultState = getResources().getDrawable(R.drawable.action_armory_7);

    private OnTouchListener armoryTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final ImageView armoryImageView = (ImageView) view;

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    Log.d("TestPishGg","ActionCalncel");
                    armoryImageView.setImageDrawable(armoryDefaultState);
                    break;

                case MotionEvent.ACTION_DOWN:
                    Log.d("TestPishGg","ActionDown");
                    armoryImageView.setImageDrawable(armoryPressedDrawable);
                    break;

                case MotionEvent.ACTION_UP:
                    Log.d("TestPishGg","ActionUp");
                    //onClickListener.bottomPanelArmoryClick();
                    break;
            }
            return true;
        }
    };*/

    private void init(final Context context, final AttributeSet attrs) {
        final View rootLayout = LayoutInflater.from(context).inflate(R.layout.view_bottom_panel, this, true);
        ButterKnife.bind(this, rootLayout);

        //armoryButton.setOnTouchListener(armoryTouchListener);

        openHideButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
        stationButton.setOnClickListener(this);
        medBayButton.setOnClickListener(this);
        //armoryButton.setOnClickListener(this);

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
        Log.d("TestPishGG", "Eqipmentsize = " +equipments.size());
        inventoryPanelAdapter.update(equipments);
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
