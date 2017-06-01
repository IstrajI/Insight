package com.npgames.insight.data.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.ui.all.presentation.PlayerPresenter;
import com.npgames.insight.ui.book.GameBookPresenter;

public class  GamePreferences {
    private static GamePreferences gamePreferences;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "gamePreferences";

    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";

    private final String PLAYER_HP = "PLAYER_HP";
    private final String PLAYER_AUR = "PLAYER_AUR";
    private final String PLAYER_DEX = "PLAYER_DEX";
    private final String PLAYER_PRC = "PLAYER_PRC";
    private final String PLAYER_TIME = "PLAYER_TIME";
    private final String PLAYER_AMN = "PLAYER_AMN";

    public static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            gamePreferences = new GamePreferences();
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return gamePreferences;
    }

    public Player loadPlayer() {
        final Player player = new Player();
        player.setHp(sharedPreferences.getInt(PLAYER_HP, Player.INIT_HP));
        player.setPrc(sharedPreferences.getInt(PLAYER_PRC, Player.INIT_PRC));
        player.setAur(sharedPreferences.getInt(PLAYER_AUR, Player.INIT_AUR));
        player.setDex(sharedPreferences.getInt(PLAYER_DEX, Player.INIT_DEX));
        player.setTime(sharedPreferences.getInt(PLAYER_TIME, Player.INIT_TIME));
        player.setAmn(sharedPreferences.getInt(PLAYER_AMN, Player.INIT_AMN));
        return player;
    }

    public void savePlayer(final Player player) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PLAYER_HP, player.getHp());
        editor.putInt(PLAYER_AUR, player.getAur());
        editor.putInt(PLAYER_DEX, player.getDex());
        editor.putInt(PLAYER_PRC, player.getPrc());
        editor.putInt(PLAYER_TIME, player.getTime());
        editor.putInt(PLAYER_AMN, player.getAmn());
        editor.apply();
    }

    public int loadCurrentParagraph() {
        return sharedPreferences.getInt(CURRENT_PARAGRAPH, Paragraph.INIT_PARAGRAPH);
    }

    public void saveCurrentParagraph(final int currentParagraph) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_PARAGRAPH, currentParagraph);
        editor.apply();
    }
}
