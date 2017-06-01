package com.npgames.insight.ui.all.presentation.player;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Player;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {

    private Player player;

    public Player loadPlayer(final Context context) {
        if (player == null) {
            player = GamePreferences.getInstance(context).loadPlayer();
        }
        return player;
    }

    public Player createPlayer() {
        player = new Player();
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
            case AMN:
                player.addAmn(statValue);
                break;
            case COND_TIME:
                Log.d("addPrc", "current prc:" +player.getPrc());
                player.addPrc(statValue);
                break;
        }
    }
}
