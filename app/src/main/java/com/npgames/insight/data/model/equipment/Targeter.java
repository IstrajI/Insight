/*
package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.StatsChanger;

public class Targeter extends Equipment {
    public static final String SHARED_PROPERTY_NAME = "TARGETER";
    private final int NAME_RES_ID = R.string.armory_equipment_targeter_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_targeter_description;

    private Stats takeOnStatsChanger = Stats.builder()
            .setDex(-1)
            .setPrc(3)
            .build();

    private Stats takeOffStatsChanger = Stats.builder()
            .setDex(1)
            .setPrc(-3)
            .build();

    public Targeter(Equipment.Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
        setTakeOnStatsChanger(takeOnStatsChanger);
        setTakeOffStatsChanger(takeOffStatsChanger);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
*/
