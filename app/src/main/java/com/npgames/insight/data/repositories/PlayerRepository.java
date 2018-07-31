package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.GamePreferences;
import com.npgames.insight.data.model.Player;

/*public class PlayerRepository {
    private final GamePreferences gamePreferences;
    private static Player player;
    private static PlayerRepository playerRepository;

    public static PlayerRepository getInstance(final Context context) {
        if (playerRepository == null) {
            playerRepository = new PlayerRepository(context);
        }

        return playerRepository;
    }

    PlayerRepository(final Context context) {
        gamePreferences = GamePreferences.getInstance(context);
        player = gamePreferences.loadPlayer();
    }

}*/
