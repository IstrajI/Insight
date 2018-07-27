package com.npgames.insight.data.repositories;

import android.content.Context;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private final GamePreferences gamePreferences;
    private static Player player;
    private static PlayerRepository playerRepository;

    public static PlayerRepository getInstance(final Context context) {
        if (playerRepository == null) {
            playerRepository = new PlayerRepository(context);
        }

        return playerRepository;
    }

    PlayerRepository(final Context context) {
        gamePreferences = GamePreferences.getInstance(context);
        player = gamePreferences.loadPlayer();
    }

    public void createPlayer() {
        player = new Player();
    }

    public void savePlayer() {
        gamePreferences.savePlayer(player);
    }

    public void changeStat(final Stats statsChanger) {
        player.changeStats(statsChanger);
    }

    public Stats getStats() {
        return player.getStats();
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
}
