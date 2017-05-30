package com.npgames.insight.ui.book;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;

public interface GameBookView extends MvpView{
    void updateParagraph(final Paragraph nextParagraph);
    void openActionsMenu();
    void closeActionsMenu();
    void openStatsPanel();
    void closeStatsPanel();

    void changeStat(final Player.Stats stats, final int difference);

}