package com.npgames.insight.data.equipment;

import android.content.Context;
import android.content.SharedPreferences;

import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.model.equipment.Equipment.TYPE;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPreferences {
    private SharedPreferences preferences;
    private static EquipmentPreferences equipmentPreferences;
    private final String PREFERENCES_NAME = "EQUIPMENT_PREFERENCES";

    EquipmentPreferences(final Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static EquipmentPreferences getInstance(final Context context) {
        if (equipmentPreferences == null) {
            equipmentPreferences = new EquipmentPreferences(context);
        }

        return equipmentPreferences;
    }

    void saveEqupment(final List<Equipment> equipments) {
        final SharedPreferences.Editor editor = preferences.edit();

        for (final Equipment equipment: equipments) {
            editor.putString(equipment.getType(), equipment.getOwnedBy());
        }

        editor.apply();
    }

    List<Equipment> loadEquipment() {
        final List<Equipment> equipments = new ArrayList<>();

        //Aid Kit
        final Stats aidKitTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats aidKitTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String aidKitOwner = preferences.getString(TYPE.AID_KIT, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.AID_KIT, aidKitTakeOnStatsChanger, aidKitTakeOffStatsChanger, aidKitOwner));

        //Beam
        final Stats beamTakeOnStatsChanger = Stats.builder().setDex(-2).build();
        final Stats beamTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String beamOwner = preferences.getString(TYPE.BEAM, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.BEAM, beamTakeOnStatsChanger, beamTakeOffStatsChanger, beamOwner));

        //Blaster
        final Stats blasterTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats blasterTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String blasterOwner = preferences.getString(TYPE.BLASTER, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.BLASTER, blasterTakeOnStatsChanger, blasterTakeOffStatsChanger, blasterOwner));

        //Electroshock
        final Stats electroshockTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats electroshockTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String electroshockOwner = preferences.getString(TYPE.ELECTROSHOCK, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.ELECTROSHOCK, electroshockTakeOnStatsChanger, electroshockTakeOffStatsChanger, electroshockOwner));

        //FlakJacket
        final Stats flakJacketTakeOnStatsChanger = Stats.builder().setDex(-2).build();
        final Stats flakJacketTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String flakJacketOwner = preferences.getString(TYPE.FlAK_JACKET, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.FlAK_JACKET, flakJacketTakeOnStatsChanger, flakJacketTakeOffStatsChanger, flakJacketOwner));

        //OpenSpaceEqpt
        final Stats openSpaceEqptTakeOnStatsChanger = Stats.builder().setDex(-3).build();
        final Stats openSpaceEqptTakeOffStatsChanger = Stats.builder().setDex(3).build();
        final @Equipment.Owner String openSpaceEqptOwner = preferences.getString(TYPE.OPEN_SPACE_EQUIPMENT, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.OPEN_SPACE_EQUIPMENT, openSpaceEqptTakeOnStatsChanger, openSpaceEqptTakeOffStatsChanger, openSpaceEqptOwner));

        //Power Shield
        final Stats powerShieldTakeOnStatsChanger = Stats.builder().setPrc(-2).build();
        final Stats powerShieldTakeOffStatsChanger = Stats.builder().setPrc(2).build();
        final @Equipment.Owner String powerShieldOwner = preferences.getString(TYPE.POWER_SHIELD, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.OPEN_SPACE_EQUIPMENT, powerShieldTakeOnStatsChanger, powerShieldTakeOffStatsChanger, powerShieldOwner));

        //Targeter Shield
        final Stats targeterTakeOnStatsChanger = Stats.builder().setPrc(-2).build();
        final Stats targeterTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String targeterOwner = preferences.getString(TYPE.TARGETTER, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.TARGETTER, targeterTakeOnStatsChanger, targeterTakeOffStatsChanger, targeterOwner));

        //Grenades
        final Stats grenadeTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats grenadeTakeOffStatsChanger = Stats.builder().setDex(1).build();

        final @Equipment.Owner String grenade_1_Owner = preferences.getString(TYPE.GRENADE_1, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_1_Owner));

        final @Equipment.Owner String grenade_2_Owner = preferences.getString(TYPE.GRENADE_2, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_2_Owner));

        final @Equipment.Owner String grenade_3_Owner = preferences.getString(TYPE.GRENADE_3, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_3_Owner));

        return equipments;
    }
}
