package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.StatsPreferences;
import com.npgames.insight.data.model.Stats;

public class StatsRepository {
    private static StatsRepository statsRepository;
    private final StatsPreferences statsPreferences;
    private Stats stats;

    StatsRepository(final Context context) {
        statsPreferences = StatsPreferences.getInstance(context);
        stats = statsPreferences.loadStats();
    }

    public static StatsRepository getInstance(final Context context){
        if (statsRepository == null) {
            statsRepository = new StatsRepository(context);
        }

        return statsRepository;
    }

    public void setStats(final Stats stats) {
        this.stats = stats;
    }

    public Stats getStats() {
        return this.stats;
    }

    public Stats updateStats(final Stats stats) {
        this.stats.setAmn(stats.getAmn() == 0 ? this.stats.getAmn() : this.stats.getAmn() + stats.getAmn());
        this.stats.setAur(stats.getAur() == 0 ? this.stats.getAur() : this.stats.getAur() + stats.getAur());
        this.stats.setHp(stats.getHp() == 0 ? this.stats.getHp() : this.stats.getHp() + stats.getHp());
        this.stats.setDex(stats.getDex() == 0 ? this.stats.getDex() : this.stats.getDex() + stats.getDex());
        this.stats.setPrc(stats.getPrc() == 0 ? this.stats.getPrc() : this.stats.getPrc() + stats.getPrc());
        this.stats.setTime(stats.getTime() == 0 ? this.stats.getTime() : this.stats.getTime() + stats.getTime());

        statsPreferences.saveStats(this.stats);
        return this.stats;
    }
}
