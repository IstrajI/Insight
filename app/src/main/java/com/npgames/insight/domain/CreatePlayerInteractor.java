package com.npgames.insight.domain;

import android.content.Context;
import android.util.Log;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.stats.StatsRepository;

public class CreatePlayerInteractor {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private int pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;
    private int dex = Player.INIT_DEX;
    private int prc = Player.INIT_PRC;
    private final StatsRepository statsRepository;

    public CreatePlayerInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
    }

    public int dexMinus() {
        //try to return points that wasn't belong to destributed points
        if (pointsToDistribute < MAX_POINTS_TO_DISTRIBUTE || dex > Player.INIT_DEX) {
            pointsToDistribute++;
            dex--;
            statsRepository.updateStats(Stats.builder()
                    .setDex(-1)
                    .build());
        }

        return dex;
    }

    public int dexPlus() {
        //no points to destribute
        if (pointsToDistribute != 0) {
            pointsToDistribute--;
            dex++;
            statsRepository.updateStats(Stats.builder()
                    .setDex(1)
                    .build());
        }

        return dex;
    }

    public int prcMinus() {
        //try to return points that wasn't belong to destributed points
        if (pointsToDistribute < MAX_POINTS_TO_DISTRIBUTE || prc > Player.INIT_PRC) {
            pointsToDistribute++;
            prc--;
            statsRepository.updateStats(Stats.builder()
                    .setPrc(-1)
                    .build());
        }

        return prc;
    }

    public int prcPlus() {
        //no points to destribute
        if (pointsToDistribute != 0) {
            pointsToDistribute--;
            prc++;
            statsRepository.updateStats(Stats.builder()
                    .setPrc(1)
                    .build());
        }

        return prc;
    }

    public boolean isDexMin() {
        return dex == Player.INIT_DEX;
    }

    public boolean isPrcMin() {
        return prc == Player.INIT_PRC;
    }

    public int getPointsToDistribute() {
        return pointsToDistribute;
    }

    public void addPointsToPlayer() {
        Log.d("TestPish", "current Dex = " +dex);
        Log.d("TestPish", "current Prc = " +prc);

        statsRepository.updateStats(Stats.builder()
                        .setDex(dex - Player.INIT_DEX)
                        .setPrc(prc - Player.INIT_PRC)
                        .build());

        Log.d("TestPish", "Prc: "+statsRepository.getStats().getPrc());
        Log.d("TestPish", "Dex: "+statsRepository.getStats().getDex());
    }
}
