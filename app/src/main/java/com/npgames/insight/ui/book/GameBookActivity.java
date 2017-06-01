package com.npgames.insight.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluejamesbond.text.DocumentView;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.all.presentation.PlayerPresenter;
import com.npgames.insight.ui.all.presentation.PlayerView;
import com.npgames.insight.ui.player.CreatePlayerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBookActivity extends BaseMvpActivity implements View.OnClickListener, RecyclerViewListeners.OnItemClickListener,
        GameBookView, PlayerView{
    @BindView(R.id.text_view_game_paragraph_text)
    protected DocumentView paragraphTextTextView;
    @BindView(R.id.recycler_view_paragraph_jumps)
    protected RecyclerView jumpsRecyclerView;
    @BindView(R.id.frame_layout_game_book_root)
    protected FrameLayout rootFrameLayout;
    @BindView(R.id.button_game_book_open_actions_menu)
    protected Button openActionsMenuButton;
    @BindView(R.id.button_game_book_open_stats_panel)
    protected Button openStatsPanelButton;

    @BindView(R.id.scroll_game_book)
    protected ScrollView scrollView;

    protected View actionsMenuLayout;
    protected Button closeActionsMenuButton;

    protected View statsPanelLayout;
    protected Button closeStatsPanelButton;
    protected TextView statsTimeTextView;
    protected TextView statsHpTextView;
    protected TextView statsPrcTextView;
    protected TextView statsDexTextView;
    protected TextView statsAurTextView;
    protected TextView statsAmnTextView;

    @InjectPresenter
    GameBookPresenter gameBookPresenter;
    @InjectPresenter
    PlayerPresenter playerPresenter;

    private JumpsAdapter jumpsAdapter;
    private ActionsMenuAdapter actionsMenuAdapter;

    public enum GameType {NEW_GAME, CONTINUE}
    public static String GAME_TYPE_KEY = "GameTypeKey";

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebook);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerPresenter.savePlayer(this);
    }

    @Override
    protected void bindViews() {
        createActionsMenu();

        jumpsAdapter = new JumpsAdapter(getApplicationContext());
        final LinearLayoutManager jumpsLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        jumpsRecyclerView.setLayoutManager(jumpsLayoutManager);
        jumpsRecyclerView.setAdapter(jumpsAdapter);
        jumpsAdapter.setOnItemClickListener(this);

        chooseGameType();
        playerPresenter.loadCurrentParagraph();
    }

    private void chooseGameType() {
        final GameType gameType = (GameType) getIntent().getSerializableExtra(GAME_TYPE_KEY);
        switch (gameType) {
            case CONTINUE:
                playerPresenter.loadPlayer(getApplicationContext());
                break;
            case NEW_GAME:
                playerPresenter.createPlayer();
                break;
        }
    }

    private void createActionsMenu() {
        actionsMenuLayout = LayoutInflater.from(this).inflate(R.layout.layout_actions_menu, rootFrameLayout, false);
        closeActionsMenuButton = ButterKnife.findById(actionsMenuLayout, R.id.button_actions_menu_close_actions_menu);
        final RecyclerView actionsMenuRecyclerView = ButterKnife.findById(actionsMenuLayout, R.id.recycler_view_actions_menu);

        statsPanelLayout = LayoutInflater.from(this).inflate(R.layout.layout_stats_panel, rootFrameLayout, false);
        closeStatsPanelButton = ButterKnife.findById(statsPanelLayout, R.id.button_stats_panel_close);
        statsAmnTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_amn);
        statsTimeTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_time);
        statsHpTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_hp);
        statsPrcTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_prc);
        statsDexTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_dex);
        statsAurTextView = ButterKnife.findById(statsPanelLayout, R.id.text_view_stats_panel_aur);

        final LinearLayoutManager actionsMenuLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        actionsMenuAdapter = new ActionsMenuAdapter();
        actionsMenuRecyclerView.setLayoutManager(actionsMenuLayoutManager);
        actionsMenuRecyclerView.setAdapter(actionsMenuAdapter);


        List<ActionsMenuAdapter.ActionItem> menus = new ArrayList<>();
        menus.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.ARMORY));
        menus.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.INSPECT));
        menus.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.MEDBAY));
        menus.add(new ActionsMenuAdapter.ActionItem(ActionsMenuAdapter.ActionTypes.STATION));

        actionsMenuAdapter.update(menus);
        closeActionsMenuButton.setOnClickListener(this);
        openActionsMenuButton.setOnClickListener(this);
        closeStatsPanelButton.setOnClickListener(this);
        openStatsPanelButton.setOnClickListener(this);
    }

    @Override
    public void updateCurrentParagraph(final int paragraph) {
        gameBookPresenter.loadNextParagraph(getApplicationContext(), paragraph);
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
    public void openStatsPanel() {
        rootFrameLayout.removeView(openStatsPanelButton);
        rootFrameLayout.addView(statsPanelLayout);
    }

    @Override
    public void closeStatsPanel() {
        rootFrameLayout.removeView(statsPanelLayout);
        rootFrameLayout.addView(openStatsPanelButton);
    }

    @Override
    public void changeStat(final Player.Stats stats, final int statDifference) {
        playerPresenter.changeStat(stats, statDifference);
        final Player player = playerPresenter.loadPlayer(getApplicationContext());
        statsAmnTextView.setText(String.valueOf(player.getAmn()));
        statsTimeTextView.setText(String.valueOf(player.getTime()));
        statsHpTextView.setText(String.valueOf(player.getHp()));
        statsPrcTextView.setText(String.valueOf(player.getPrc()));
        statsDexTextView.setText(String.valueOf(player.getDex()));
        statsAurTextView.setText(String.valueOf(player.getAur()));
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.button_adapter_jump:
                final int nextParagraph = ((JumpsAdapter)adapter).getItemAt(position).getId();
                if (nextParagraph == 0) {
                    Intent intent = new Intent(this, CreatePlayerActivity.class);
                    startActivityForResult(intent, 1);
                    return;
                }
                gameBookPresenter.loadNextParagraph(getApplicationContext(), nextParagraph);
                break;
        }
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
            case R.id.button_game_book_open_stats_panel:
                openStatsPanel();
                break;
            case R.id.button_stats_panel_close:
                closeStatsPanel();
                break;
        }
    }

    @Override
    public void updateParagraph(final Paragraph nextParagraph) {
        jumpsAdapter.update(nextParagraph.getJumps());
        paragraphTextTextView.setText(getString(nextParagraph.getTextId()));
        scrollView.scrollTo(0, paragraphTextTextView.getTop());
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            final int dex = data.getIntExtra("DEX", 0);
            final int prc = data.getIntExtra("PRC", 0);

            playerPresenter.updatePlayer(dex, prc);
            gameBookPresenter.loadNextParagraph(getApplicationContext(), 1);
            playerPresenter.updatePlayersParagraph(1);

            openStatsPanelButton.setVisibility(View.VISIBLE);
        }
    }
}
