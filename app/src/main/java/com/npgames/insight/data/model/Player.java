package com.npgames.insight.data.model;

import com.npgames.insight.data.db.KeyWordsPreferences;
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
import java.util.Set;

public class Player {
    public static final int INIT_HP = 16;
    public static final int INIT_AUR = 2;
    public static final int INIT_DEX = 10;
    public static final int INIT_PRC = 3;
    public static final int INIT_TIME = 30;
    public static final int INIT_AMN = 8;

    private int[] visittedParagraphs;
    private int paragraph;

    private final Stats stats;
    private final List<Equipment> equipments;
    private final Set<String> keywords;
    //private final List<String> locations;

/*    public Player() {
        stats = Stats.builder()
                .setHp(INIT_HP)
                .setAur(INIT_AUR)
                .setTime(INIT_TIME)
                .setDex(INIT_DEX)
                .setPrc(INIT_PRC)
                .setAmn(INIT_AMN)
                .build();
    }*/

    public Player(final Stats playerStats, final Set<String> playerKeywords, final List<Equipment> playerEquipments) {
        stats = playerStats;
        keywords = playerKeywords;
        equipments = playerEquipments;
    }




/*    public void takeOnEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.PLAYER);
        changeStats(equipment.getTakeOnStatsChanger());
    }*/

/*    public void takeOffEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
        changeStats(equipment.getTakeOffStatsChanger());
    }*/

/*    public boolean canWearEquipment(final Equipment equipment) {
        final StatsChanger statsChanger = equipment.getTakeOnStatsChanger();

        Log.d("result ", ""+(stats.getDex() + statsChanger.getDex() >= getDexMin()));
        return (stats.getDex() + statsChanger.getDex() >= getDexMin());
    }*/

/*
    public void dropEquipment(final int position) {
        equipments.get(position).setOwnedBy(Equipment.Owner.TRASH);
    }


    public boolean isOwnerOf(final String equipmentName) {
        for (Equipment equipment : equipments) {
            if (equipment.getSharedPropertyName().equals(equipmentName)) {
                return equipment.getOwnedBy() == Equipment.Owner.PLAYER;
            }
        }
        return false;
    }

    public Stats getStats() {
        return stats;
    }

    public void addKeyword(@KeyWord.KeyWords String keyword) {
        keywords.add(keyword);
    }
*/



/*    //----------------------------------------------------------------------------------------------
    // -------------------------------  KeyWords ---------------------------------------------------
    public void addKeyWord(final @KeyWord.KeyWords String keyword) {
        keywords.add(keyword);
    }

    public Set<String> getKeyWords() {
        return keywords;
    }

    public void setKeyWords(final Set<String> keywords) {
        this.keywords = keywords;
    }*/
}
