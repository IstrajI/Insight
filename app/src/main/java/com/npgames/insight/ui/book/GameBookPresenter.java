package com.npgames.insight.ui.book;

import android.content.Context;
import android.text.TextPaint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.db.GamePreferences;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.repositories.ParagraphRepository;
import com.npgames.insight.data.repositories.StatsRepository;
import com.npgames.insight.domain.ActionsInteractor;
import com.npgames.insight.domain.GameInteractor;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView> {
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;
    private int screenHeight;
    private Paragraph currentParagraph;
    private boolean isBottomPanelOpened = false;
    private ActionsInteractor actionsInteractor;
    private StatsRepository statsRepository;
    private ParagraphRepository paragraphRepository;
    private GamePreferences gamePreferences;

    private GameInteractor gameInteractor;

    GameBookPresenter(final Context context) {
        actionsInteractor = new ActionsInteractor(context);
        gameInteractor = new GameInteractor(context);
        statsRepository = StatsRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
        gamePreferences = GamePreferences.getInstance(context);
    }

    public void interactWithStatsPanel() {
        if (isStatsPanelOpen) {
            //getViewState().closeStatsPanel();
            isActionsMenuOpen = false;
            return;
        }
        //getViewState().openStatsPanel();
        isStatsPanelOpen = true;
    }

    public void setDefaultBlockTextParams(final float spacingAdd,
                                          final float spacingMultiplier,
                                          final TextPaint textPaint,
                                          final int width) {
        BlockText.DEF_SPACING_ADD = spacingAdd;
        BlockText.DEF_SPACING_MULTIPLIER = spacingMultiplier;
        BlockText.DEF_TEXT_PAINT = textPaint;
        BlockText.DEF_WIDTH = width;
    }

    public void loadParagraph(final int paragraphNumber, final int paragraphTextHeight) {
        currentParagraph = gameInteractor.nextParagraph(paragraphNumber, paragraphTextHeight);
        getViewState().updateParagraph(currentParagraph);
    }

    public int loadCurrentParagraphNumber(final Context context) {
        return GamePreferences.getInstance(context).loadCurrentParagraph();
    }

    //TODO: we should save current paragraph similar to player
    public void saveGame(final Context context) {
        playerRepository.savePlayer();
        GamePreferences.getInstance(context).saveCurrentParagraph(paragraphRepository.getParagraph().paragraphNumber);
    }

    private void onFindButtonClick() {

    }

    private void onMedBayButtonClick() {

    }


    public void onFindAction(final int paragraphNumber, final int paragraphResId) {
        final boolean isParagraphExists = paragraphResId != 0;

        if (isParagraphExists) {
            getViewState().showFindSuccess(paragraphNumber, paragraphResId);
        } else {
            //changeStats
            getViewState().showFindFailed();
        }
    }

    public int getWantedParagraph() {
        return paragraphRepository.getParagraph().paragraphNumber + 10;
    }


    void newGame() {
        final int FIRST_PARAGRAPH_NUMBER = 500;
        getViewState().showParagraph(FIRST_PARAGRAPH_NUMBER);
        gameInteractor.startNewGame();
        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void continueGame() {
        final int currentParagraphNumber = gamePreferences.loadCurrentParagraph();
        getViewState().showParagraph(currentParagraphNumber);

        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void updatePlayerStats(final int dex, final int prc) {
        final Stats stats = Stats.builder()
                .setDex(dex)
                .setPrc(prc)
                .build();

        statsRepository.updateStats(stats);
        getViewState().showStats(statsRepository.getStats());
    }

    void applyAction() {
        if (!paragraphRepository.getParagraph().wasActionPressed) {
            paragraphRepository.getParagraph().wasActionPressed = true;

            actionsInteractor.applyAction(paragraphRepository.getParagraph().paragraphNumber);
            getViewState().showStats(statsRepository.getStats());

            gameInteractor.enableJumpsDisableActions();
            getViewState().updateParagraph(paragraphRepository.getParagraph());
        }
    }
}
