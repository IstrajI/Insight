package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.GamePreferences;

import java.util.Set;

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

    public void addAchievement(final @GamePreferences.Achievements String achievement) {
        achievements.add(achievement);
    }

    public Set<String> getAchievements() {
        return achievements;
    }

    public void updateAchievements(final Set<String> achievements) {
        gamePreferences.saveAchievements(achievements);
    }
}
