package com.npgames.insight.data.paragraph;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ParagraphPreferences{
    private static ParagraphPreferences paragraphPreferences;
    private final SharedPreferences preferences;

    private final String PREFERENCES_NAME = "PARAGRAPH_PREFERENCES";
    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";
    private final String WAS_ACTION_PRESSED = "WAS_ACTION_PRESSED";
    private final String SPECIAL_VISITED_PARAGRAPHS = "SPECIAL_VISITED_PARAGRAPHS";
    private final String DISTRIBUTES_DEX_POINTS = "DISTRIBUTES_DEX_POINTS";
    private final String DISTRIBUTES_PRC_POINTS = "DISTRIBUTES_PRC_POINTS";
    private final String POINTS_TO_DISTRIBUTE = "POINTS_TO_DISTRIBUTE";

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
        return preferences.getStringSet(SPECIAL_VISITED_PARAGRAPHS, new HashSet<>());
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


    //---------------------------- Create Player ---------------------------------------------------
    //----------------------------------------------------------------------------------------------
    public int loadDistributedDexPoints() {
        return preferences.getInt(DISTRIBUTES_DEX_POINTS, 0);
    }

    public void saveDistributedDexPoints(final int distributedDexPoints) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(DISTRIBUTES_DEX_POINTS, distributedDexPoints);
        editor.apply();
    }

    public int loadDistributedPrcPoints() {
        return preferences.getInt(DISTRIBUTES_PRC_POINTS, 0);
    }


    public void saveDistributedPrcPoints(final int distributedPrcPoints) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(DISTRIBUTES_PRC_POINTS, distributedPrcPoints);
        editor.apply();
    }

    //---------------------------- D points --------------------------------------------------------
    public int loadPointsToDistribute() {
        final int MAX_POINTS_TO_DISTRIBUTE = 4;
        final int pointsToDistribute = preferences.getInt(POINTS_TO_DISTRIBUTE, MAX_POINTS_TO_DISTRIBUTE);
        Log.d("TestPish", "load preferences pointsToD = "+pointsToDistribute);
        return pointsToDistribute;
    }

    public void savePointsToDistribute(final int pointsToDistribute) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(POINTS_TO_DISTRIBUTE, pointsToDistribute);
        Log.d("TestPish", "save preferences pointsToD = "+pointsToDistribute);
        editor.apply();
    }
}
