package com.npgames.insight.ui.book;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Paragraph paragraphModel;
    private RecyclerViewListeners.OnItemClickListener clickListener;

    public PagerAdapter(final FragmentManager fm) {
        super(fm);

        paragraphModel = new Paragraph();
    }

    @Override
    public Fragment getItem(final int position) {
        return GamePageFragment.newInstance(paragraphModel.pages.get(position), clickListener);
    }

    @Override
    public int getCount() {
        return paragraphModel.pages.size();
    }

    public void update(final Paragraph paragraphModel) {
        this.paragraphModel = paragraphModel;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final RecyclerViewListeners.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
