package com.npgames.insight.ui.book.inventory;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryPanelView extends FrameLayout {

    @BindView(R.id.inventory_panel_items_recycler_view)
    RecyclerView recyclerView;


    public InventoryPanelView(@NonNull Context context) {
        super(context);
        init(context, null);
        setWillNotDraw(false);
    }

    public InventoryPanelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
        setWillNotDraw(false);
    }

    public InventoryPanelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
        setWillNotDraw(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InventoryPanelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, null);
        setWillNotDraw(false);
    }

    private void init(final Context context, final AttributeSet attributeSet) {
        final View rootLayout = LayoutInflater.from(context).inflate(R.layout.view_inventory_panel, this, true);
        ButterKnife.bind(this, rootLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        final InventoryPanelAdapter inventoryPanelAdapter = new InventoryPanelAdapter(getContext());
        recyclerView.setAdapter(inventoryPanelAdapter);
    }
}
