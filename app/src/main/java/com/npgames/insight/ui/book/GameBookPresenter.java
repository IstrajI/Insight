package com.npgames.insight.ui.book;

import android.content.Context;
import android.text.TextPaint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;
import com.npgames.insight.domain.ActionsInteractor;
import com.npgames.insight.domain.GameInteractor;
import com.npgames.insight.domain.UserActionInteractor;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView> {
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;
    private boolean isBottomPanelOpened = false;
    private ActionsInteractor actionsInteractor;
    private StatsRepository statsRepository;
    private ParagraphRepository paragraphRepository;
    private GameInteractor gameInteractor;
    private UserActionInteractor userActionInteractor;


    GameBookPresenter(final Context context) {
        userActionInteractor = new UserActionInteractor(context);
        actionsInteractor = new ActionsInteractor(context);
        gameInteractor = new GameInteractor(context);
        statsRepository = StatsRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
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

    //---------------------------- User Bottom Panel Actions ---------------------------------------
    //----------------------------------------------------------------------------------------------

    public void onFindClick(final int availableHeight) {
        getViewState().showStats(userActionInteractor.spendTime());
        final Paragraph searchingParagraph = userActionInteractor.loadSearchingParagraph(availableHeight);

        if (searchingParagraph == null) {
            getViewState().showFindFailed();
        } else {
            getViewState().showFindSuccess();
            getViewState().updateParagraph(searchingParagraph);
        }
    }

    public void onMedBayClick(final int availableHeight) {
        userActionInteractor.loadMedBay(availableHeight);
    }

    public void onStationClick(final int availableHeight) {
        userActionInteractor.loadStation(availableHeight);
    }

    //---------------------------- Game Modes ------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    void newGame(final int availableTextHeight) {
        final Paragraph paragraph = gameInteractor.startNewGame(availableTextHeight);
        getViewState().updateParagraph(paragraph);

        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void continueGame(final int availableHeight) {
        final Paragraph paragraph = gameInteractor.loadSavedParagraph(availableHeight);
        getViewState().updateParagraph(paragraph);

        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    public void saveGame() {
        gameInteractor.saveGame();
    }

    void loadGameParagraph(final int availableTextHeight, final int paragraphNumber) {
        final Paragraph paragraph = gameInteractor.nextParagraph(paragraphNumber, availableTextHeight);
        getViewState().updateParagraph(paragraph);

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

    public void loadStats() {
        getViewState().showStats(statsRepository.getStats());
    }

    void applyAction() {
        //A bit of kostil logic here
        final int paragraphNumber = paragraphRepository.getParagraph().paragraphNumber;
        if (gameInteractor.isMedBay(paragraphNumber)) {
            actionsInteractor.applyAction(paragraphNumber);
            getViewState().showStats(statsRepository.getStats());
        } else if (!paragraphRepository.getParagraph().wasActionPressed) {
            paragraphRepository.getParagraph().wasActionPressed = true;
            actionsInteractor.applyAction(paragraphNumber);
            getViewState().showStats(statsRepository.getStats());

            gameInteractor.enableJumpsDisableActions();
            getViewState().refreshParagraph(paragraphRepository.getParagraph());
        }

        if (gameInteractor.onDeath()) {
            gameInteractor.clearGameSettings();
            getViewState().showDeathScreen();

        }
    }

    public void disableJumps() {
        paragraphRepository.getParagraph().wasActionPressed = false;

        gameInteractor.disableJumps();
        getViewState().refreshParagraph(paragraphRepository.getParagraph());
    }
}
