package com.npgames.insight.application;

import android.util.DisplayMetrics;

public class ScreenUtils {

    private static int screenWidthParam;
    private static int screenHeightParam;

    public static void init(final int screenWidth, final int screenHeight) {
        screenWidthParam = screenWidth;
        screenHeightParam = screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidthParam;
    }

    public static int getScreenHeight() {
        return screenHeightParam;
    }
}
