package com.npgames.insight.ui.all.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewListeners {

    public interface OnItemClickListener{
        void onItemClick(View view, int position, RecyclerView.Adapter adapter);
    }
}
