package com.npgames.insight.ui.all.presentation.player;

import android.content.Context;
import android.util.Log;
import android.view.View;

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

    public void loadPlayer(final Context context) {
        if (player == null) {
            player = GamePreferences.getInstance(context).loadPlayer();
        }
        loadStats();    }

    public void createPlayer() {
        player = new Player();
        loadStats();
    }

    public void savePlayer(final Context context) {
        GamePreferences.getInstance(context).savePlayer(player);
    }

    public void updatePlayer(final int dex, final int prc) {
        player.setPrc(prc);
        player.setDex(dex);
        loadStats();
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
        loadStats();
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
        //checkCanWearStatus();
        Log.d("name = ", ""+equipment.getSharedPropertyName());
        getViewState().showWearedEquipment();
        getViewState().showStats(
            player.getHp(),
            player.getAur(),
            player.getPrc(),
            player.getDex(),
            player.getTime(),
            player.getAmn());
        obtainWearStatus();
    }

    public void unwearEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
        //checkCanWearStatus();
        equipment.unwearChangeStats(player);
        getViewState().showStats(
                player.getHp(),
                player.getAur(),
                player.getPrc(),
                player.getDex(),
                player.getTime(),
                player.getAmn());
        obtainWearStatus();
    }

    public void obtainWearStatus() {
        final List<Equipment> equipments = player.getEquipments();
        for (int i = 0; i < equipments.size(); i++) {
            if (!equipments.get(i).wearChangeStats(player)) {
                getViewState().showCantWearEquipment(i);
                return;
            }
            getViewState().showCanWearEquipment(i);
        }
    }

    public void checkCanWearStatus() {
        final List<Equipment> equipments = player.getEquipments();
        for (int i = 0; i < equipments.size(); i++) {
            if (!equipments.get(i).wearChangeStats(player)) {
                getViewState().showCantWearEquipment(i);
                return;
            }
            getViewState().showCanWearEquipment(i);
        }
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

    public void printStats() {
        Log.d("Printing", " stats:");
        Log.d("HP  :", ""+player.getHp());
        Log.d("AUR :", ""+player.getAur());
        Log.d("DEX :", ""+player.getDex());
        Log.d("PRC :", ""+player.getPrc());
        Log.d("TIME:", ""+player.getTime());
        Log.d("AMN :", ""+player.getAmn());
    }
}
