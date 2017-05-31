package com.npgames.insight.data.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.ui.all.presentation.PlayerPresenter;
import com.npgames.insight.ui.book.GameBookPresenter;

public class  GamePreferences {
    private static GamePreferences gamePreferences;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "gamePreferences";

    private final String PLAYER_PARAGRAPH = "PLAYER_PARAGRAPH";
    private final String PLAYER_HP = "PLAYER_HP";
    private final String PLAYER_AUR = "PLAYER_AUR";
    private final String PLAYER_DEX = "PLAYER_DEX";
    private final String PLAYER_PRC = "PLAYER_PRC";
    private final String PLAYER_TIME = "PLAYER_TIME";
    private final String PLAYER_AMNS = "PLAYER_AMNS";

    //private Context context;


    public static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            gamePreferences = new GamePreferences();
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return gamePreferences;
    }

/*    public boolean isSaveExist() {
        return sharedPreferences.getInt(PLAYER_PARAGRAPH, GameBookPresenter.INIT_PARAGRAPH) !=
                GameBookPresenter.INIT_PARAGRAPH;
    }*/

    public Player loadPlayer() {
        final Player player = new Player();
        player.setParagraph(sharedPreferences.getInt(PLAYER_PARAGRAPH, GameBookPresenter.INIT_PARAGRAPH));
        player.setHp(sharedPreferences.getInt(PLAYER_HP, PlayerPresenter.INIT_HP));
        player.setPrc(sharedPreferences.getInt(PLAYER_PRC, PlayerPresenter.INIT_PRC));
        player.setAur(sharedPreferences.getInt(PLAYER_AUR, PlayerPresenter.INIT_AUR));
        player.setDex(sharedPreferences.getInt(PLAYER_DEX, PlayerPresenter.INIT_DEX));
        player.setTime(sharedPreferences.getInt(PLAYER_TIME, PlayerPresenter.INIT_TIME));
        player.setAmn(sharedPreferences.getInt(PLAYER_AMNS, PlayerPresenter.INIT_AMNS));
        Log.d("players hp", ""+player.getHp());
        return player;
    }

    public void savePlayer(final Player player) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PLAYER_PARAGRAPH, player.getParagraph());
        editor.putInt(PLAYER_HP, player.getHp());
        editor.putInt(PLAYER_AUR, player.getAur());
        editor.putInt(PLAYER_DEX, player.getDex());
        editor.putInt(PLAYER_PRC, player.getPrc());
        editor.putInt(PLAYER_TIME, player.getTime());
        editor.putInt(PLAYER_AMNS, player.getAmn());
        editor.apply();
    }
}
