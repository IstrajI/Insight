package com.npgames.insight.ui.book;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.npgames.insight.R;
import com.npgames.insight.data.model.new_model.Page;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.book.page.GamePageAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GamePageFragment extends Fragment {
    private static final String PAGES = "PAGES";

    @BindView(R.id.recycler_view_adapter_page)
    protected RecyclerView pageRecyclerView;
    @BindView(R.id.text_view_game_book_measuring)
    protected TextView test;

    private static MvpDelegate mvpDelegate;

    private Page page;
    private static RecyclerViewListeners.OnItemClickListener onItemClickListener;
    private static View.OnClickListener onClickListener;
    private static ICreatePlayer onCreatePlayerConsumeCallback;
    private static IDirectoryOpener directoryOpenerListener;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = (Page) getArguments().getSerializable(PAGES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_adapter_page_item, null);
        ButterKnife.bind(this, view);

        GamePageAdapter blocksAdapter = new GamePageAdapter(getResources(), mvpDelegate);
        blocksAdapter.setOnItemClickListener(onItemClickListener);
        blocksAdapter.setClickListener(onClickListener);
        blocksAdapter.setCreatePlayerListener(onCreatePlayerConsumeCallback);
        blocksAdapter.setDirectoryOpenerListener(directoryOpenerListener);

        pageRecyclerView.setAdapter(blocksAdapter);
        pageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        blocksAdapter.update(page.blocks);
        return view;
    }

    public static GamePageFragment newInstance(final Page pages,
                                               final RecyclerViewListeners.OnItemClickListener itemClickListener,
                                               final MvpDelegate parentMvpDelegate,
                                               final View.OnClickListener clickListener, ICreatePlayer createPlayerConsumeCallback,
                                               final IDirectoryOpener directoryOpener) {
        directoryOpenerListener = directoryOpener;
        onItemClickListener = itemClickListener;
        onClickListener = clickListener;
        onCreatePlayerConsumeCallback = createPlayerConsumeCallback;

        mvpDelegate = parentMvpDelegate;

        final GamePageFragment gamePageFragment = new GamePageFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PAGES, pages);
        gamePageFragment.setArguments(arguments);
        return gamePageFragment;
    }
}
