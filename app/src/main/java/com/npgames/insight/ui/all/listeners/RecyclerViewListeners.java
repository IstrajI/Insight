package com.npgames.insight.ui.all.listeners;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewListeners {

    public interface OnItemClickListener{
        void onItemClick(View view, int position, RecyclerView.Adapter adapter);
    }
}
