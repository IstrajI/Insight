package com.npgames.insight.ui.book;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.ParagraphParser;

@InjectViewState
public class GameBookPresenter extends MvpPresenter<GameBookView>{
    private boolean isActionsMenuOpen = false;
    void loadNextParagraph(final Context context, final int paragraphNumber) {
        getViewState().updateParagraph(ParagraphParser.parse(context, paragraphNumber));
    }

    void interactWithActionsMenu() {
        if (isActionsMenuOpen) {
            getViewState().closeActionsMenu();
            isActionsMenuOpen = false;
            return;
        }
        getViewState().openActionsMenu();
        isActionsMenuOpen = true;
    }
}
