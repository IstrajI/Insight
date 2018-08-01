package com.npgames.insight.data.model.equipment;

import com.npgames.insight.data.model.Stats;

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

    public Equipment() {
        this(TYPE.NONE, Stats.builder().build(), Stats.builder().build(), Owner.NONE);
    }

    public Equipment(final @TYPE String type, final Stats takeOnStatsChanger, final Stats takeOffStatsChanger, final @Owner String ownedBy) {
        this.type = type;
        this.takeOnStatsChanger = takeOnStatsChanger;
        this.takeOffStatsChanger = takeOffStatsChanger;
        this.ownedBy = ownedBy;
        this.isEnabled = true;
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
        String FlAK_JACKET = "FLACK_JACKET";
        String GRENADE = "GRENADE";
        String GRENADE_1 = "GRENADE_1";
        String GRENADE_2 = "GRENADE_2";
        String GRENADE_3 = "GRENADE_3";
        String OPEN_SPACE_EQUIPMENT = "OPEN_SPACE_EQUIPMENT";
        String POWER_SHIELD = "POWER_SHIELD";
        String TARGETTER = "TARGETTER";
    }
}
