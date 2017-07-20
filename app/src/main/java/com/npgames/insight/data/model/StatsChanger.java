package com.npgames.insight.data.model;

public class StatsChanger {
    private final int ZERO_CHANGE = 0;
    private int hp;
    private int aur;
    private int dex;
    private int prc;
    private int time;
    private int amn;

    public StatsChanger() {
        hp = ZERO_CHANGE;
        aur = ZERO_CHANGE;
        dex = ZERO_CHANGE;
        prc = ZERO_CHANGE;
        time = ZERO_CHANGE;
        amn = ZERO_CHANGE;
    }
    public StatsChanger(final int dex, final int prc) {
        this();
        this.dex = dex;
        this.prc = prc;
    }
    public StatsChanger(final int hp, final int aur, final int dex, final int prc, final int time, final int amn) {
        this.hp = hp;
        this.aur = aur;
        this.dex = dex;
        this.prc = prc;
        this.time = time;
        this.amn = amn;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAur() {
        return aur;
    }
    public void setAur(int aur) {
        this.aur = aur;
    }

    public int getDex() {
        return dex;
    }
    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getPrc() {
        return prc;
    }
    public void setPrc(int prc) {
        this.prc = prc;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public int getAmn() {
        return amn;
    }
    public void setAmn(int amn) {
        this.amn = amn;
    }
}
