package com.npgames.insight.domain;

import android.content.Context;

import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.paragraph.ParagraphRepository;

public class UserActionInteractor {
    private final int MED_BAY_PARAGRAPH = 54;
    private final int STATION_PARAGRAPH = 95;
    private final int SEARCHING_PARAGRAPH_INCREMENT = 10;

    private ParagraphRepository paragraphRepository;

    public UserActionInteractor(final Context context) {
        paragraphRepository = ParagraphRepository.getInstance(context);
    }

    public Paragraph loadMedBay(final int availableHeight) {
        return paragraphRepository.getNextParagraph(MED_BAY_PARAGRAPH, availableHeight);
    }

    public Paragraph loadStation(final int availableHeight) {
        return paragraphRepository.getNextParagraph(STATION_PARAGRAPH, availableHeight);
    }

    public Paragraph loadSeachingParagraph(final int availableHeight) {
        return paragraphRepository.getNextParagraph(paragraphRepository.getParagraphNumber() + SEARCHING_PARAGRAPH_INCREMENT, availableHeight);
    }




}
