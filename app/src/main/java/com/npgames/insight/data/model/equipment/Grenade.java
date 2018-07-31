/*
package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;

public abstract class Grenade extends Equipment {
    private final int NAME_RES_ID = R.string.armory_equipment_grenade_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_grenade_description;


    private Stats takeOnStatsChanger = Stats.builder()
            .setDex(-1)
            .setPrc(0)
            .build();

    private Stats takeOffStatsChanger = Stats.builder()
            .setDex(1)
            .setPrc(0)
            .build();

    public Grenade(final Equipment.Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
        setTakeOnStatsChanger(takeOnStatsChanger);
        setTakeOffStatsChanger(takeOffStatsChanger);
    }

    @Override
    public abstract String getSharedPropertyName();
}
*/
