package com.npgames.insight.ui.book;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;

public interface GameBookView extends MvpView {
    void updateParagraph(final Paragraph blocks);
    void refreshParagraph(final Paragraph blocks);
    void showFindSuccess();
    void showFindFailed();
    void showStats(final Stats stats);
    void showDeathScreen();
}