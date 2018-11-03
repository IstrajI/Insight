package com.npgames.insight.data.model;

import com.npgames.insight.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Equipment {
    private final @TYPE String type;
    private final Stats takeOnStatsChanger;
    private final Stats takeOffStatsChanger;
    private @Owner String ownedBy;
    private String name;
    private String description;
    private boolean isEnabled;
    private boolean canWear;

    public Equipment() {
        this(TYPE.NONE, Stats.builder().build(), Stats.builder().build(), Owner.NONE, "", "");
    }

    public Equipment(final @TYPE String type, final Stats takeOnStatsChanger, final Stats takeOffStatsChanger, final @Owner String ownedBy, final String name, final String description) {
        this.type = type;
        this.takeOnStatsChanger = takeOnStatsChanger;
        this.takeOffStatsChanger = takeOffStatsChanger;
        this.ownedBy = ownedBy;
        this.isEnabled = true;
        this.name = name;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(final @Equipment.Owner String owner)  {
        this.ownedBy = owner;
    }

    public boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Stats getTakeOnStatsChanger() {
        return takeOnStatsChanger;
    }

    public Stats getTakeOffStatsChanger() {
        return takeOffStatsChanger;
    }

    public void setCanWear(final boolean canWear) {
        this.canWear = canWear;
    }

    public boolean getCanWear() {
        return canWear;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Owner {
        String NONE = "NONE";
        String PLAYER = "PLAYER";
        String ARRMORY = "ARRMORY";
        String TRASH = "TRASH";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
        String NONE = "NONE";
        String AID_KIT = "AID_KIT";
        String BEAM = "BEAM";
        String BLASTER = "BLASTER";
        String ELECTROSHOCK = "ELECTROSHOCK";
        String FLAK_JACKET = "FLACK_JACKET";
        String GRENADE = "GRENADE";
        String GRENADE_1 = "GRENADE_1";
        String GRENADE_2 = "GRENADE_2";
        String GRENADE_3 = "GRENADE_3";
        String OPEN_SPACE_EQUIPMENT = "OPEN_SPACE_EQUIPMENT";
        String POWER_SHIELD = "POWER_SHIELD";
        String TARGETTER = "TARGETTER";
    }

    public int pish() {
        int image;

        switch (type) {
            case Equipment.TYPE.BLASTER:
                return getBlasterDrawable(ownedBy);

            case Equipment.TYPE.BEAM:
                return getBeamDrawable(ownedBy);

            case Equipment.TYPE.ELECTROSHOCK:
                return getElectroshockDrawable(ownedBy);

            case Equipment.TYPE.AID_KIT:
                return getAidKitDrawable(ownedBy);

            case Equipment.TYPE.OPEN_SPACE_EQUIPMENT:
                return getOpenSpaceEquipmentDrawable(ownedBy);

            case Equipment.TYPE.GRENADE:
                return getGrenadeDrawable(ownedBy);

            case Equipment.TYPE.FLAK_JACKET:
                return getFlakJacketDrawable(ownedBy);

            case Equipment.TYPE.POWER_SHIELD:
                return getPowerShieldDrawable(ownedBy);

            case Equipment.TYPE.TARGETTER:
                return getTargetterDrawable(ownedBy);

            default:
                return getTargetterDrawable(ownedBy);
        }
    }

    public int getDrawable() {
        int image = R.drawable.blaster;

        switch (type) {
            case Equipment.TYPE.BLASTER:

                image = R.drawable.blaster3;
                break;

            case Equipment.TYPE.BEAM:
                image = R.drawable.laser_2;
                break;

            case Equipment.TYPE.ELECTROSHOCK:
                image = R.drawable.shoker_2;
                break;

            case Equipment.TYPE.AID_KIT:
                image = R.drawable.medkit_3;
                break;

            case Equipment.TYPE.OPEN_SPACE_EQUIPMENT:
                image = R.drawable.helmet_11_xxx;
                break;

            case Equipment.TYPE.GRENADE:
                image = R.drawable.grenade_1;
                break;

            case Equipment.TYPE.FLAK_JACKET:
                image = R.drawable.jaket;
                break;

            case Equipment.TYPE.POWER_SHIELD:
                image = R.drawable.powershiled_8;
                break;

            case Equipment.TYPE.TARGETTER:
                image = R.drawable.powershiled_8;
                break;
        }

        return image;
    }

    private int getBlasterDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.blaster3;
            case Owner.PLAYER:
                return R.drawable.blaster3_taked_on;
            default:
                return R.drawable.blaster3;
        }
    }

    private int getBeamDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.laser_2;
            case Owner.PLAYER:
                return R.drawable.laser_taked_on_test;
            default:
                return R.drawable.laser_2;
        }
    }

    private int getElectroshockDrawable(final String ownedBy) {
        switch (ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.shoker_2;
            case Owner.PLAYER:
                return R.drawable.shoker_2_take_on;
            default:
                return R.drawable.shoker_2;
        }
    }

    private int getAidKitDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.medkit_3;
            case Owner.PLAYER:
                return R.drawable.medkit_3_take_on;
            default:
                return R.drawable.medkit_3;
        }
    }

    private int getOpenSpaceEquipmentDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.helmet_11_xxx;
            case Owner.PLAYER:
                return R.drawable.helmet_11_xxx_take_on;
            default:
                return R.drawable.helmet_11_xxx;
        }
    }

    private int getGrenadeDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.grenade_1;
            case Owner.PLAYER:
                return R.drawable.grenade_1_taked_on;
            default:
                return R.drawable.grenade_1;
        }
    }

    private int getFlakJacketDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.jaket;
            case Owner.PLAYER:
                return R.drawable.jaket_taked_on;
            default:
                return R.drawable.jaket;
        }
    }

    private int getPowerShieldDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.powershiled_7;
            case Owner.PLAYER:
                return R.drawable.powershiled_7_taked_on;
            default:
                return R.drawable.powershiled_7;
        }
    }

    private int getTargetterDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return R.drawable.powershiled_7;
            case Owner.PLAYER:
                return R.drawable.powershiled_7_taked_on;
            default:
                return R.drawable.powershiled_7;
        }
    }
}
