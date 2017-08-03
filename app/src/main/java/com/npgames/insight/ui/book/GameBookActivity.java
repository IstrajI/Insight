package com.npgames.insight.ui.book;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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

public class GameBookActivity extends BaseMvpActivity implements RecyclerViewListeners.OnItemClickListener,
        GameBookView, PlayerView, ParagraphView{
    @BindView(R.id.text_view_game_paragraph_text)
    protected DocumentView paragraphTextTextView;
    @BindView(R.id.recycler_view_paragraph_jumps)
    protected RecyclerView jumpsRecyclerView;
    @BindView(R.id.scroll_game_book)
    protected ScrollView scrollView;
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

    @BindView(R.id.frame_layout_gamebook_stats_panel)
    protected FrameLayout test;

    @InjectPresenter
    ParagraphPresenter paragraphPresenter;
    @InjectPresenter
    GameBookPresenter gameBookPresenter;
    @InjectPresenter
    PlayerPresenter playerPresenter;

    private JumpsAdapter jumpsAdapter;

    @Override
    public void showEquipmentsOwnedBy(List<Equipment> equipments) {
    //
    }

    @Override
    public void showStats(final int hp, final int aur, final int prc, final int dex, final int time, final int amn) {
        amnTextView.setText(String.valueOf(amn));
        hpTextView.setText(String.valueOf(hp));
        aurTextView.setText(String.valueOf(aur));
        prcTextView.setText(String.valueOf(prc));
        dexTextView.setText(String.valueOf(dex));
        amnTextView.setText(String.valueOf(amn));
    }

    @Override
    public void showEquipments(List<Equipment> equipments) {

    }

    @Override
    public void updateWearEquipmentStatus(int equipmentNumber, boolean canWear) {

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


    @Override
    public void changeStat(final Paragraph.ActionTypes stats, final int statDifference) {
        playerPresenter.changeStat(stats, statDifference);
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
    public void updateParagraph(final Paragraph nextParagraph) {

        Log.d("meajured", "height = "+test.getMeasuredHeight() +" widht = " +test.getMeasuredWidth());
        playerPresenter.checkJumpsConditions(nextParagraph);
        jumpsAdapter.update(nextParagraph.getJumps());
        Log.d("pop", ""+paragraphTextTextView.getLayout().getLineCount());
        paragraphTextTextView.setText(getString(nextParagraph.getTextId()));

        Log.d("pop", ""+paragraphTextTextView.getLayout().getMeasuredHeight());
        scrollView.scrollTo(0, paragraphTextTextView.getTop());

        final Paint.FontMetrics fontMetrics = paragraphTextTextView.getLayout().getPaint().getFontMetrics();

        Log.d("fontmetrics", ""+fontMetrics.bottom +"  " +fontMetrics.top);
        Log.d("fontmetrdsics", ""+fontMetrics.bottom +"  " +fontMetrics.top+"  gg:" +paragraphTextTextView.getLayout().getMeasuredHeight()+"  "+paragraphTextTextView.getMeasuredHeight());
        Log.d("govno", ""+paragraphTextTextView.getLayout().getLineCount());
        paragraphTextTextView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("fontmetrics", ""+fontMetrics.bottom +"  " +fontMetrics.top+"  gg:" +paragraphTextTextView.getHeight()+"  "+paragraphTextTextView.getMeasuredHeight());
                int lineCount = paragraphTextTextView.getLayout().getLineCount();
                Log.d("actual", ""+lineCount);
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.d("requestCode", "pish" + requestCode);
        playerPresenter.printStats();
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final int dex = data.getIntExtra("DEX", 0);
                    Log.d("dex multiplier", ""+dex);
                    final int prc = data.getIntExtra("PRC", 0);
                    Log.d("prc multiplier", ""+prc);
                    playerPresenter.updatePlayer(dex, prc);
                    playerPresenter.printStats();
                    paragraphPresenter.loadParagraph(getApplicationContext(), 1);
                    //openStatsPanelButton.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    paragraphPresenter.loadParagraph(getApplicationContext(), 40);
                    //Log.d("visibility", ""+openStatsPanelButton.getVisibility());
                }
                break;
        }

    }
}
