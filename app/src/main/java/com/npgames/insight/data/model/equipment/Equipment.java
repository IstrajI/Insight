package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Equipment {
    private final @TYPE String type;
    private final Stats takeOnStatsChanger;
    private final Stats takeOffStatsChanger;
    private final @Owner String ownedBy;
    private String name;
    private String description;

    public Equipment(final @TYPE String type, final Stats takeOnStatsChanger, final Stats takeOffStatsChanger, final @Owner String ownedBy) {
        this.type = type;
        this.takeOnStatsChanger = takeOnStatsChanger;
        this.takeOffStatsChanger = takeOffStatsChanger;
        this.ownedBy = ownedBy;
    }

    public String getType() {
        return type;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    /*

    public boolean isOwner(final Owner owner) {
        return ownedBy == owner;
    }*/


    @Retention(RetentionPolicy.SOURCE)
    public @interface Owner {
        String PLAYER = "PLAYER";
        String ARRMORY = "ARRMORY";
        String TRASH = "TRASH";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
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
