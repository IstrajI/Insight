package com.npgames.insight.data.model;

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
    private Stats stats;

    private List<Equipment> equipments;
    //TODO: add saving
    private Set<String> keywords;
    private List<String> locations;

    public Player() {
        stats = Stats.builder()
                .setHp(INIT_HP)
                .setAur(INIT_AUR)
                .setTime(INIT_TIME)
                .setDex(INIT_DEX)
                .setPrc(INIT_PRC)
                .setAmn(INIT_AMN)
                .build();

        equipments = new ArrayList<>();
        equipments.add(new AidKit(Equipment.Owner.ARRMORY));
        equipments.add(new Beam(Equipment.Owner.ARRMORY));
        equipments.add(new Blaster(Equipment.Owner.ARRMORY));
        equipments.add(new Electroshock(Equipment.Owner.ARRMORY));
        equipments.add(new FlakJacket(Equipment.Owner.ARRMORY));
        equipments.add(new Grenade_1(Equipment.Owner.ARRMORY));
        equipments.add(new Grenade_2(Equipment.Owner.ARRMORY));
        equipments.add(new Grenade_3(Equipment.Owner.ARRMORY));
        equipments.add(new OpenSpaceEqpt(Equipment.Owner.ARRMORY));
        equipments.add(new PowerShield(Equipment.Owner.ARRMORY));
        equipments.add(new Targeter(Equipment.Owner.ARRMORY));
    }

    public void changeStats(final Stats stats) {
        this.stats.setHp(this.stats.getHp() + stats.getHp());
        this.stats.setDex(this.stats.getDex() + stats.getDex());
        this.stats.setAmn(this.stats.getAmn() + stats.getAmn());
        this.stats.setAur(this.stats.getAur() + stats.getAur());
        this.stats.setPrc(this.stats.getPrc() + stats.getPrc());
        this.stats.setTime(this.stats.getTime() + stats.getTime());
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


    public int getParagraph() {
        return paragraph;
    }
    public void setParagraph(final int paragraph) {
        this.paragraph = paragraph;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public int getDexMin() {
        final int dexMin = 5;
        return dexMin;
    }

    //----------------------------------------------------------------------------------------------
    // -------------------------------  KeyWords ---------------------------------------------------
    public void addKeyWord(final @KeyWord.KeyWords String keyword) {
        keywords.add(keyword);
    }

    public Set<String> getKeyWords() {
        return keywords;
    }

    public void setKeyWords(final Set<String> keywords) {
        this.keywords = keywords;
    }
}
