package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.EquipmentPreferences;
import com.npgames.insight.data.model.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository {
    private static EquipmentRepository equipmentRepository;
    private EquipmentPreferences equipmentPreferences;
    private static List<Equipment> equipments;

    public static EquipmentRepository getInstance(final Context context) {
        if (equipmentRepository == null) {
            equipmentRepository = new EquipmentRepository(context);
        }

        return equipmentRepository;
    }

    EquipmentRepository(final Context context) {
        equipmentPreferences = EquipmentPreferences.getInstance(context);
        equipments = equipmentPreferences.loadEquipment();
    }

    public List<Equipment> getEquipmentsOwnedBy(final @Equipment.Owner String owner) {
        final List<Equipment> resultList = new ArrayList<>();

        for (final Equipment equipment : equipments) {
            if (owner.equals(String.valueOf(equipment.getOwnedBy()))) resultList.add(equipment);
        }

        return resultList;
    }
}
