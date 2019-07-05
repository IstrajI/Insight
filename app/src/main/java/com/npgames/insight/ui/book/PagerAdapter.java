package com.npgames.insight.ui.book;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.View;

import com.arellomobile.mvp.MvpDelegate;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private MvpDelegate mvpDelegate;
    private Paragraph paragraphModel;
    private RecyclerViewListeners.OnItemClickListener itemClickListener;
    private View.OnClickListener clickListener;
    private ICreatePlayer createPlayerConsumeCallback;
    private IDirectoryOpener directoryOpener;

    public PagerAdapter(final FragmentManager fm, final MvpDelegate mvpDelegate) {
        super(fm);

        this.mvpDelegate = mvpDelegate;
        paragraphModel = new Paragraph();
    }

    @Override
    public Fragment getItem(final int position) {
        return GamePageFragment.newInstance(paragraphModel.pages.get(position), itemClickListener, mvpDelegate, clickListener, createPlayerConsumeCallback, directoryOpener);
    }

    @Override
    public int getCount() {
        return paragraphModel.pages.size();
    }

    public void update(final Paragraph paragraphModel) {
        this.paragraphModel = paragraphModel;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final RecyclerViewListeners.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    public void setClickListener(final View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setCreatePlayerConsumeCallback(ICreatePlayer createPlayerConsumeCallback) {
        this.createPlayerConsumeCallback = createPlayerConsumeCallback;
    }

    public void setDirectoryOpenerListener(final IDirectoryOpener directoryOpener) {
        this.directoryOpener = directoryOpener;
    }
}
