package com.npgames.insight.ui.book;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.ParagraphParser;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView>{
    private boolean isActionsMenuOpen = false;
    private boolean isStatsPanelOpen = false;

    public static final int INIT_PARAGRAPH = 500;
    public void loadNextParagraph(final Context context, final int paragraphNumber) {

        getViewState().updateParagraph(ParagraphParser.parse(context, paragraphNumber));
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
}
