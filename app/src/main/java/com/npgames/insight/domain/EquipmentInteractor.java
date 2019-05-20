package com.npgames.insight.domain;

import android.content.Context;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.stats.StatsRepository;

import java.util.ArrayList;
import java.util.List;

public class EquipmentInteractor {
    final EquipmentRepository equipmentRepository;
    final StatsRepository statsRepository;

    List<Equipment> equipments;

    private final int LOW_DEX_BORDER = 5;

    public EquipmentInteractor(final Context context) {
        equipmentRepository = EquipmentRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);

        equipments = loadEquipment();
    }

    public void takeOnEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.PLAYER);
        statsRepository.updateStats(equipment.getTakeOnStatsChanger());
    }

    public void takeOffEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
        statsRepository.updateStats(equipment.getTakeOffStatsChanger());
    }

    public List<Equipment> loadEquipment() {
        final List<Equipment> equipments = equipmentRepository.getEquipments();

        for (final Equipment equipment: equipments) {
            equipment.setCanWear(canWear(equipment));
        }

        return equipments;
    }

    public void dropEquipment(final String equipmentType) {

        for (final Equipment equipment: equipments) {
            if (equipment.getType().equals(equipmentType)) {
                equipment.setOwnedBy(Equipment.Owner.TRASH);
                statsRepository.updateStats(equipment.getTakeOffStatsChanger());
            }
        }
        equipmentRepository.saveEquipment();
    }

    public List<Equipment> getEquipmentsOwnedBy(final String owner) {
        final List<Equipment> resultEquipments = new ArrayList<>();

        for (final Equipment equipment: equipments) {
            if (owner.equals(equipment.getOwnedBy())) {
                resultEquipments.add(equipment);
            }
        }



        return resultEquipments;
        //return equipments;
    }

    private boolean canWear(final Equipment equipment) {
        final int playerDex = statsRepository.getStats().getDex();
        final int equipmentDexChange = equipment.getTakeOnStatsChanger().getDex();

        final boolean isStatsSatisfied = playerDex + equipmentDexChange >= LOW_DEX_BORDER || Equipment.Owner.PLAYER.equals(equipment.getOwnedBy());
        final boolean isTargetterCheckPassed = additionalCheckForTargetter(equipment);

        return isStatsSatisfied && isTargetterCheckPassed;
    }

    private boolean additionalCheckForTargetter(final Equipment equipment) {
        if (Equipment.TYPE.TARGETTER.equals(equipment.getType())) {
            return equipmentRepository.isOwnedBy(Equipment.TYPE.BLASTER, Equipment.Owner.PLAYER);
        } else {
            return true;
        }
    }
}
