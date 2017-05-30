package com.npgames.insight.ui.book;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;

import java.util.Map;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView>{
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;

    public static final int INIT_PARAGRAPH = 500;
    public void loadNextParagraph(final Context context, final int paragraphNumber) {
        final Paragraph paragraph = ParagraphParser.parse(context, paragraphNumber);
        executeParagraphAction(paragraph.getActions());
        getViewState().updateParagraph(paragraph);
    }

    public void interactWithStatsPanel() {
        if (isStatsPanelOpen) {
            getViewState().closeStatsPanel();
            isActionsMenuOpen = false;
            return;
        }
        getViewState().openStatsPanel();
        isStatsPanelOpen = true;
    }

    public void interactWithActionsMenu() {
        if (isActionsMenuOpen) {
            getViewState().closeActionsMenu();
            isActionsMenuOpen = false;
            return;
        }
        getViewState().openActionsMenu();
        isActionsMenuOpen = true;
    }

    public void executeParagraphAction(final Map<Player.Stats, Integer> actions) {
        for (Map.Entry<Player.Stats, Integer> action : actions.entrySet()) {
            getViewState().changeStat(action.getKey(), action.getValue());
        }
    }
}
