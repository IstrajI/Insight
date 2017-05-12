package com.npgames.insight.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.npgames.insight.ui.book.GameBookActivity;
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startGameBookActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, GameBookActivity.class);
        context.startActivity(intent);
    }
}
