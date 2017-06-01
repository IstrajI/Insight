package com.npgames.insight.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.npgames.insight.ui.book.GameBookActivity;
import com.npgames.insight.ui.player.CreatePlayerActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startGameBookActivity(@NonNull final Context context, final GameBookActivity.GameType gameType) {
        final Intent intent = new Intent(context, GameBookActivity.class);
        intent.putExtra(GameBookActivity.GAME_TYPE_KEY, gameType);
        context.startActivity(intent);
    }

    public static void startCreatePlayerActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, CreatePlayerActivity.class);
        context.startActivity(intent);
    }
}
