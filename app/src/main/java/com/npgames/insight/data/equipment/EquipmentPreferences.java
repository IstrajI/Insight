package com.npgames.insight.data.equipment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.model.Equipment.TYPE;

import java.util.ArrayList;
import java.util.List;

public class EquipmentPreferences {
    private SharedPreferences preferences;
    private static EquipmentPreferences equipmentPreferences;

    private final String PREFERENCES_NAME = "EQUIPMENT_PREFERENCES";

    private Resources resources;

    EquipmentPreferences(final Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        resources = context.getResources();
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
        final String aidKitName = resources.getString(R.string.armory_equipment_aid_kit_title);
        final String aidKitDescription = resources.getString(R.string.armory_equipment_aid_kit_description);
        equipments.add(new Equipment(TYPE.AID_KIT, aidKitTakeOnStatsChanger, aidKitTakeOffStatsChanger, aidKitOwner, aidKitName, aidKitDescription));

        //Beam
        final Stats beamTakeOnStatsChanger = Stats.builder().setDex(-2).build();
        final Stats beamTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String beamOwner = preferences.getString(TYPE.BEAM, String.valueOf(Equipment.Owner.ARRMORY));
        final String beamName = resources.getString(R.string.armory_equipment_beam_title);
        final String beamDescription = resources.getString(R.string.armory_equipment_beam_description);
        equipments.add(new Equipment(TYPE.BEAM, beamTakeOnStatsChanger, beamTakeOffStatsChanger, beamOwner, beamName, beamDescription));

        //Blaster
        final Stats blasterTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats blasterTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String blasterOwner = preferences.getString(TYPE.BLASTER, String.valueOf(Equipment.Owner.ARRMORY));
        final String blasterName = resources.getString(R.string.armory_equipment_blaster_title);
        final String blasterDescription = resources.getString(R.string.armory_equipment_blaster_description);
        equipments.add(new Equipment(TYPE.BLASTER, blasterTakeOnStatsChanger, blasterTakeOffStatsChanger, blasterOwner, blasterName, blasterDescription));

        //Electroshock
        final Stats electroshockTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats electroshockTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String electroshockOwner = preferences.getString(TYPE.ELECTROSHOCK, String.valueOf(Equipment.Owner.ARRMORY));
        final String electroshockName = resources.getString(R.string.armory_equipment_electroshock_title);
        final String electroshockDescription = resources.getString(R.string.armory_equipment_electroshock_description);
        equipments.add(new Equipment(TYPE.ELECTROSHOCK, electroshockTakeOnStatsChanger, electroshockTakeOffStatsChanger, electroshockOwner, electroshockName, electroshockDescription));

        //FlakJacket
        final Stats flakJacketTakeOnStatsChanger = Stats.builder().setDex(-2).build();
        final Stats flakJacketTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String flakJacketOwner = preferences.getString(TYPE.FLAK_JACKET, String.valueOf(Equipment.Owner.ARRMORY));
        final String flakJacketName = resources.getString(R.string.armory_equipment_flak_jacket_title);
        final String flakJacketDescription = resources.getString(R.string.armory_equipment_flak_jacket_description);
        equipments.add(new Equipment(TYPE.FLAK_JACKET, flakJacketTakeOnStatsChanger, flakJacketTakeOffStatsChanger, flakJacketOwner, flakJacketName, flakJacketDescription));

        //OpenSpaceEqpt
        final Stats openSpaceEqptTakeOnStatsChanger = Stats.builder().setDex(-3).build();
        final Stats openSpaceEqptTakeOffStatsChanger = Stats.builder().setDex(3).build();
        final @Equipment.Owner String openSpaceEqptOwner = preferences.getString(TYPE.OPEN_SPACE_EQUIPMENT, String.valueOf(Equipment.Owner.ARRMORY));
        final String openSpaceEqptName = resources.getString(R.string.armory_equipment_open_space_eqpt_title);
        final String openSpaceEqptDescription = resources.getString(R.string.armory_equipment_open_space_eqpt_description);
        equipments.add(new Equipment(TYPE.OPEN_SPACE_EQUIPMENT, openSpaceEqptTakeOnStatsChanger, openSpaceEqptTakeOffStatsChanger, openSpaceEqptOwner, openSpaceEqptName, openSpaceEqptDescription));

        //Power Shield
        final Stats powerShieldTakeOnStatsChanger = Stats.builder().setPrc(-2).build();
        final Stats powerShieldTakeOffStatsChanger = Stats.builder().setPrc(2).build();
        final @Equipment.Owner String powerShieldOwner = preferences.getString(TYPE.POWER_SHIELD, String.valueOf(Equipment.Owner.ARRMORY));
        final String powerShieldName = resources.getString(R.string.armory_equipment_power_shield_title);
        final String powerShieldDescription = resources.getString(R.string.armory_equipment_power_shield_description);
        equipments.add(new Equipment(TYPE.POWER_SHIELD, powerShieldTakeOnStatsChanger, powerShieldTakeOffStatsChanger, powerShieldOwner, powerShieldName, powerShieldDescription));

        //Targeter Shield
        final Stats targeterTakeOnStatsChanger = Stats.builder().setPrc(-2).build();
        final Stats targeterTakeOffStatsChanger = Stats.builder().setDex(2).build();
        final @Equipment.Owner String targeterOwner = preferences.getString(TYPE.TARGETTER, String.valueOf(Equipment.Owner.ARRMORY));
        final String targeterName = resources.getString(R.string.armory_equipment_targeter_title);
        final String targeterDescription = resources.getString(R.string.armory_equipment_targeter_description);
        equipments.add(new Equipment(TYPE.TARGETTER, targeterTakeOnStatsChanger, targeterTakeOffStatsChanger, targeterOwner, targeterName, targeterDescription));

        //Grenades
        final Stats grenadeTakeOnStatsChanger = Stats.builder().setDex(-1).build();
        final Stats grenadeTakeOffStatsChanger = Stats.builder().setDex(1).build();
        final @Equipment.Owner String grenade_1_Owner = preferences.getString(TYPE.GRENADE_1, String.valueOf(Equipment.Owner.ARRMORY));
        final String grenadeName = resources.getString(R.string.armory_equipment_grenade_title);
        final String grenadeDescription = resources.getString(R.string.armory_equipment_grenade_description);
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_1_Owner, grenadeName, grenadeDescription));

        final @Equipment.Owner String grenade_2_Owner = preferences.getString(TYPE.GRENADE_2, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_2_Owner, grenadeName, grenadeDescription));

        final @Equipment.Owner String grenade_3_Owner = preferences.getString(TYPE.GRENADE_3, String.valueOf(Equipment.Owner.ARRMORY));
        equipments.add(new Equipment(TYPE.GRENADE, grenadeTakeOnStatsChanger, grenadeTakeOffStatsChanger, grenade_3_Owner, grenadeName, grenadeDescription));

        return equipments;
    }
}
