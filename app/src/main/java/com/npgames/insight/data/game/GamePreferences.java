package com.npgames.insight.data.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.npgames.insight.data.game.GamePreferences.Achievements.NATURALIST;

public class GamePreferences {
    private static GamePreferences gamePreferences;
    private static SharedPreferences sharedPreferences;

    private static final String PREFERENCES_NAME = "gamePreferences";
    private final String ACHIEVEMENTS = "ACHIEVEMENTS";
    private final String CONTINUE_GAME_AVAILABLE = "CONTINUE_GAME_AVAILABLE";

    private Set<String> achievements;

    GamePreferences() {
        achievements = loadAchievements();
    }

    static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
            gamePreferences = new GamePreferences();
        }
        return gamePreferences;
    }


    //---------------------------- Continue Game Available -----------------------------------------
    //----------------------------------------------------------------------------------------------
    boolean loadContinueGameAvailable() {
        return sharedPreferences.getBoolean(CONTINUE_GAME_AVAILABLE, false);
    }

    void saveContinueGameAvailable(final boolean continueGameAvailable) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CONTINUE_GAME_AVAILABLE, continueGameAvailable);
        editor.apply();
    }

    //---------------------------- Achievements ----------------------------------------------------
    //----------------------------------------------------------------------------------------------

    void saveAchievements(final Set<String> achievements) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(ACHIEVEMENTS, achievements);
        editor.apply();
    }

    Set<String> loadAchievements() {
        return sharedPreferences.getStringSet(ACHIEVEMENTS, Collections.emptySet());
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({NATURALIST})
    public @interface Achievements {
        String NATURALIST = "NATURALIST";
    }
}
