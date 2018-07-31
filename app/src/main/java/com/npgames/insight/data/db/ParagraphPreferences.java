package com.npgames.insight.data.db;

import android.content.Context;
import android.content.SharedPreferences;

public class ParagraphPreferences{
    private static ParagraphPreferences paragraphPreferences;
    private final SharedPreferences preferences;

    private final String PREFERENCES_NAME = "PARAGRAPH_PREFERENCES";
    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";

    ParagraphPreferences(final Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static ParagraphPreferences getInstance(final Context context) {
        if (paragraphPreferences == null) {
            paragraphPreferences = new ParagraphPreferences(context);
        }

        return paragraphPreferences;
    }

    public void saveCurrentParagraphNumber(final int currentParagraphNumber) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(CURRENT_PARAGRAPH, currentParagraphNumber);
        editor.apply();
    }

    public int loadCurrentParagraphNumber() {
        return preferences.getInt(CURRENT_PARAGRAPH, 500);
    }
}
