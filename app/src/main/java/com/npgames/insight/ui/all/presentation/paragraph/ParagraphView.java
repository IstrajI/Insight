package com.npgames.insight.ui.all.presentation.paragraph;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;

public interface ParagraphView extends MvpView{
    void updateParagraph(final Paragraph nextParagraph);
    void changeStat(final Paragraph.ActionTypes actionTypes, final int difference);
}
