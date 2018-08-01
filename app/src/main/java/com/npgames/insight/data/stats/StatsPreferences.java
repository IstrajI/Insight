package com.npgames.insight.data.stats;

import android.content.Context;
import android.content.SharedPreferences;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;

public class StatsPreferences {
    private final String PREFERENCES_NAME = "STATS_PREFERENCES";
    private final String PLAYER_HP = "PLAYER_HP";
    private final String PLAYER_AUR = "PLAYER_AUR";
    private final String PLAYER_DEX = "PLAYER_DEX";
    private final String PLAYER_PRC = "PLAYER_PRC";
    private final String PLAYER_TIME = "PLAYER_TIME";
    private final String PLAYER_AMN = "PLAYER_AMN";

    private static StatsPreferences statsPreferences;
    private SharedPreferences sharedPreferences;

    StatsPreferences(final Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
    
    public static StatsPreferences getInstance(final Context context) {
        if (statsPreferences == null) {
            statsPreferences = new StatsPreferences(context);
        }

        return statsPreferences;
    }

    public Stats loadStats() {
        final Stats stats = Stats.builder()
                .setHp(sharedPreferences.getInt(PLAYER_HP, Player.INIT_HP))
                .setPrc(sharedPreferences.getInt(PLAYER_PRC, Player.INIT_PRC))
                .setAur(sharedPreferences.getInt(PLAYER_AUR, Player.INIT_AUR))
                .setDex(sharedPreferences.getInt(PLAYER_DEX, Player.INIT_DEX))
                .setTime(sharedPreferences.getInt(PLAYER_TIME, Player.INIT_TIME))
                .setAmn(sharedPreferences.getInt(PLAYER_AMN, Player.INIT_AMN))
                .build();
        return stats;
    }

    public void saveStats(final Stats stats) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PLAYER_HP, stats.getHp());
        editor.putInt(PLAYER_AUR, stats.getAur());
        editor.putInt(PLAYER_DEX, stats.getDex());
        editor.putInt(PLAYER_PRC, stats.getPrc());
        editor.putInt(PLAYER_TIME, stats.getTime());
        editor.putInt(PLAYER_AMN, stats.getAmn());
        editor.apply();
    }


}
