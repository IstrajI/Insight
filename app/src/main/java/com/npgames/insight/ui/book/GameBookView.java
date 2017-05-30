package com.npgames.insight.ui.book;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Paragraph;

public interface GameBookView extends MvpView{
    void updateParagraph(final Paragraph nextParagraph);
    void openActionsMenu();
    void closeActionsMenu();
    void openStatsPanel();
    void closeStatsPanel();
}
