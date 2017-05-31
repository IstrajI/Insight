package com.npgames.insight.ui.all.presentation;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Player;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {
    public static final int INIT_HP = 16;
    public static final int INIT_AUR = 2;
    public static final int INIT_DEX = 10;
    public static final int INIT_PRC = 3;
    public static final int INIT_TIME = 30;
    public static final int INIT_AMNS = 8;
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private int pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;

    private Player player;

    public Player loadPlayer(final Context context) {
        if (player == null) {
            player = GamePreferences.getInstance(context).loadPlayer();
        }
        return player;
    }

    public void savePlayer(final Context context) {
        GamePreferences.getInstance(context).savePlayer(player);
    }

    public void updatePlayer(final int dex, final int prc) {
        player.setPrc(prc);
        player.setDex(prc);
    }

    public void changeStat(final Player.Stats statName, final int statValue) {
        switch(statName) {
            case HP:
                player.addHp(statValue);
                break;
            case AUR:
                player.addAur(statValue);
                break;
            case PRC:
                player.addPrc(statValue);
                break;
            case DEX:
                player.addDex(statValue);
                break;
            case TIME:
                player.addTime(statValue);
                break;
            case AMNS:
                player.addAmn(statValue);
                break;
            case COND_TIME:
                Log.d("addPrc", "current prc:" +player.getPrc());
                player.addPrc(statValue);
                break;
        }
    }

    public void updatePlayersParagraph(final int paragraph) {
        player.setParagraph(paragraph);
    }
}
