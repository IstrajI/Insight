package com.npgames.insight.ui.book.armory;

import android.content.Context;

import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.stats.StatsRepository;

import java.util.List;

public class ArmoryPresenter extends MvpPresenter<ArmoryView>{
    private EquipmentRepository equipmentRepository;
    private StatsRepository statsRepository;

    ArmoryPresenter(final Context context) {
        equipmentRepository = EquipmentRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);
    }


    void loadEquipmentsOwnedBy(final @Equipment.Owner String owner) {
        final List<Equipment> equipments = equipmentRepository.getEquipmentsOwnedBy(owner);
        getViewState().showEquipment(equipments);
    }

    void loadStatsPlayerStats() {
        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void takeOnEquipment(final Equipment equipmentOn) {
/*        playerRepository.takeOnEquipment(equipmentOn);
        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);*/
    }

    void takeOffEquipment(final Equipment equipmentOff) {
/*        playerRepository.takeOffEquipment(equipmentOff);
        final Stats stats = playerRepository.getStats();
        getViewState().showStats(stats);*/
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
