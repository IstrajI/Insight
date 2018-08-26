package com.npgames.insight.domain;

import android.content.Context;
import android.util.Log;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;

public class CreatePlayerInteractor {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private final StatsRepository statsRepository;
    private final ParagraphRepository paragraphRepository;

    public CreatePlayerInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
    }

    public int dexMinus() {
        //try to return points that wasn't belong to destributed points
        if (paragraphRepository.getPointsToDistribute() < MAX_POINTS_TO_DISTRIBUTE && statsRepository.getStats().getDex() > Player.INIT_DEX) {
            paragraphRepository.updatePointsToDistribute(1);
            paragraphRepository.updateDistributedDexPoints(-1);
            statsRepository.updateStats(Stats.builder()
                    .setDex(-1)
                    .build());
        }

        return statsRepository.getStats().getDex();
    }

    public int dexPlus() {
        //no points to destribute
        if (paragraphRepository.getPointsToDistribute() != 0) {
            paragraphRepository.updatePointsToDistribute(-1);
            paragraphRepository.updateDistributedDexPoints(1);
            statsRepository.updateStats(Stats.builder()
                    .setDex(1)
                    .build());
        }

        return statsRepository.getStats().getDex();
    }

    public int prcMinus() {
        //try to return points that wasn't belong to destributed points
        if (paragraphRepository.getPointsToDistribute() < MAX_POINTS_TO_DISTRIBUTE && statsRepository.getStats().getPrc() > Player.INIT_PRC) {
            paragraphRepository.updatePointsToDistribute(1);
            paragraphRepository.updateDistributedPrcPoints(-1);
            statsRepository.updateStats(Stats.builder()
                    .setPrc(-1)
                    .build());
        }

        return statsRepository.getStats().getPrc();
    }

    public int prcPlus() {
        //no points to destribute
        if (paragraphRepository.getPointsToDistribute() != 0) {
            paragraphRepository.updatePointsToDistribute(-1);
            paragraphRepository.updateDistributedPrcPoints(1);
            statsRepository.updateStats(Stats.builder()
                    .setPrc(1)
                    .build());
        }

        return statsRepository.getStats().getPrc();
    }

    public boolean isDexMin() {
        return statsRepository.getStats().getDex() == Player.INIT_DEX;
    }

    public boolean isPrcMin() {
        return statsRepository.getStats().getPrc() == Player.INIT_PRC;
    }

    public int getPointsToDistribute() {
        return paragraphRepository.getPointsToDistribute();
    }

    public int getPrc() {
        return paragraphRepository.getDistributedPrcPoints() + Player.INIT_PRC;
    }

    public int getDex() {
        return paragraphRepository.getDistributedDexPoints() + Player.INIT_DEX;
    }

    public void addPointsToPlayer() {
        statsRepository.updateStats(Stats.builder()
                        .setDex(paragraphRepository.getDistributedDexPoints() + Player.INIT_DEX)
                        .setPrc(paragraphRepository.getDistributedPrcPoints() + Player.INIT_PRC)
                        .build());
    }

    public void saveDistributedPoints() {
        paragraphRepository.saveDistributedDexPoints();
        paragraphRepository.saveDistributedPrcPoints();
        paragraphRepository.savePointsToDistribute();
    }
}
