package com.npgames.insight.ui.book;

import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.InsightApplication;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.all.fragments.infoDialog.InfoDialog;
import com.npgames.insight.ui.all.listeners.RecyclerViewListeners;
import com.npgames.insight.ui.book.armory.ArmoryActivity;
import com.npgames.insight.ui.book.equipmentDialog.EquipmentDialogFragment;
import com.npgames.insight.ui.book.bottom_new.BottomPanelPresenter;
import com.npgames.insight.ui.book.bottom_new.BottomPanelView;
import com.npgames.insight.ui.book.bottom_new.IBottomPanelView;
import com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog;
import com.npgames.insight.ui.book.death.DeathDialogFragment;
import com.npgames.insight.ui.book.menu.MenuDialogFragment;
import com.npgames.insight.ui.book.page.GamePageAdapter;
import com.npgames.insight.ui.book.top_panel.TopPanelView;

import java.util.List;

import butterknife.BindView;

import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.BOTTOM_ACTION_CONFIRM_DIALOG_TAG;
import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.CONFIRMATION_TEXT;
import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.REQUEST_CODE;

public class GameBookActivity extends BaseMvpActivity implements RecyclerViewListeners.OnItemClickListener,
        GameBookView, IBottomPanelView, BottomPanelView.BottomPanelListener, TopPanelView.TopPanelClickListener,
        MenuDialogFragment.MenuDialogClickListener, View.OnClickListener, ICreatePlayer, DeathDialogFragment.IDeathDialogListener,
        BottomActionConfirmDialog.BottomActionConfirmListener, EquipmentDialogFragment.EquipmentDialogFragmentInterface {

    public enum GameType {NEW_GAME, CONTINUE, NONE}
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
    protected BottomPanelView bottomPanelView;
    @BindView(R.id.bottom_panel_actions_layout)
    protected ConstraintLayout actionsFrameLayout;

    private int paragraphTextHeight;
    private PagerAdapter pagerAdapter;

    @InjectPresenter
    BottomPanelPresenter bottomPanelPresenter;
    @ProvidePresenter
    BottomPanelPresenter provideBottomPanelPresenter() {
        return new BottomPanelPresenter(getApplicationContext());
    }

    @InjectPresenter
    GameBookPresenter gameBookPresenter;
    @ProvidePresenter
    GameBookPresenter provideGameBookPresenter() {
        return new GameBookPresenter(getApplicationContext());
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebook);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getMvpDelegate());
        pagerAdapter.setOnItemClickListener(this);
        pagerAdapter.setClickListener(this);
        pagerAdapter.setCreatePlayerConsumeCallback(this);
        pagesViewPager.setAdapter(pagerAdapter);

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ScreenUtils.init(metrics.widthPixels, metrics.heightPixels);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("TestPish", "GameBook: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((InsightApplication)getApplication()).setMusic(R.raw.background_deep_space);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TestPish", "GameBook: onPause");
        gameBookPresenter.saveGame();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TestPish", "GameBook: onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TestPish", "GameBook: onDestroy");

        //gameBookPresenter.saveGame();
    }

    @Override
    protected void bindViews() {
        measuringTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                measuringTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                gameBookPresenter.setDefaultBlockTextParams(measuringTextView.getLineSpacingExtra(),
                        measuringTextView.getLineSpacingMultiplier(),
                        measuringTextView.getPaint(),
                        measuringTextView.getWidth());

                paragraphTextHeight = measuringTextView.getHeight();

                chooseGameType();
            }
        });

        bottomPanelView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final float openPosition = bottomPanelView.getTop();
                final float closePosition = Resources.getSystem().getDisplayMetrics().heightPixels -
                        actionsFrameLayout.getHeight()
                        + GameBookActivity.this.getResources().getDimension(R.dimen.spacing_8);

                bottomPanelPresenter.initOpenClosePositions(openPosition, closePosition);
                bottomPanelPresenter.openCloseBottomPanel();
                bottomPanelView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });



        bottomPanelView.addClickListener(this);
        bottomPanelPresenter.loadPlayerEquipment();
        statsTopPanelView.setClickListener(this);
    }

    @Override
    public void showStats(final Stats stats) {
        Log.d("TestPish", "Call");
        statsTopPanelView.setStats(stats);
    }

    private void chooseGameType() {
        final GameType gameType = (GameType) getIntent().getSerializableExtra(GAME_TYPE_KEY);
        switch (gameType) {
            case NONE:
            case CONTINUE:
                gameBookPresenter.continueGame(paragraphTextHeight);
                break;

            case NEW_GAME:
                gameBookPresenter.newGame(paragraphTextHeight);
                break;
        }

        final Intent intent = new Intent(getIntent());
        intent.putExtra(GAME_TYPE_KEY, GameType.NONE);
        setIntent(intent);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.adapter_game_page_button_jump_button:
                try {
                    final int nextParagraph = Integer.parseInt(((GamePageAdapter) adapter).getItemAt(position).content);
                    if (nextParagraph == 1000) {
                        gameBookPresenter.clearWasActionPressed();
                        Intent armoryIntent = new Intent(this, ArmoryActivity.class);
                        armoryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(armoryIntent, 2);
                        return;
                    }

                    //Use grenade
                    if (nextParagraph == 327) {
                        gameBookPresenter.removeGrenade();
                        bottomPanelPresenter.loadPlayerEquipment();
                    }

                    gameBookPresenter.loadGameParagraph(paragraphTextHeight, nextParagraph);

                } catch(NumberFormatException ex) {

                }
                break;

            case R.id.adapter_game_page_action_button:
                gameBookPresenter.applyAction();
                break;
        }
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()) {
            case R.id.create_player_dex_minus_button:
            case R.id.create_player_dex_plus_button:
            case R.id.create_player_prc_minus_button:
            case R.id.create_player_prc_plus_button:
                gameBookPresenter.loadStats();
                break;
        }
    }


    @Override
    public void updateParagraph(final Paragraph paragraph) {
        pagesViewPager.setCurrentItem(0);
        pagerAdapter.update(paragraph);

        checkAvailableBottomActionsState(paragraph);
    }

    public void checkAvailableBottomActionsState(final Paragraph paragraph) {
        bottomPanelPresenter.setAvailableActionsState(paragraph);
    }

    @Override
    public void refreshParagraph(Paragraph paragraph) {
        pagerAdapter.update(paragraph);
    }

    @Override
    public void onClose() {
        this.finish();
    }

    @Override
    public void showDeathScreen() {
        final DeathDialogFragment deathDialogFragment = new DeathDialogFragment();
        deathDialogFragment.setOnDeathDialogListener(this);
        deathDialogFragment.show(getSupportFragmentManager(), BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
    }

    public void deathDialogListener() {
        this.finish();
    }

    @Override
    public void menuDialogOnGoToMainMenuClick() {
        finish();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {
                    final int dex = data.getIntExtra("DEX", 0);
                    final int prc = data.getIntExtra("PRC", 0);

                    gameBookPresenter.updatePlayerStats(dex, prc);
                    gameBookPresenter.loadGameParagraph(paragraphTextHeight, 1);
                }

                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    gameBookPresenter.loadGameParagraph(paragraphTextHeight, 40);
                    gameBookPresenter.updatePlayerStatsWithoutAnimation();
                }

                break;
        }
    }

    public void setStatsWithoutAnimation(final Stats stats) {
        statsTopPanelView.setStatsWithoutAnimation(stats);
    }

    //---------------------------- CreatePlayer ----------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void allPointsDistributed() {
        gameBookPresenter.applyAction();
    }

    @Override
    public void outOfAllPointsDistributed() {
        gameBookPresenter.disableJumps();
    }

    //---------------------------- Top Panel -------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void topPanelOnMenuClick() {
        final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

        final Bundle bundle = new Bundle();
        bundle.putString(CONFIRMATION_TEXT, getString(R.string.action_confirm_dialog_go_to_main_menu));
        bundle.putInt(REQUEST_CODE, MED_BAY_CLICK_REQUEST_CODE);
        bottomActionConfirmDialog.setArguments(bundle);

        bottomActionConfirmDialog.setConfirmationListener(this);
        bottomActionConfirmDialog.show(getSupportFragmentManager(), BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
    }

    //---------------------------- User Bottom Panel General ---------------------------------------
    //----------------------------------------------------------------------------------------------
    @Override
    public void moveYTo(final float y) {
        bottomPanelView.setY(y);
        bottomPanelView.invalidate();
    }

    @Override
    public void showPlayerEquipment(final List<Equipment> equipmentList) {
        bottomPanelView.updateEquipment(equipmentList);
    }

    //---------------------------- User Bottom Panel Actions ---------------------------------------
    //----------------------------------------------------------------------------------------------

    public final int FIND_CLICK_REQUEST_CODE = 1;
    public final int STATION_CLICK_REQUEST_CODE = 2;
    public final int MED_BAY_CLICK_REQUEST_CODE = 3;
    public final int ARMORY_CLICK_REQUEST_CODE = 4;
    public final int MAIN_MENU_REQUEST_CODE = 5;

    @Override
    public void bottomPanelFindClick() {
        final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

        final Bundle bundle = new Bundle();
        bundle.putString(CONFIRMATION_TEXT, getString(R.string.action_confirm_dialog_find_evidence_text));
        bundle.putInt(REQUEST_CODE, FIND_CLICK_REQUEST_CODE);
        bottomActionConfirmDialog.setArguments(bundle);
        bottomActionConfirmDialog.setConfirmationListener(this);

        bottomActionConfirmDialog.show(getSupportFragmentManager(), BottomActionConfirmDialog.BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
    }

    @Override
    public void showFindSuccess() {
        final InfoDialog infoDialog = new InfoDialog();
        final Bundle bundle = new Bundle();
        bundle.putString(InfoDialog.MESSAGE, "Вы нашли скрытый параграф!");
        infoDialog.setArguments(bundle);
        infoDialog.show(getSupportFragmentManager(), InfoDialog.INFO_DIALOG_TAG);
    }

    @Override
    public void showFindFailed() {
        final InfoDialog infoDialog = new InfoDialog();
        final Bundle bundle = new Bundle();
        bundle.putString(InfoDialog.MESSAGE, "Поиск улик ничего не дал");
        infoDialog.setArguments(bundle);
        infoDialog.show(getSupportFragmentManager(), InfoDialog.INFO_DIALOG_TAG);
        Toast.makeText(getApplicationContext(), "Find Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bottomPanelStationClick() {
        final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

        final Bundle bundle = new Bundle();
        bundle.putString(CONFIRMATION_TEXT, getString(R.string.action_confirm_dialog_station_text));
        bundle.putInt(REQUEST_CODE, STATION_CLICK_REQUEST_CODE);
        bottomActionConfirmDialog.setArguments(bundle);
        bottomActionConfirmDialog.setConfirmationListener(this);

        bottomActionConfirmDialog.show(getSupportFragmentManager(), BottomActionConfirmDialog.BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
    }

    @Override
    public void bottomPanelMedBayClick() {
        final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

        final Bundle bundle = new Bundle();
        bundle.putString(CONFIRMATION_TEXT, getString(R.string.action_confirm_dialog_med_bay_text));
        bundle.putInt(REQUEST_CODE, MED_BAY_CLICK_REQUEST_CODE);
        bottomActionConfirmDialog.setArguments(bundle);
        bottomActionConfirmDialog.setConfirmationListener(this);

        bottomActionConfirmDialog.show(getSupportFragmentManager(), BOTTOM_ACTION_CONFIRM_DIALOG_TAG);

/*        gameBookPresenter.onMedBayClick(paragraphTextHeight);*/
    }

    @Override
    public void bottomPanelArmoryClick() {
        final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

        final Bundle bundle = new Bundle();
        bundle.putString(CONFIRMATION_TEXT, getString(R.string.action_confirm_dialog_armory_text));
        bundle.putInt(REQUEST_CODE, ARMORY_CLICK_REQUEST_CODE);
        bottomActionConfirmDialog.setArguments(bundle);
        bottomActionConfirmDialog.setConfirmationListener(this);

        bottomActionConfirmDialog.show(getSupportFragmentManager(), BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
    }


    @Override
    public void bottomPanelShowItemInfo(final Equipment equipment) {
        final String name = equipment.getName();
        final String description = equipment.getDescription();
        final int drawable = equipment.getDrawable(Equipment.DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR);
        final @Equipment.TYPE String type = equipment.getType();

        final EquipmentDialogFragment equipmentDialogFragment = EquipmentDialogFragment.createEquipmentDialogFragment(name, description, drawable, type, true);
        equipmentDialogFragment.setEquipmentDialogFragment(this);
        equipmentDialogFragment.show(getSupportFragmentManager(), EquipmentDialogFragment.TAG);
    }

    @Override
    public void bottomPanelClick() {
        bottomPanelPresenter.openCloseBottomPanel();
    }

    public void onOpenBottomPanel() {
        bottomPanelPresenter.loadPlayerEquipment();
        bottomPanelView.onOpen();
    }

    public void onCloseBottomPanel() {
        bottomPanelView.onClose();
    }

    @Override
    public void showAvailableAllActionsState() {
        bottomPanelView.setAvailableAllActionsState();
    }

    @Override
    public void showAvailableFindActionsState() {
        bottomPanelView.setAvailableFindActionsState();
    }

    @Override
    public void showDisabledAllActionsState() {
        bottomPanelView.setDisabledAllActionState();
    }

    @Override
    public void showDisabledArmoryActionsState() {
        bottomPanelView.setDisabledArmoryActionsState();
    }

    @Override
    public void disableMedBayActionsState() {
        bottomPanelView.setDisabledMedBayActionsState();
    }


    @Override
    public void onItemDropped() {
        bottomPanelPresenter.loadPlayerEquipment();
        gameBookPresenter.loadStats();
    }

    @Override
    public void onConfirm(final int requestCode) {
        switch (requestCode) {
            case FIND_CLICK_REQUEST_CODE:
                findConfirmed();
                break;
            case STATION_CLICK_REQUEST_CODE:
                stationConfirmed();
                break;

            case MED_BAY_CLICK_REQUEST_CODE:
                medBayConfirmed();
                break;

            case ARMORY_CLICK_REQUEST_CODE:
                armoryConfirmed();
                break;

            case MAIN_MENU_REQUEST_CODE:
                menuDialogOnGoToMainMenuClick();
                break;
        }
    }

    public void findConfirmed() {
        gameBookPresenter.onFindClick(paragraphTextHeight);
    }

    public void stationConfirmed() {
        gameBookPresenter.onStationClick(paragraphTextHeight);
    }

    public void medBayConfirmed() {
        gameBookPresenter.onMedBayClick(paragraphTextHeight);
    }

    public void armoryConfirmed() {
        gameBookPresenter.onArmoryClick(paragraphTextHeight);
    }
}
