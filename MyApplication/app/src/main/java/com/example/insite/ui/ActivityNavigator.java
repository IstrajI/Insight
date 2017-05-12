package com.example.insite.ui;

import android.content.Context;
import android.content.Intent;

import com.example.insite.ui.gamebook.GamebookActivity;

public class ActivityNavigator {

    public static void startGameBookActivity(final Context context) {
        Intent intent = new Intent(context, GamebookActivity.class);
        context.startActivity(intent);
    }
}
