package com.npgames.insight.domain;

import android.content.Context;

import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;

public class UserActionInteractor {
    private final int MED_BAY_PARAGRAPH = 54;
    private final int STATION_PARAGRAPH = 95;
    private final int SEARCHING_PARAGRAPH_INCREMENT = 10;

    private ParagraphRepository paragraphRepository;
    private StatsRepository statsRepository;

    public UserActionInteractor(final Context context) {
        paragraphRepository = ParagraphRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);
    }

    public Paragraph loadMedBay(final int availableHeight) {
        return paragraphRepository.getNextParagraph(MED_BAY_PARAGRAPH, availableHeight);
    }

    public Paragraph loadStation(final int availableHeight) {
        return paragraphRepository.getNextParagraph(STATION_PARAGRAPH, availableHeight);
    }

    public Paragraph loadSearchingParagraph(final int availableHeight) {
        return paragraphRepository.getNextParagraph(paragraphRepository.getParagraph().paragraphNumber + SEARCHING_PARAGRAPH_INCREMENT, availableHeight);
    }

    public Stats spendTime() {
        statsRepository.updateStats(Stats.builder()
                .setTime(-1)
                .build());

        return statsRepository.getStats();
    }
}
