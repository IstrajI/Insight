package com.npgames.insight.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnSuccessListener;
import com.npgames.insight.data.directory.DirectoryItem;
import com.npgames.insight.ui.book.GameBookActivity;
import com.npgames.insight.ui.directory.DirectoryActivity;
import com.npgames.insight.ui.home.HomeScreenActivity;
import com.npgames.insight.ui.home.authors.AuthorsActivity;
import com.npgames.insight.ui.player.CreatePlayerActivity;

import androidx.annotation.NonNull;

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

    public static void startAuthorsActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, AuthorsActivity.class);
        context.startActivity(intent);
    }

    public static void startDirectoryActivity(final Context context) {
        final Intent intent = new Intent(context, DirectoryActivity.class);
        context.startActivity(intent);
    }

    public static void startAchivementsActivity(final Context context, final GoogleSignInAccount googleSignInAccount) {
        Log.d("TestPish", "startAchivements");
        Games.getAchievementsClient(context, googleSignInAccount)
                .getAchievementsIntent()
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(final Intent intent) {
                        context.startActivity(intent);
                    }
                });
    }
}
