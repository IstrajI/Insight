package com.npgames.insight.ui.all.presentation.paragraph;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.TrackingParagraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@InjectViewState
public class ParagraphPresenter extends MvpPresenter<ParagraphView> {
    private int currentParagraph;

    private List<TrackingParagraph> trackingParagraphs = new ArrayList<>();

    public void loadLastSavedParagraph(final Context context) {
        currentParagraph = GamePreferences.getInstance(context).loadCurrentParagraph();
        final Paragraph paragraph = ParagraphParser.parse(context, currentParagraph);
        getViewState().updateParagraph(paragraph);
    }

    public void loadParagraph(final Context context, final int number) {
        final Paragraph paragraph = ParagraphParser.parse(context, number);
        currentParagraph = paragraph.getId();
        //executeParagraphAction(number, paragraph.getActions());
        getViewState().updateParagraph(paragraph);
    }

    public void executeParagraphAction(final int paragraphNumber, final Map<Paragraph.ActionTypes, Integer> actions) {
        for (Map.Entry<Paragraph.ActionTypes, Integer> action : actions.entrySet()) {
            checkConditionActions(paragraphNumber, action, actions);
            getViewState().changeStat(action.getKey(), action.getValue());
        }
    }

    public void saveParagraph(final Context context) {
        GamePreferences.getInstance(context).saveCurrentParagraph(currentParagraph);
    }

    public void checkConditionActions(final int paragraphNumber, final Map.Entry<Paragraph.ActionTypes, Integer> action,
                                      final Map<Paragraph.ActionTypes, Integer> actions) {
        switch(action.getKey()) {
            case COND_NFT_TIME:
                final TrackingParagraph trackingParagraph = findOrCreateParagraphAction(paragraphNumber);
                final TrackingParagraph.Action trackingParagraphAction = trackingParagraph.findOrCreateAction(Paragraph.ActionTypes.COND_NFT_TIME);
                if (checkConditionNotFirstTime(action, trackingParagraphAction)) {
                    actions.put(Paragraph.ActionTypes.TIME,actions.remove(action));
                }
                trackingParagraph.getActions().remove(trackingParagraphAction);
                trackingParagraph.getActions().add(trackingParagraphAction);
                trackingParagraphs.add(trackingParagraph);
                break;
        }
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
}
