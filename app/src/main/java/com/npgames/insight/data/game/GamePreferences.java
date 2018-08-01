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

    private Set<String> achievements;

    GamePreferences() {
        achievements = loadAchievements();
    }

    static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            gamePreferences = new GamePreferences();
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return gamePreferences;
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
