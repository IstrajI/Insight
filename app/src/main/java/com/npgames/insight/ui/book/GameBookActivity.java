package com.npgames.insight.ui.book;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.npgames.insight.R;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.adapters.MyWebClient;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBookActivity extends BaseMvpActivity implements View.OnClickListener, RecyclerViewListeners.OnItemClickListener, GameBookView{
    @InjectPresenter
    GameBookPresenter gameBookPresenter;

    @BindView(R.id.scroll_view_game_book_root)
    protected NestedScrollView rootScrollView;
    @BindView(R.id.text_view_game_paragraph_text)
    protected TextView paragraphTextTextView;
    @BindView(R.id.recycler_view_paragraph_jumps)
    protected RecyclerView jumpsRecyclerView;
    @BindView(R.id.frame_layout_game_book_root)
    protected FrameLayout rootFrameLayout;
    @BindView(R.id.button_game_book_open_actions_menu)
    protected Button openActionsMenuButton;
    protected View actionsMenuLayout;
    protected Button closeActionsMenuButton;

    @BindView(R.id.web_view)
    protected WebView webView;

    private JumpsAdapter jumpsAdapter;
    private ActionsMenuAdapter actionsMenuAdapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebook);
    }

    @Override
    protected void bindViews() {
        jumpsAdapter = new JumpsAdapter(getApplicationContext());
        final LinearLayoutManager jumpsLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        jumpsRecyclerView.setLayoutManager(jumpsLayoutManager);
        jumpsRecyclerView.setAdapter(jumpsAdapter);
        jumpsAdapter.setOnItemClickListener(this);


        webView.setWebViewClient(new MyWebClient(getApplicationContext()));
        webView.getSettings().setJavaScriptEnabled(true);
        String htmlString = "\n" +
                "На внутреннем экране шлема исин отмечает маркерами направление. Целый ряд скал, похожих на клыки чудовищного монстра возвышаются гребнем через весь горизонт. Перепрыгивать через них даже с реактивным ранцем было бы рискован";

        webView.loadData(htmlString, "text/html; charset=utf-8", "UTF-8");

        createActionsMenu();
        gameBookPresenter.loadNextParagraph(getApplicationContext(), 1);
    }

    private void createActionsMenu() {
        actionsMenuLayout = LayoutInflater.from(this).inflate(R.layout.layout_actions_menu, rootFrameLayout, false);
        closeActionsMenuButton = ButterKnife.findById(actionsMenuLayout, R.id.button_actions_menu_close_actions_menu);
        final RecyclerView actionsMenuRecyclerView = ButterKnife.findById(actionsMenuLayout, R.id.recycler_view_actions_menu);

        final LinearLayoutManager actionsMenuLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        actionsMenuAdapter = new ActionsMenuAdapter();
        actionsMenuRecyclerView.setLayoutManager(actionsMenuLayoutManager);
        actionsMenuRecyclerView.setAdapter(actionsMenuAdapter);


        List<ActionsMenuAdapter.ActionItem> menus = new ArrayList<>();
        menus.add(new ActionsMenuAdapter.ActionItem("pish"));
        menus.add(new ActionsMenuAdapter.ActionItem("pish2"));
        menus.add(new ActionsMenuAdapter.ActionItem("pish"));
        menus.add(new ActionsMenuAdapter.ActionItem("pish2"));

        actionsMenuAdapter.update(menus);

        closeActionsMenuButton.setOnClickListener(this);
        openActionsMenuButton.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.button_adapter_jump:
                final int nextParagraph = ((JumpsAdapter)adapter).getItemAt(position).getId();
                gameBookPresenter.loadNextParagraph(getApplicationContext(), nextParagraph);
        }
    }

    @Override
    public void updateParagraph(final Paragraph nextParagraph) {
        paragraphTextTextView.setText(nextParagraph.getText());
        jumpsAdapter.update(nextParagraph.getJumps());
        rootScrollView.scrollTo(0, rootScrollView.getTop());
    }

    @Override
    public void openActionsMenu() {
        rootFrameLayout.removeView(openActionsMenuButton);
        rootFrameLayout.addView(actionsMenuLayout);
    }

    @Override
    public void closeActionsMenu() {
        rootFrameLayout.removeView(actionsMenuLayout);
        rootFrameLayout.addView(openActionsMenuButton);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_game_book_open_actions_menu:
                openActionsMenu();
                break;
            case R.id.button_actions_menu_close_actions_menu:
                closeActionsMenu();
                break;
        }
    }
}
