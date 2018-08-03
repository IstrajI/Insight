package com.npgames.insight.data.paragraph;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

public class ParagraphPreferences{
    private static ParagraphPreferences paragraphPreferences;
    private final SharedPreferences preferences;

    private final String PREFERENCES_NAME = "PARAGRAPH_PREFERENCES";
    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";
    private final String WAS_ACTION_PRESSED = "WAS_ACTION_PRESSED";
    private final String SPECIAL_VISITED_PARAGRAPHS = "SPECIAL_VISITED_PARAGRAPHS";

    ParagraphPreferences(final Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static ParagraphPreferences getInstance(final Context context) {
        if (paragraphPreferences == null) {
            paragraphPreferences = new ParagraphPreferences(context);
        }

        return paragraphPreferences;
    }

    //---------------------------- Visited Paragraphs ----------------------------------------------
    //----------------------------------------------------------------------------------------------

    Set<String> loadSpecialVisitedParagraphs() {
        return preferences.getStringSet(SPECIAL_VISITED_PARAGRAPHS, Collections.emptySet());
    }

    void saveSpecialVisitedParagraphs(final Set<String> specialVisitedParagraphs) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(SPECIAL_VISITED_PARAGRAPHS, specialVisitedParagraphs);
        editor.apply();
    }

    public void saveWasActionPressed(final boolean wasActionPressed) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(WAS_ACTION_PRESSED, wasActionPressed);
        editor.apply();
    }

    public boolean loadWasActionPressed() {
        return preferences.getBoolean(WAS_ACTION_PRESSED, false);
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
