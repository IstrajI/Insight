package com.npgames.insight.ui.book;

import android.content.Context;
import android.text.TextPaint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.dao.ParagraphActionsChecker;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.dao.PlayerRepository;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.StatsChanger;
import com.npgames.insight.data.model.TrackingParagraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView>{
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;
    private int screenHeight;
    private int currentParagraph;
    private boolean isBottomPanelOpened = false;
    private ParagraphActionsChecker paragraphActionsChecker;

    private PlayerRepository playerRepository;
    private GamePreferences gamePreferences;

    GameBookPresenter(final Context context) {
        playerRepository = PlayerRepository.getInstance(context);
        final Player player = playerRepository.getPlayer();
        gamePreferences = GamePreferences.getInstance(context);
        paragraphActionsChecker = new ParagraphActionsChecker(player);
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

    public void loadParagraph(final int paragraphNumber, final int paragraphTextHeight, final String paragraphString) {
        currentParagraph = paragraphNumber;

        final List<BlockArea> blockAreas = ParagraphParser.parse(paragraphString);
        final Pagination pagination = new Pagination();
        getViewState().updateParagraph(pagination.createParagraphModel(blockAreas, paragraphTextHeight));
    }

    public int loadCurrentParagraphNumber(final Context context) {
        return GamePreferences.getInstance(context).loadCurrentParagraph();
    }

    //TODO: we should save current paragraph similar to player
    public void saveGame(final Context context) {
        playerRepository.savePlayer();
        GamePreferences.getInstance(context).saveCurrentParagraph(currentParagraph);
    }

    public void checkConditionActions(final int paragraphNumber) {
        //final StatsChanger statsChanger = paragraphActionsChecker.checkParagraph(paragraphNumber);
        //TODO: apply statschanger
        //playerRepository.

        getViewState().showStats(playerRepository.getStats());
    }


    public boolean checkConditionNotFirstTime(final Map.Entry<Paragraph.ActionTypes, Integer> action, final TrackingParagraph.Action action1) {
        if (!action1.isStatus()) {
            action1.setStatus(true);
            return false;
        }
        return true;
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
        return currentParagraph + 10;
    }

    public String getResourceName(final int nextParagraph) {
        return ParagraphParser.formatParagraphResName(nextParagraph);
    }

    void newGame() {
        final int FIRST_PARAGRAPH_NUMBER = 500;
        getViewState().showParagraph(FIRST_PARAGRAPH_NUMBER);

        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);
    }

    void continueGame() {
        final int currentParagraphNumber = gamePreferences.loadCurrentParagraph();
        getViewState().showParagraph(currentParagraphNumber);

        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);
    }

    public void updatePlayerStats(final int dex, final int prc) {
        final Stats stats = Stats.builder().setDex(dex)
                .setPrc(prc)
                .build();

        playerRepository.updateStats(stats);
        getViewState().showStats(playerRepository.getStats());
    }


}
