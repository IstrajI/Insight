package com.npgames.insight.ui.all.presentation.paragraph;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;

import java.util.Map;

@InjectViewState
public class ParagraphPresenter extends MvpPresenter<ParagraphView> {

    private int currentParagraph;

    public void loadLastSavedParagraph(final Context context) {
        currentParagraph = GamePreferences.getInstance(context).loadCurrentParagraph();
        final Paragraph paragraph = ParagraphParser.parse(context, currentParagraph);
        getViewState().updateParagraph(paragraph);
    }

    public void loadParagraph(final Context context, final int number) {
        final Paragraph paragraph = ParagraphParser.parse(context, number);
        Log.d("id", "paragraphId = " +paragraph.getId());
        currentParagraph = paragraph.getId();
        executeParagraphAction(paragraph.getActions());
        getViewState().updateParagraph(paragraph);
    }

    public void executeParagraphAction(final Map<Player.Stats, Integer> actions) {
        for (Map.Entry<Player.Stats, Integer> action : actions.entrySet()) {
            getViewState().changeStat(action.getKey(), action.getValue());
        }
    }

    public void saveParagraph(final Context context) {
        GamePreferences.getInstance(context).saveCurrentParagraph(currentParagraph);
    }
}
