package com.npgames.insight.ui.all.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.npgames.insight.ui.book.page.GamePageAdapter;

public class RecyclerViewListeners {

    public interface OnItemClickListener{
        void onItemClick(View view, int position, RecyclerView.Adapter adapter);
        void onItemPress(View view, int adapterPosition, GamePageAdapter gamePageAdapter);
        void onItemRelease(View view, int adapterPosition, GamePageAdapter gamePageAdapter);
    }
}
