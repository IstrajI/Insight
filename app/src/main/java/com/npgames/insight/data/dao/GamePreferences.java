package com.npgames.insight.data.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;

import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;
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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.npgames.insight.data.dao.GamePreferences.Achievements.NATURALIST;

public class GamePreferences {
    private static GamePreferences gamePreferences;
    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_NAME = "gamePreferences";
    private final List<String> achievements = new ArrayList<>();


    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";

    private final String KEYWORDS = "KEYWORDS";
    private final String OWNER_KEY = "_OWNER : ";

    public static GamePreferences getInstance(final Context appContext) {
        if (gamePreferences == null) {
            gamePreferences = new GamePreferences();
            sharedPreferences = appContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return gamePreferences;
    }

    public int loadCurrentParagraph() {
        return sharedPreferences.getInt(CURRENT_PARAGRAPH, Paragraph.INIT_PARAGRAPH);
    }

    public void saveCurrentParagraph(final int currentParagraph) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_PARAGRAPH, currentParagraph);
        editor.apply();
    }

    //Equpment

    public List<Equipment> loadEquipment() {
        final List<Equipment> equipments = new ArrayList<>();
        equipments.add(new AidKit(loadEquipmentOwnedBy(AidKit.SHARED_PROPERTY_NAME)));
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

    private Equipment.Owner loadEquipmentOwnedBy(final String sharedPropertyName) {
        final String DEFAULT_OWNER = String.valueOf(Equipment.Owner.ARRMORY);
        final String preferenceProperty = sharedPropertyName + OWNER_KEY;
        final String ownedByString = sharedPreferences.getString(preferenceProperty, DEFAULT_OWNER);
        return Equipment.Owner.valueOf(ownedByString);
    }

    public void saveEqupment(final List<Equipment> equipments) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        for (final Equipment equipment: equipments) {
            final String ownedByString = String.valueOf(equipment.getOwnedBy());
            editor.putString(equipment.getSharedPropertyName() + OWNER_KEY, ownedByString);
        }
        editor.apply();
    }



    public void addAchievement(final @Achievements String achievement) {
        achievements.add(achievement);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({NATURALIST})
    public @interface Achievements {
        String NATURALIST = "NATURALIST";
    }
}
