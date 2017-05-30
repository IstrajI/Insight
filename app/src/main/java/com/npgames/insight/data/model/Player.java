package com.npgames.insight.data.model;

import java.util.List;

public class Player {
    public static final int INIT_HP = 16;
    public static final int INIT_AUR = 2;
    public static final int INIT_DEX = 10;
    public static final int INIT_PRC = 3;
    public static final int INIT_TIME = 30;
    public static final int INIT_AMNS = 8;

    public enum Stats {HP, AUR, DEX, PRC, TIME, AMNS}

    private int paragraph;

    private int hp;
    private int aur;
    private int dex;
    private int prc;

    private int time;
    private int amns;

    private List<String> eqpt;
    private List<String> keys;
    private List<String> locations;

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

    public int getAmns() {
        return amns;
    }

    public void setAmns(final int amns) {
        this.amns = amns;
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

    public void addAmns(final int addingAmns) {
        this.amns += addingAmns;
    }
}
