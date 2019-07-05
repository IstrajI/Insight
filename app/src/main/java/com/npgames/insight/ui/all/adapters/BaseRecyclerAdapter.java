package com.npgames.insight.ui.all.adapters;

import android.content.res.Resources;

import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected RecyclerViewListeners.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(RecyclerViewListeners.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
