package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.EquipmentPreferences;
import com.npgames.insight.data.model.equipment.Equipment;

import java.util.List;

public class EquipmentRepository {
    public static EquipmentRepository equipmentRepository;
    public static List<Equipment> equipments;

    public static EquipmentRepository getInstance(final Context context) {
        if (equipmentRepository == null) {
            equipmentRepository = new EquipmentRepository(context);
        }

        return equipmentRepository;
    }

    EquipmentRepository(final Context context) {
        equipmentRepository = EquipmentPreferences.getInstance(context);
        equipments = equipmentRepository.loadEquipment();
    }





}
