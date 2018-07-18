package com.npgames.insight.data.dao;

import android.content.Context;
import android.util.Log;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.StatsChanger;
import com.npgames.insight.data.model.equipment.Equipment;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private GamePreferences gamePreferences;
    private static Player player;
    private static PlayerRepository playerRepository;
    private ParagraphActionsChecker paragraphActionsChecker;
    private ParagraphJumpsChecker paragraphJumpsChecker;

    public static PlayerRepository getInstance(final Context context) {
        if (playerRepository == null) {
            playerRepository = new PlayerRepository(context);
        }

        return playerRepository;
    }

    PlayerRepository(final Context context) {
        gamePreferences = GamePreferences.getInstance(context);
        player = gamePreferences.loadPlayer();
        //paragraphActionsChecker = new ParagraphActionsChecker();
        paragraphJumpsChecker = new ParagraphJumpsChecker(player);
    }

    public Player getPlayer() {
        return player;
    }



    public void createPlayer() {
        player = new Player();
    }

    public void savePlayer() {
        gamePreferences.savePlayer(player);
    }

    public void updatePlayer(final int dex, final int prc) {
        player.getStats().setPrc(prc);
        player.getStats().setDex(dex);
    }

    public void changeStat(final StatsChanger statsChanger) {
        player.changeStats(statsChanger);
    }

    public Stats getStats() {
        return Stats.builder()
                .setAmn(player.getStats().getAmn())
                .setAur(player.getStats().getAur())
                .setDex(player.getStats().getDex())
                .setHp(player.getStats().getHp())
                .setPrc(player.getStats().getPrc())
                .setTime(player.getStats().getTime())
                .build();
    }

    public void takeOnEquipment(final Equipment equipment) {
        //layer.takeOnEquipment(equipment);
        obtainWearEquipmentStatus();
    }

    public void takeOffEquipment(final Equipment equipment) {
        //player.takeOffEquipment(equipment);
        obtainWearEquipmentStatus();
    }

    public void obtainWearEquipmentStatus() {

    }

    public List<Equipment> loadEquipmentsOwnedBy(final Equipment.Owner owner) {
        final List<Equipment> ownerEquipments = new ArrayList<>();

        for (Equipment equipment : player.getEquipments()) {
            if (equipment.getOwnedBy() == owner) ownerEquipments.add(equipment);
        }

        return ownerEquipments;
    }


    public void updateStats(final Stats stats) {
        player.getStats().setAmn(stats.getAmn() == 0 ? player.getStats().getAmn() : stats.getAmn());
        player.getStats().setAur(stats.getAur() == 0 ? player.getStats().getAur() : stats.getAur());
        player.getStats().setHp((stats.getHp() == 0 ? player.getStats().getHp() : stats.getHp()));
        player.getStats().setDex(stats.getDex() == 0 ? player.getStats().getDex() : stats.getDex());
        player.getStats().setPrc(stats.getPrc() == 0 ? player.getStats().getTime() : stats.getPrc());
        player.getStats().setTime(stats.getTime() == 0 ? player.getStats().getTime() : stats.getTime());
    }


    public void printStats() {
        Log.d("Printing", " stats:");
        Log.d("HP  :", "" + player.getStats().getHp());
        Log.d("AUR :", "" + player.getStats().getAur());
        Log.d("DEX :", "" + player.getStats().getDex());
        Log.d("PRC :", "" + player.getStats().getPrc());
        Log.d("TIME:", "" + player.getStats().getTime());
        Log.d("AMN :", "" + player.getStats().getAmn());
    }
}
