package com.npgames.insight.ui.book;

import android.content.Context;
import android.text.TextPaint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.db.GamePreferences;
import com.npgames.insight.data.repositories.StatsRepository;
import com.npgames.insight.domain.ActionsInteractor;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.TrackingParagraph;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.domain.GameInteractor;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView> {
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;
    private int screenHeight;
    private int currentParagraphNumber;
    private Paragraph currentParagraph;
    private boolean isBottomPanelOpened = false;
    private ActionsInteractor actionsInteractor;
    private boolean wasActionPressed;

    private StatsRepository statsRepository;
    private GamePreferences gamePreferences;

    private GameInteractor gameInteractor;

    GameBookPresenter(final Context context) {
        actionsInteractor = new ActionsInteractor(context);
        gameInteractor = new GameInteractor(context);
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
    private List<TrackingParagraph> trackingParagraphs = new ArrayList<>();

    public void loadParagraph(final int paragraphNumber, final int paragraphTextHeight) {
        currentParagraphNumber = paragraphNumber;
        currentParagraph = gameInteractor.nextParagraph(paragraphNumber, paragraphTextHeight, paragraphString);

        getViewState().updateParagraph(currentParagraph);
    }

    public int loadCurrentParagraphNumber(final Context context) {
        return GamePreferences.getInstance(context).loadCurrentParagraph();
    }

    //TODO: we should save current paragraph similar to player
    public void saveGame(final Context context) {
        playerRepository.savePlayer();
        GamePreferences.getInstance(context).saveCurrentParagraph(currentParagraphNumber);
    }




    private TrackingParagraph findOrCreateParagraphAction(final int paragraphNumber) {
        for (TrackingParagraph action : trackingParagraphs) {
            if (action.getParagraphNumber() == paragraphNumber) {
                return action;
            }
        }
        return new TrackingParagraph(paragraphNumber);
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
        return currentParagraphNumber + 10;
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

    void checkIfActionDisableJumps(final Paragraph paragraph) {
        //General approach
        final List<BlockArea> blockAreas = paragraph.getBlockAreas();
        boolean paragraphHasAction = false;
        wasActionPressed = true;

        for (final BlockArea blockArea: blockAreas) {
            if (blockArea.type == BlockArea.BlockType.ACTION) {
                paragraphHasAction = true;
                wasActionPressed = false;
            }
        }

        if (paragraphHasAction) {
            disableJumps(blockAreas);
        }
    }

    private void disableJumps(final List<BlockArea> blockAreas) {
        for (final BlockArea blockArea: blockAreas) {
            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                ((BlockButton) blockArea).setEnable(false);
            }
        }
    }

    void actionPressed() {
        wasActionPressed = true;

        actionsInteractor.applyAction(currentParagraphNumber);
        getViewState().showStats(statsRepository.getStats());

        enableJumpsDisableActions();
        getViewState().updateParagraph(currentParagraph);
    }

    private void enableJumpsDisableActions() {
        final List<BlockArea> blockAreas = currentParagraph.getBlockAreas();

        for (final BlockArea blockArea: blockAreas) {
            if (blockArea.type == BlockArea.BlockType.ACTION) {
                ((BlockAction) blockArea).setEnable(false);
            }

            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                ((BlockButton) blockArea).setEnable(true);
            }
        }
    }
}
