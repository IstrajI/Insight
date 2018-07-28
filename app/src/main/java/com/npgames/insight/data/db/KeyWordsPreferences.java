package com.npgames.insight.data.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Set;

public class KeyWordsPreferences {
    private SharedPreferences preferences;
    private static KeyWordsPreferences keyWordsPreferences;
    private final String PREFERENCES_NAME = "KEY_WORDS_PREFERENCES";
    private final String KEYWORDS = "KEYWORDS";

    KeyWordsPreferences(final Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static KeyWordsPreferences getInstance(final Context context) {
        if (keyWordsPreferences == null) {
            keyWordsPreferences = new KeyWordsPreferences(context);
        }

        return keyWordsPreferences;
    }

    public void saveKeyWords(final Set<String> keyWords) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(KEYWORDS, keyWords);
        editor.apply();
    }

    public Set<String> loadKeyWords() {
        return preferences.getStringSet(KEYWORDS, Collections.emptySet());
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({KeyWords.SHINE})
    public @interface KeyWords {
        String SHINE = "SHINE";
    }
}
