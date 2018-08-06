package com.npgames.insight.ui.book;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.new_model.Page;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.book.page.GamePageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamePageFragment extends Fragment {

    @BindView(R.id.recycler_view_adapter_page)
    protected RecyclerView pageRecyclerView;
    @BindView(R.id.text_view_game_book_measuring)
    protected TextView test;

    private static final String PAGES = "PAGES";
    private Page page;
    private static RecyclerViewListeners.OnItemClickListener onItemClickListener;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        Log.d("TestPish", "GamePageFragment");
        super.onCreate(savedInstanceState);

        page = (Page) getArguments().getSerializable(PAGES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_adapter_page_item, null);
        ButterKnife.bind(this, view);

        GamePageAdapter blocksAdapter = new GamePageAdapter(getResources());
        blocksAdapter.setOnItemClickListener(onItemClickListener);

        pageRecyclerView.setAdapter(blocksAdapter);
        pageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        blocksAdapter.update(page.blocks);
        return view;
    }

    public static GamePageFragment newInstance(final Page pages,
                                               final RecyclerViewListeners.OnItemClickListener clickListener) {
        onItemClickListener = clickListener;

        final GamePageFragment gamePageFragment = new GamePageFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PAGES, pages);
        gamePageFragment.setArguments(arguments);
        return gamePageFragment;
    }
}
