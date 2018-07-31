package com.npgames.insight.data.model;

public class Stats {
    private int hp;
    private int aur;
    private int dex;
    private int prc;
    private int time;
    private int amn;

    private Stats() {}

    public int getHp() {
        return hp;
    }

    public int getAur() {
        return aur;
    }

    public int getDex() {
        return dex;
    }

    public int getPrc() {
        return prc;
    }

    public int getTime() {
        return time;
    }

    public int getAmn() {
        return amn;
    }

    public void setHp(int hp) {
        this.hp = hp >= 22 ? 22 : hp;
    }

    public void setAur(int aur) {
        this.aur = aur;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setPrc(int prc) {
        this.prc = prc;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAmn(int amn) {
        this.amn = amn;
    }

    public static Builder builder() {
        return new Stats().new Builder();
    }

    public class Builder {
        private int hp;
        private int aur;
        private int dex;
        private int prc;
        private int time;
        private int amn;

        public Builder setHp(final int hp) {
            Stats.this.hp = hp;
            return this;
        }

        public Builder setAur(final int aur) {
            Stats.this.aur = aur;
            return this;
        }

        public Builder setDex(final int dex) {
            Stats.this.dex = dex;
            return this;
        }

        public Builder setPrc(final int prc) {
            Stats.this.prc = prc;
            return this;
        }

        public Builder setTime(final int time) {
            Stats.this.time = time;
            return this;
        }

        public Builder setAmn(final int amn) {
            Stats.this.amn = amn;
            return this;
        }

        public Stats build() {
            return Stats.this;
        }
    }
}
