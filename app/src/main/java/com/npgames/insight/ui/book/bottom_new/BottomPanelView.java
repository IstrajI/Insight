package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
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

public class BottomPanelView extends RelativeLayout implements View.OnClickListener, InventoryPanelAdapter.InventoryPanelListener {
    @BindView(R.id.button_bottom_panel_find)
    protected ImageView findButton;
    @BindView(R.id.button_bottom_panel_station)
    protected ImageView stationButton;
    @BindView(R.id.button_bottom_panel_med_bay)
    protected ImageView medBayButton;
    @BindView(R.id.button_bottom_panel_armory)
    protected ImageView armoryButton;
    @BindView(R.id.button_bottom_panel_open_hide_inventory)
    protected Button openHideButton;
    @BindView(R.id.bottom_panel_actions_layout)
    protected ConstraintLayout actionsLayout;
    @BindView(R.id.bottom_panel_inventory)
    protected ConstraintLayout inventoryConstraintLayout;

    @BindView(R.id.bottom_panel_inventory_left_button_image_view)
    protected ImageView inventoryMoveLeftButtonImageView;
    @BindView(R.id.bottom_panel_right_button_image_view)
    protected ImageView inventoryMoveRightButtonImageView;
    @BindView(R.id.bottom_panel_inventory_left_wire_image_view)
    protected ImageView inventoryLeftWireImageView;
    @BindView(R.id.bottom_panel_right_wire_image_view)
    protected ImageView inventoryRightWireImageView;

    @BindView(R.id.bottom_panel_left_item_image_view)
    protected ImageView inventoryLeftItemImageView;
    @BindView(R.id.bottom_panel_middle_item_image_view)
    protected ImageView inventoryMiddleItemImageView;
    @BindView(R.id.bottom_panel_right_item_image_view)
    protected ImageView inventoryRightItemImageView;

    private InventoryPanelAdapter inventoryPanelAdapter;
    private BottomPanelClickListener onClickListener;
    private boolean isPanelOpen = false;

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

        inventoryPanelAdapter = new InventoryPanelAdapter(this);

        inventoryMoveLeftButtonImageView.setOnTouchListener(inventoryNavigationLeftButtonListener);
        inventoryMoveRightButtonImageView.setOnTouchListener(inventoryNavigationRightButtonListener);
        openHideButton.setOnTouchListener(inventoryButtonListener);

        findButton.setOnClickListener(this);
        stationButton.setOnClickListener(this);
        medBayButton.setOnClickListener(this);
        armoryButton.setOnClickListener(this);
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
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
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

    @Override
    public void updatePanel(final int leftItem, final int middleItem, final int rightItem) {
        if (leftItem != -1) {
            inventoryLeftItemImageView.setImageDrawable(getResources().getDrawable(leftItem));
        }

        if (middleItem != -1) {
            inventoryMiddleItemImageView.setImageDrawable(getResources().getDrawable(middleItem));
        }

        if (rightItem != -1) {
            inventoryRightItemImageView.setImageDrawable(getResources().getDrawable(rightItem));
        }
    }

    @Override
    public void blockLeftButton() {
        inventoryLeftItemImageView.setEnabled(false);
    }

    @Override
    public void blockRightButton() {
        inventoryRightItemImageView.setEnabled(false);
    }

    public void onOpen() {
        isPanelOpen = true;
    }

    public void onClose() {
        isPanelOpen = false;
    }

    public interface BottomPanelClickListener {
        void bottomPanelClick();
        void bottomPanelFindClick();
        void bottomPanelStationClick();
        void bottomPanelMedBayClick();
        void bottomPanelArmoryClick();
    }

    private final OnTouchListener inventoryNavigationLeftButtonListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final Drawable inventoryWireLeftActive = getResources().getDrawable(R.drawable.inventory_wire_left_active);
                    inventoryLeftWireImageView.setImageDrawable(inventoryWireLeftActive);

                    final Drawable inventoryLeftButtonActiveDrawable = getResources().getDrawable(R.drawable.inventory_left_active);
                    inventoryMoveLeftButtonImageView.setImageDrawable(inventoryLeftButtonActiveDrawable);
                    break;

                case MotionEvent.ACTION_UP:
                    view.performClick();
                    inventoryPanelAdapter.goLeft();

                case MotionEvent.ACTION_CANCEL:
                    final Drawable inventoryWireLeft = getResources().getDrawable(R.drawable.inventory_wire_left);
                    inventoryLeftWireImageView.setImageDrawable(inventoryWireLeft);

                    final Drawable inventoryLeftButtonDrawable = getResources().getDrawable(R.drawable.inventory_left);
                    inventoryMoveLeftButtonImageView.setImageDrawable(inventoryLeftButtonDrawable);

                    break;
            }
            return true;
        }
    };

    private final OnTouchListener inventoryNavigationRightButtonListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final Drawable inventoryLeftWireActiveDrawable = getResources().getDrawable(R.drawable.inventory_wire_right_active);
                    inventoryRightWireImageView.setImageDrawable(inventoryLeftWireActiveDrawable);

                    final Drawable inventoryRightButtonActiveDrawable = getResources().getDrawable(R.drawable.inventory_right_active);
                    inventoryMoveRightButtonImageView.setImageDrawable(inventoryRightButtonActiveDrawable);
                    break;

                case MotionEvent.ACTION_UP:
                    view.performClick();
                    inventoryPanelAdapter.goRight();

                case MotionEvent.ACTION_CANCEL:
                    final Drawable inventoryRightWireeDrawable = getResources().getDrawable(R.drawable.inventory_wire_right);
                    inventoryRightWireImageView.setImageDrawable(inventoryRightWireeDrawable);

                    final Drawable inventoryRightButtonDrawable = getResources().getDrawable(R.drawable.inventory_right);
                    inventoryMoveRightButtonImageView.setImageDrawable(inventoryRightButtonDrawable);

                    break;
            }
            return true;
        }
    };

    private final OnTouchListener inventoryButtonListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_UP:
                    onClickListener.bottomPanelClick();
                    view.performClick();

                case MotionEvent.ACTION_CANCEL:
                    if (isPanelOpen) {
                        final Drawable openHideButtonDefaultOpenState = getResources().getDrawable(R.drawable.top_reworked_open_pos);
                        actionsLayout.setBackground(openHideButtonDefaultOpenState);
                    } else {
                        final Drawable openHideButtonDefaultState = getResources().getDrawable(R.drawable.top_reworked_2);
                        actionsLayout.setBackground(openHideButtonDefaultState);
                    }
                    break;

                case MotionEvent.ACTION_DOWN:
                    if (isPanelOpen) {
                        final Drawable openHideButtonDefaultOpenState = getResources().getDrawable(R.drawable.top_reworked_open_pos_active_v4);
                        actionsLayout.setBackground(openHideButtonDefaultOpenState);
                    } else {
                        final Drawable openHideButtonDefaultState = getResources().getDrawable(R.drawable.top_reworked_close_pos_active);
                        actionsLayout.setBackground(openHideButtonDefaultState);
                    }
                    break;
            }
            return true;
        }
    };
}
