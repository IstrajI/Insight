package com.npgames.insight.ui.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.application.ScreenUtils;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.book.armory.ArmoryActivity;
import com.npgames.insight.ui.book.page.GamePageAdapter;
import com.npgames.insight.ui.book.top_panel.TopPanelView;
import com.npgames.insight.ui.player.CreatePlayerActivity;

import butterknife.BindView;

import static com.npgames.insight.ui.book.DeathDialogFragment.DEATH_DIALOG_FRAGMENT_TAG;

public class GameBookActivity extends BaseMvpActivity implements RecyclerViewListeners.OnItemClickListener,
        GameBookView, com.npgames.insight.ui.book.bottom_new.BottomPanelView.OnClickListener {

    public enum GameType {NEW_GAME, CONTINUE}
    public static String GAME_TYPE_KEY = "GameTypeKey";

    @BindView(R.id.viewpager_gamebook_pages)
    protected ViewPager pagesViewPager;
    @BindView(R.id.text_view_game_book_measuring)
    protected TextView measuringTextView;
    @BindView(R.id.gamebook_stats_top_panel_view)
    protected TopPanelView statsTopPanelView;
    @BindView(R.id.frame_layout_game_book_page_root)
    protected FrameLayout pageRootFrameLayout;
    @BindView(R.id.buttom_panel_game_book_actions)
    protected com.npgames.insight.ui.book.bottom_new.BottomPanelView bottomPanelView;

    private int paragraphTextHeight;

    @InjectPresenter
    GameBookPresenter gameBookPresenter;

    private PagerAdapter pagerAdapter;
    @ProvidePresenter
    GameBookPresenter provideGameBookPresenter() {
        return new GameBookPresenter(getApplicationContext());
    }



    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebook);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.setOnItemClickListener(this);
        pagesViewPager.setAdapter(pagerAdapter);

        initScreenUtils();
    }

    private void initScreenUtils() {
        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ScreenUtils.init(metrics.widthPixels, metrics.heightPixels);
    }

    @Override
    protected void bindViews() {
        measuringTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                measuringTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                bottomPanelView.init(getMvpDelegate(), GameBookActivity.this);
                gameBookPresenter.setDefaultBlockTextParams(measuringTextView.getLineSpacingExtra(),
                        measuringTextView.getLineSpacingMultiplier(),
                        measuringTextView.getPaint(),
                        measuringTextView.getWidth());

                paragraphTextHeight = measuringTextView.getHeight();

                chooseGameType();
            }
        });
    }

    @Override
    public void showStats(final Stats stats) {
        statsTopPanelView.setStats(stats);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        gameBookPresenter.saveGame(getApplicationContext());
    }

    private void chooseGameType() {
        final GameType gameType = (GameType) getIntent().getSerializableExtra(GAME_TYPE_KEY);
        switch (gameType) {
            case CONTINUE:
                gameBookPresenter.continueGame();
                break;

            case NEW_GAME:
                gameBookPresenter.newGame();
                showParagraph(500);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.adapter_game_page_button_jump_button:
                Log.d("TestPish", "ActionClicked");
                try {
                    final int nextParagraph = Integer.parseInt(((GamePageAdapter) adapter).getItemAt(position).content);
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

                    showParagraph(nextParagraph);

                    //gameBookPresenter.loadParagraphResName(nextParagraph);
                    //gameBookPresenter.showParagraph(getApplicationContext(), nextParagraph, paragraphTextHeight);

                } catch(NumberFormatException ex) {

                }
                break;

            case R.id.adapter_game_page_action_button:
                gameBookPresenter.actionPressed();
        }
    }

    public void disableJumps() {

    }

    public void showParagraph(final int paragraphNumber) {



        //gameBookPresenter.checkConditionActions(paragraphNumber);
        gameBookPresenter.loadParagraph(paragraphNumber, paragraphTextHeight, paragraphString);
    }

    @Override
    public void updateParagraph(final Paragraph paragraph) {
        pagesViewPager.setCurrentItem(0);
        pagerAdapter.update(paragraph);
    }

    @Override
    public void showDeathScreen() {
        final DeathDialogFragment deathDialogFragment = new DeathDialogFragment();
        deathDialogFragment.show(getSupportFragmentManager(), DEATH_DIALOG_FRAGMENT_TAG);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final int dex = data.getIntExtra("DEX", 0);
                    Log.d("dex multiplier", ""+dex);
                    final int prc = data.getIntExtra("PRC", 0);
                    Log.d("prc multiplier", ""+prc);
                    gameBookPresenter.updatePlayerStats(dex, prc);
                    showParagraph(1);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    //gameBookPresenter.showParagraph(getApplicationContext(), 40, paragraphTextHeight);
                    //Log.d("visibility", ""+openStatsPanelButton.getVisibility());
                }
                break;
        }
    }

    @Override
    public void onFind() {
        final int wantedParagraph = gameBookPresenter.getWantedParagraph();
        Log.d("TestPish", "wantedParagraph = " +wantedParagraph);
        final String paragraphResName = gameBookPresenter.getResourceName(wantedParagraph);
        Log.d("TestPish", "paragraphResId = " +paragraphResName);
        final int paragraphResId = getResources().getIdentifier(paragraphResName, "string", getPackageName());
        Log.d("TestPish", "paragraphResId = " +paragraphResId);
        gameBookPresenter.onFindAction(wantedParagraph, paragraphResId);
    }

    @Override
    public void showFindSuccess(final int paragraphNumber, final int paragraphResId) {
        final String paragraphString = getString(paragraphResId);
        Toast.makeText(getApplicationContext(), "Find Success", Toast.LENGTH_SHORT).show();
        gameBookPresenter.loadParagraph(paragraphNumber, paragraphTextHeight, paragraphString);
    }

    @Override
    public void showFindFailed() {
        Toast.makeText(getApplicationContext(), "Find Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStation() {
        showParagraph(95);
    }

    @Override
    public void onMedBay() {
        showParagraph(54);
    }

    @Override
    public void onArmory() {
        Intent intent = new Intent(this, ArmoryActivity.class);
        startActivityForResult(intent, 1);
    }
}
