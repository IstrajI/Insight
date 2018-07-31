package com.npgames.insight.ui.book;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;

public interface GameBookView extends MvpView {
    void updateParagraph(final Paragraph blocks);
    void showFindSuccess(int paragraphNumber, int paragraphResId);
    void showFindFailed();
    void showStats(final Stats stats);
    void showParagraph(int paragraphNumber);-
    void disableJumps();
    void showDeathScreen();
}