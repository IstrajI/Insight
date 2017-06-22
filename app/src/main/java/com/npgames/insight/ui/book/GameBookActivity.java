package com.npgames.insight.ui.book;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluejamesbond.text.DocumentView;
import com.npgames.insight.R;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.all.presentation.paragraph.ParagraphPresenter;
import com.npgames.insight.ui.all.presentation.paragraph.ParagraphView;
import com.npgames.insight.ui.all.presentation.player.PlayerPresenter;
import com.npgames.insight.ui.all.presentation.player.PlayerView;
import com.npgames.insight.ui.book.armory.ArmoryActivity;
import com.npgames.insight.ui.player.CreatePlayerActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBookActivity extends BaseMvpActivity implements View.OnClickListener, RecyclerViewListeners.OnItemClickListener,
        GameBookView, PlayerView, ParagraphView{
    @BindView(R.id.text_view_game_paragraph_text)
    protected DocumentView paragraphTextTextView;
    @BindView(R.id.recycler_view_paragraph_jumps)
    protected RecyclerView jumpsRecyclerView;
    @BindView(R.id.frame_layout_game_book_root)
    protected LinearLayout rootLinearLayout;
    @BindView(R.id.scroll_game_book)
    protected ScrollView scrollView;
    @BindView(R.id.button_game_book_open_actions_menu)
    protected Button openActionMenuButton;

    @BindView(R.id.text_view_stats_panel_amn)
    protected TextView amnTextView;
    @BindView(R.id.text_view_stats_panel_time)
    protected TextView timeTextView;
    @BindView(R.id.text_view_stats_panel_hp)
    protected TextView hpTextView;
    @BindView(R.id.text_view_stats_panel_prc)
    protected TextView prcTextView;
    @BindView(R.id.text_view_stats_panel_dex)
    protected TextView dexTextView;
    @BindView(R.id.text_view_stats_panel_aur)
    protected TextView aurTextView;

    protected View actionsMenuLayout;
    protected Button closeActionsMenuButton;

    @InjectPresenter
    ParagraphPresenter paragraphPresenter;
    @InjectPresenter
    GameBookPresenter gameBookPresenter;
    @InjectPresenter
    PlayerPresenter playerPresenter;

    private JumpsAdapter jumpsAdapter;
    private ActionsMenuAdapter actionsMenuAdapter;

    @Override
    public void showEquipmentsOwnedBy(List<Equipment> equipments) {
    //
    }

    @Override
    public void showStats(int hp, int aur, int prc, int dex, int time, int amn) {

    }

    @Override
    public void showPlayerOwnEquipment() {
        //
    }

    @Override
    public void showCantWearEquipment(int position) {

    }


    @Override
    public void showWearedEquipment() {

    }

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
        paragraphPresenter.saveParagraph(getApplicationContext());
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
    }

    private void chooseGameType() {
        final GameType gameType = (GameType) getIntent().getSerializableExtra(GAME_TYPE_KEY);
        switch (gameType) {
            case CONTINUE:
                paragraphPresenter.loadLastSavedParagraph(getApplicationContext());
                playerPresenter.loadPlayer(getApplicationContext());
                break;
            case NEW_GAME:
                paragraphPresenter.loadParagraph(getApplicationContext(), 500);
                playerPresenter.createPlayer();
                break;
        }
        playerPresenter.printEquipment();
    }

    private void createActionsMenu() {
        actionsMenuLayout = LayoutInflater.from(this).inflate(R.layout.layout_actions_menu, rootLinearLayout, false);
        closeActionsMenuButton = ButterKnife.findById(actionsMenuLayout, R.id.button_actions_menu_close_actions_menu);
        final RecyclerView actionsMenuRecyclerView = ButterKnife.findById(actionsMenuLayout, R.id.recycler_view_actions_menu);

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
        openActionMenuButton.setOnClickListener(this);
        //closeStatsPanelButton.setOnClickListener(this);
    }

    @Override
    public void openActionsMenu() {
        rootLinearLayout.removeView(openActionMenuButton);
        rootLinearLayout.addView(actionsMenuLayout);
    }

    @Override
    public void closeActionsMenu() {
        rootLinearLayout.removeView(actionsMenuLayout);
        rootLinearLayout.addView(openActionMenuButton);
    }

    @Override
    public void changeStat(final Paragraph.ActionTypes stats, final int statDifference) {
        playerPresenter.changeStat(stats, statDifference);
        final Player player = playerPresenter.loadPlayer(getApplicationContext());
        amnTextView.setText(String.valueOf(player.getAmn()));
        timeTextView.setText(String.valueOf(player.getTime()));
        hpTextView.setText(String.valueOf(player.getHp()));
        prcTextView.setText(String.valueOf(player.getPrc()));
        dexTextView.setText(String.valueOf(player.getDex()));
        aurTextView.setText(String.valueOf(player.getAur()));
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.button_adapter_jump:
                try {
                    final int nextParagraph = Integer.parseInt(((JumpsAdapter) adapter).getItemAt(position).getId());
                    if (nextParagraph == 0) {
                        Intent intent = new Intent(this, CreatePlayerActivity.class);
                        startActivityForResult(intent, 1);
                        return;
                    }
                    if (nextParagraph == 100) {
                        Intent armoryIntent = new Intent(this, ArmoryActivity.class);
                        startActivityForResult(armoryIntent, 2);
                        return;
                    }
                    paragraphPresenter.loadParagraph(getApplicationContext(), nextParagraph);
                    break;
                } catch(NumberFormatException ex) {

                }
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
/*            case R.id.button_game_book_open_stats_panel:
                openStatsPanel();
                break;*/
/*            case R.id.button_stats_panel_close:
                closeStatsPanel();
                break;*/
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
        Log.d("requestCode", "pish" + requestCode);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final int dex = data.getIntExtra("DEX", 0);
                    final int prc = data.getIntExtra("PRC", 0);

                    playerPresenter.updatePlayer(dex, prc);
                    paragraphPresenter.loadParagraph(getApplicationContext(), 1);
                    //openStatsPanelButton.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Log.d("TAGIL", "here");
                    paragraphPresenter.loadParagraph(getApplicationContext(), 40);
                    //Log.d("visibility", ""+openStatsPanelButton.getVisibility());
                }
                break;
        }

    }
}
