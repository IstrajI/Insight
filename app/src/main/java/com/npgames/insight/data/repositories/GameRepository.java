package com.npgames.insight.data.repositories;

import android.content.Context;

public class GameRepository {
    private static GameRepository gameRepository;
    private GamePreferences gamePreferences;
    private Set<String> achievements;

    public static GameRepository getInstance(final Context context) {
        if (gameRepository == null) {
            gameRepository = new GameRepository(context);
        }

        return gameRepository;
    }

    GameRepository(final Context context) {
        gamePreferences = GamePreferences.getInstance(context);
        achievements = gamePreferences.loadAchievements();
    }

    public void addAchievements(final @Achievements String achievement) {
        achievements.add(achievement);
    }

    public Set<String> getAchievements() {
        return achievements;
    }

    public void updateAchievements() {
        gamePreferences.saveAchievements(achievement);
    }
}
