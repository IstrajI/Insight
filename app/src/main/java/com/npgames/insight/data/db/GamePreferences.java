package com.npgames.insight.data.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;

import com.npgames.insight.data.model.Paragraph;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.npgames.insight.data.db.GamePreferences.Achievements.NATURALIST;

public class GamePreferences {
    private static GamePreferences gamePreferences;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "gamePreferences";

    private final String ACHIEVEMENTS = "ACHIEVEMENTS";
    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";

    private final Set<String> achievements = new HashSet<>();

    public static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            gamePreferences = new GamePreferences();
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return gamePreferences;
    }

    public int loadCurrentParagraph() {
        return sharedPreferences.getInt(CURRENT_PARAGRAPH, Paragraph.INIT_PARAGRAPH);
    }

    public void saveCurrentParagraph(final int currentParagraph) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_PARAGRAPH, currentParagraph);
        editor.apply();
    }

    public void saveAchievements(final Set<String> achievements) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(ACHIEVEMENTS, achievements);
        editor.apply();
    }

    public Set<String> loadAchievements() {
        return sharedPreferences.getStringSet(ACHIEVEMENTS, Collections.emptySet());
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({NATURALIST})
    public @interface Achievements {
        String NATURALIST = "NATURALIST";
    }
}
