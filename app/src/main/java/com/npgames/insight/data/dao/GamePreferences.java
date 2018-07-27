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
    private final Set<String> achievements = new HashSet<>();
    private final String ACHIEVEMENTS = "ACHIEVEMENTS"

    private final String CURRENT_PARAGRAPH = "CURRENT_PARAGRAPH";

    private final String KEYWORDS = "KEYWORDS";

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



    public void saveAchievements(final Set<String> achievements) {
        editor.putStringSet(ACHIEVEMENTS, achievements);
        editor.apply();
    }

    public List<String> loadAchievements() {
        return sharedPreferences.getStringSet(ACHIEVEMENTS, Collections.emptySet<String>);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({NATURALIST})
    public @interface Achievements {
        String NATURALIST = "NATURALIST";
    }
}
