package com.npgames.insight.data.game;

import android.content.Context;

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

    //---------------------------- Achievement -----------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public void addAchievement(final @GamePreferences.Achievements String achievement) {
        achievements.add(achievement);
    }

    public Set<String> getAchievements() {
        return achievements;
    }

    public void saveAchievements() {
        gamePreferences.saveAchievements(achievements);
    }

    //---------------------------- Continue Game Available -----------------------------------------
    //----------------------------------------------------------------------------------------------
    public boolean getContinueGameAvailable() {
        return gamePreferences.loadContinueGameAvailable();
    }

    public void saveContinueGameAvailable(final boolean continueGameAvailable) {
        gamePreferences.saveContinueGameAvailable(continueGameAvailable);
    }
}
