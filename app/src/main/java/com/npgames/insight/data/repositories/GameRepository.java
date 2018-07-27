package com.npgames.insight.data.repositories;

import android.content.Context;

public class GameRepository {
    private static GameRepository gameRepository;

    public static GameRepository getInstance(final Context context) {
        if (gameRepository == null) {
            gameRepository = new GameRepository(context);
        }

        return gameRepository;
    }

    GameRepository(final Context context) {

    }


    public void addAchievement(final String achievement) {

    }
}
