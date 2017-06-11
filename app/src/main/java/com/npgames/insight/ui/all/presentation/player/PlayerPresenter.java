package com.npgames.insight.ui.all.presentation.player;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {

    private static Player player;

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

    public void changeStat(final Paragraph.ActionTypes statName, final int statValue) {
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
        }
    }

    public void printEquipment() {
        for (Equipment equipment : player.getEquipments()) {
            Log.d("equipment ", ""+equipment.getClass());
        }
    }

    public void loadStats() {
        getViewState().showStats(
            player.getHp(),
            player.getAur(),
            player.getPrc(),
            player.getDex(),
            player.getTime(),
            player.getAmn());
    }


    public void wearEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.PLAYER);
        equipment.wearChangeStats(player);
        getViewState().showStats(
            player.getHp(),
            player.getAur(),
            player.getPrc(),
            player.getDex(),
            player.getTime(),
            player.getAmn());
    }

    public void unwearEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
        equipment.wearChangeStats(player);
        getViewState().showStats(
                player.getHp(),
                player.getAur(),
                player.getPrc(),
                player.getDex(),
                player.getTime(),
                player.getAmn());
    }

    public void dropEquipment(final Equipment equipment) {
        //
    }



    public void loadEquipmentsOwnedBy(final Equipment.Owner owner) {
        final List<Equipment> ownerEquipments = new ArrayList<>();
        for (Equipment equipment : player.getEquipments()) {
            if (equipment.getOwnedBy() == owner) ownerEquipments.add(equipment);
        }
        getViewState().showEquipmentsOwnedBy(ownerEquipments);
    }
}
