package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;

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
    @BindView(R.id.bottom_panel_empty_inventory_text_view)
    protected TextView inventoryNoItemsTextView;

    private InventoryPanelAdapter inventoryPanelAdapter;
    private BottomPanelListener onClickListener;
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
        findButton.setEnabled(false);

        findButton.setOnClickListener(this);
        stationButton.setOnClickListener(this);
        medBayButton.setOnClickListener(this);
        armoryButton.setOnClickListener(this);
    }

    public void addClickListener(final BottomPanelListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void moveYTo(final float y) {
        setY(y);
        invalidate();
    }

    public void updateEquipment(final List<Equipment> equipments) {
        inventoryPanelAdapter.update(equipments);
    }

    public void showEmptyInventoryMessage() {
        inventoryNoItemsTextView.setVisibility(View.VISIBLE);
    }

    public void hideEmptyInventoryMessage() {
        inventoryNoItemsTextView.setVisibility(View.GONE);
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

    OnClickListener onLeftClickListener = new OnClickListener() {
        @Override
        public void onClick(final View view) {
            onClickListener.bottomPanelShowItemInfo(inventoryPanelAdapter.getLeftItemPosition());
        }
    };

    OnClickListener onMiddleClickListener = new OnClickListener() {
        @Override
        public void onClick(final View view) {
            onClickListener.bottomPanelShowItemInfo(inventoryPanelAdapter.getMiddleItemPosition());
        }
    };

    OnClickListener onRightClickListener = new OnClickListener() {
        @Override
        public void onClick(final View view) {
            onClickListener.bottomPanelShowItemInfo(inventoryPanelAdapter.getRightItemPosition());
        }
    };

    @Override
    public void updatePanelLeft(int leftDrawable) {
        inventoryLeftItemImageView.setVisibility(View.VISIBLE);
        inventoryLeftItemImageView.setImageDrawable(getResources().getDrawable(leftDrawable));
        inventoryLeftItemImageView.setOnClickListener(onLeftClickListener);
    }

    @Override
    public void updatePanelMiddle(int middleDrawable) {
        inventoryMiddleItemImageView.setVisibility(View.VISIBLE);
        inventoryMiddleItemImageView.setImageDrawable(getResources().getDrawable(middleDrawable));
        inventoryMiddleItemImageView.setOnClickListener(onMiddleClickListener);
    }

    @Override
    public void updatePanelRight(int rightDrawable) {
        inventoryRightItemImageView.setVisibility(View.VISIBLE);
        inventoryRightItemImageView.setImageDrawable(getResources().getDrawable(rightDrawable));
        inventoryRightItemImageView.setOnClickListener(onRightClickListener);
    }

    @Override
    public void resetPanelDrawables() {
        inventoryLeftItemImageView.setVisibility(View.INVISIBLE);
        inventoryMiddleItemImageView.setVisibility(View.INVISIBLE);
        inventoryRightItemImageView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void blockLeftButton() {
        inventoryMoveLeftButtonImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_left_inactive));
        inventoryLeftWireImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_wire_left_inactive));
        inventoryMoveLeftButtonImageView.setEnabled(false);
    }

    @Override
    public void blockRightButton() {
        inventoryMoveRightButtonImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_right_inactive));
        inventoryRightWireImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_wire_right_inactive));
        inventoryMoveRightButtonImageView.setEnabled(false);

    }

    @Override
    public void unblockLeftButton() {
        inventoryMoveLeftButtonImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_left));
        inventoryLeftWireImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_wire_left));

        inventoryMoveLeftButtonImageView.setEnabled(true);
    }

    @Override
    public void unblockRightButton() {
        inventoryMoveRightButtonImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_right));
        inventoryRightWireImageView.setImageDrawable(getResources().getDrawable(R.drawable.inventory_wire_right));

        inventoryMoveRightButtonImageView.setEnabled(true);
    }

    public void onOpen() {
        isPanelOpen = true;
    }

    public void onClose() {
        isPanelOpen = false;
    }

    public void setAvailableAllActionsState() {
        findButton.setEnabled(true);
        armoryButton.setEnabled(true);
        medBayButton.setEnabled(true);
        stationButton.setEnabled(true);
    }

    public void setAvailableFindActionsState() {
        findButton.setEnabled(true);
        armoryButton.setEnabled(false);
        medBayButton.setEnabled(false);
        stationButton.setEnabled(false);
    }

    public void setDisabledAllActionState() {
        findButton.setEnabled(false);
        armoryButton.setEnabled(false);
        medBayButton.setEnabled(false);
        stationButton.setEnabled(false);
    }

    public void setDisabledArmoryActionsState() {
        findButton.setEnabled(true);
        armoryButton.setEnabled(false);
        medBayButton.setEnabled(true);
        stationButton.setEnabled(true);
    }

    public void setDisabledMedBayActionsState() {
        findButton.setEnabled(true);
        armoryButton.setEnabled(true);
        medBayButton.setEnabled(false);
        stationButton.setEnabled(true);
    }

    public interface BottomPanelListener {
        void bottomPanelClick();
        void bottomPanelFindClick();
        void bottomPanelStationClick();
        void bottomPanelMedBayClick();
        void bottomPanelArmoryClick();
        void bottomPanelShowItemInfo(final Equipment equipment);
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
