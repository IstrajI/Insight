package com.npgames.insight.domain;

import android.content.Context;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.repositories.StatsRepository;

/**
 * Created by i_straj_i on 28.07.2018.
 */

public class GameInteractor {
    private final StatsRepository statsRepository;

    public GameInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
    }

    public void startNewGame() {
        //reset stats
        final Stats stats = Stats.builder()
                .setAmn(Player.INIT_AMN)
                .setTime(Player.INIT_TIME)
                .setDex(Player.INIT_DEX)
                .setAur(Player.INIT_AUR)
                .setPrc(Player.INIT_PRC)
                .setHp(Player.INIT_HP)
                .build();

        statsRepository.setStats(stats);

        //reset equipment

        //reset keywords

        //reset paragraph
    }
}
