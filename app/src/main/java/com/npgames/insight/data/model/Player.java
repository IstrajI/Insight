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

public class Player {
    public static final int INIT_HP = 16;
    public static final int INIT_AUR = 2;
    public static final int INIT_DEX = 10;
    public static final int INIT_PRC = 3;
    public static final int INIT_TIME = 30;
    public static final int INIT_AMN = 8;

    public enum Stats {HP, AUR, DEX, PRC, TIME, AMN}

    private int [] visittedParagraphs;

    private int paragraph;
    private int hp;
    private int aur;
    private int dex;
    private int prc;

    private int time;
    private int amn;

    private List<Equipment> equipments;
    private List<String> keys;
    private List<String> locations;

    public Player() {
        this.hp = INIT_HP;
        this.aur = INIT_AUR;
        this.dex = INIT_DEX;
        this.prc = INIT_PRC;
        this.time = INIT_TIME;
        this.amn = INIT_AMN;


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

    public void takeOnEquipment(final int position) {
        equipments.get(position).setOwnedBy(Equipment.Owner.PLAYER);
    }

    public void takeOffEquipment(final int position) {
        equipments.get(position).setOwnedBy(Equipment.Owner.ARRMORY);
    }

    public void dropEquipment(final int position) {
        equipments.get(position).setOwnedBy(Equipment.Owner.TRASH);
    }


    public boolean isOwnerOf(final String equipmentName){
        for (Equipment equipment : equipments) {
            if (equipment.getSharedPropertyName().equals(equipmentName)) {
                return equipment.getOwnedBy() == Equipment.Owner.PLAYER;
            }
        }
        return false;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(final int hp) {
        this.hp = hp >= 22? 22 : hp;
    }

    public int getAur() {
        return aur;
    }

    public void setAur(final int aur) {
        this.aur = aur;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(final int dex) {
        this.dex = dex;
    }

    public int getPrc() {
        return prc;
    }

    public void setPrc(final int prc) {
        this.prc = prc;
    }

    public int getParagraph() {
        return paragraph;
    }

    public void setParagraph(final int paragraph) {
        this.paragraph = paragraph;
    }

    public int getTime() {
        return time;
    }

    public void setTime(final int time) {
        this.time = time;
    }

    public int getAmn() {
        return amn;
    }

    public void setAmn(final int amn) {
        this.amn = amn;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void addDex(final int addingDex) {
        this.dex += addingDex;
    }

    public void addPrc(final int addingPrc) {
        this.prc += addingPrc;
    }

    public void addHp(final int addingHp) {
        this.hp += addingHp;
    }

    public void addAur(final int addingAur) {
        this.aur += addingAur;
    }

    public void addTime(final int addingTime) {
        this.time += addingTime;
    }

    public void addAmn(final int addingAmn) {
        this.amn += addingAmn;
    }

    public void takeEquipment(final Equipment equipment) {
        equipments.add(equipment);
    }

    public void putEquipment(final Equipment equipment) {
        //equipment.
    }

    public int getDexMin() {
        final int dexMin = 5;
        return dexMin;
    }
}
