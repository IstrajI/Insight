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

    @Retention(RetentionPolicy.SOURCE)
    public @interface DRAWABLE_COLOR_MODEL {
        String USE_BLUE_COLOR = "USE_BLUE_COLOR";
        String USE_GREEN_COLOR = "USE_GREEN_COLOR";
        String DEFAULT = "DEFAULT";
    }

    public int getDrawable(final @DRAWABLE_COLOR_MODEL String drawableColorModel) {
        final @Owner String ownedBy;
        switch(drawableColorModel) {
            case DRAWABLE_COLOR_MODEL.USE_BLUE_COLOR:
                ownedBy = Owner.ARRMORY;
                break;

            case DRAWABLE_COLOR_MODEL.USE_GREEN_COLOR:
                ownedBy = Owner.ARRMORY;
                break;

            case DRAWABLE_COLOR_MODEL.DEFAULT:
            default:
                ownedBy = this.ownedBy;
        }

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

    private int getBlasterDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.blaster3 : R.drawable.blaster3_disabled;
            case Owner.PLAYER:
                return R.drawable.blaster3_taked_on;
            default:
                return R.drawable.blaster3;
        }
    }

    private int getBeamDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.laser_2 : R.drawable.laser_2_disabled;
            case Owner.PLAYER:
                return R.drawable.laser_taked_on_test;
            default:
                return R.drawable.blaster3_disabled;
        }
    }

    private int getElectroshockDrawable(final String ownedBy) {
        switch (ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.shoker_2: R.drawable.shoker_2_disabled;
            case Owner.PLAYER:
                return R.drawable.shoker_2_take_on;
            default:
                return R.drawable.blaster3_disabled2;
        }
    }

    private int getAidKitDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.medkit_3 : R.drawable.medkit_3_disabled;
            case Owner.PLAYER:
                return R.drawable.medkit_3_take_on;
            default:
                return R.drawable.medkit_3;
        }
    }

    private int getOpenSpaceEquipmentDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.helmet_11_xxx : R.drawable.helmet_disabled;
            case Owner.PLAYER:
                return R.drawable.helmet_11_xxx_take_on;
            default:
                return R.drawable.helmet_11_xxx;
        }
    }

    private int getGrenadeDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.grenade_1 : R.drawable.grenade_1_disabled;
            case Owner.PLAYER:
                return R.drawable.grenade_1_taked_on;
            default:
                return R.drawable.grenade_1;
        }
    }

    private int getFlakJacketDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.jaket: R.drawable.jaket_disabled;
            case Owner.PLAYER:
                return R.drawable.jaket_taked_on;
            default:
                return R.drawable.jaket;
        }
    }

    private int getPowerShieldDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.powershiled_7: R.drawable.powershiled_disabled;
            case Owner.PLAYER:
                return R.drawable.powershiled_7_taked_on;
            default:
                return R.drawable.powershiled_7;
        }
    }

    private int getTargetterDrawable(final String ownedBy) {
        switch(ownedBy) {
            case Owner.ARRMORY:
                return canWear ? R.drawable.powershiled_7 : R.drawable.powershiled_disabled;
            case Owner.PLAYER:
                return R.drawable.powershiled_7_taked_on;
            default:
                return R.drawable.powershiled_7;
        }
    }
}
