package com.npgames.insight.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.npgames.insight.data.model.equipment.AidKit;
import com.npgames.insight.data.model.equipment.Beam;
import com.npgames.insight.data.model.equipment.Blaster;
import com.npgames.insight.data.model.equipment.Electroshock;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.model.equipment.FlakJacket;
import com.npgames.insight.data.model.equipment.Grenade_1;
import com.npgames.insight.data.model.equipment.Grenade_2;
import com.npgames.insight.data.model.equipment.Grenade_3;
import com.npgames.insight.data.model.equipment.OpenSpaceEqpt;
import com.npgames.insight.data.model.equipment.PowerShield;
import com.npgames.insight.data.model.equipment.Targeter;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPreferences {
    private EquipmentPreferences equipmentPreferences;
    private final String PREFERENCES_NAME = "EQUIPMENT_PREFERENCES";

    EquipmentPrefereces(final Context context) {
        equipmentPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static EquipmentPreferences getInstance(final Context context) {
        if (equipmentPreferences == null) {
            equipmentPreferences = new EquipmentPreferences(context);
        }

        return equipmentPreferences;
    }

    public void saveEqupment(final List<Equipment> equipments) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        for (final Equipment equipment: equipments) {
            final String ownedByString = String.valueOf(equipment.getOwnedBy());
            editor.putString(equipment.getSharedPropertyName(), ownedByString);
        }

        editor.apply();
    }

    public List<Equipment> loadEquipment() {
        final List<Equipment> equipments = new ArrayList<>();
        equipments.add(new AidKit(loadEquipmentOwnedBy()));
        equipments.add(new Beam(loadEquipmentOwnedBy(Beam.SHARED_PROPERTY_NAME)));
        equipments.add(new Blaster(loadEquipmentOwnedBy(Blaster.SHARED_PROPERTY_NAME)));
        equipments.add(new Grenade_1(loadEquipmentOwnedBy(Grenade_1.SHARED_PROPERTY_NAME)));
        equipments.add(new Grenade_2(loadEquipmentOwnedBy(Grenade_2.SHARED_PROPERTY_NAME)));
        equipments.add(new Grenade_3(loadEquipmentOwnedBy(Grenade_3.SHARED_PROPERTY_NAME)));
        equipments.add(new Electroshock(loadEquipmentOwnedBy(Electroshock.SHARED_PROPERTY_NAME)));
        equipments.add(new FlakJacket(loadEquipmentOwnedBy(FlakJacket.SHARED_PROPERTY_NAME)));
        equipments.add(new OpenSpaceEqpt(loadEquipmentOwnedBy(OpenSpaceEqpt.SHARED_PROPERTY_NAME)));
        equipments.add(new PowerShield(loadEquipmentOwnedBy(PowerShield.SHARED_PROPERTY_NAME)));
        equipments.add(new Targeter(loadEquipmentOwnedBy(Targeter.SHARED_PROPERTY_NAME)));
        return equipments;
    }
}
