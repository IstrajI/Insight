package com.npgames.insight.domain;

import android.content.Context;
import android.util.Log;

import com.npgames.insight.application.StringUtills;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;

public class UserActionInteractor {
    private final int MED_BAY_PARAGRAPH = 54;
    private final int STATION_PARAGRAPH = 95;
    private final int SEARCHING_PARAGRAPH_INCREMENT = 10;
    private final int ARMORY_PARAGRAPH = 100;

    private ParagraphRepository paragraphRepository;
    private StatsRepository statsRepository;

    public UserActionInteractor(final Context context) {
        paragraphRepository = ParagraphRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);
    }

    public int getMedBayNumber() {
        return MED_BAY_PARAGRAPH;
    }

    public int getStationNumber() {
        return STATION_PARAGRAPH;
    }

    public int getArmoryNumber() {
        return ARMORY_PARAGRAPH;
    }

    public int getSearchingParagraphNumber() {
        Log.d("TestPish", "currentParagraph =" +paragraphRepository.getParagraph().paragraphNumber);
        return paragraphRepository.getParagraph().paragraphNumber + SEARCHING_PARAGRAPH_INCREMENT;
    }

    public Stats spendTime() {
        statsRepository.updateStats(Stats.builder()
                .setTime(-1)
                .build());

        return statsRepository.getStats();
    }
}
