package com.npgames.insight.ui.book.armory;

import android.content.Context;

import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.repositories.PlayerRepository;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;

import java.util.List;

public class ArmoryPresenter extends MvpPresenter<ArmoryView>{

    private PlayerRepository playerRepository;

    ArmoryPresenter(final Context context) {
        playerRepository = PlayerRepository.getInstance(context);
    }


    void loadEquipmentsOwnedBy(final Equipment.Owner owner) {
        final List<Equipment> equipments = playerRepository.loadEquipmentsOwnedBy(owner);
        getViewState().showEquipment(equipments);
    }

    void loadStatsPlayerStats() {
        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);
    }

    void takeOnEquipment(final Equipment equipmentOn) {
        playerRepository.takeOnEquipment(equipmentOn);
        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);
    }

    void takeOffEquipment(final Equipment equipmentOff) {
        playerRepository.takeOffEquipment(equipmentOff);
        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);
    }

    //TODO: wrong logic
    /*void obtainWearEquipmentStatus() {
        final List<Equipment> equipments = playerRepository.loadEquipmentsOwnedBy(Equipment.Owner.PLAYER);
        for (int i = 0; i < equipments.size(); i++) {
            if (player.canWearEquipment(equipments.get(i))) {
                getViewState().updateWearEquipmentStatus(i, true);
                continue;
            }
            getViewState().updateWearEquipmentStatus(i, false);
        }
    }*/
}
