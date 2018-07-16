package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.StatsChanger;

public class PowerShield extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "POWER_SHIELD";
    private final int NAME_RES_ID = R.string.armory_equipment_power_shield_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_power_shield_description;

    private Stats takeOnStatsChanger = Stats.builder()
            .setDex(0)
            .setPrc(-2)
            .build();

    private Stats takeOffStatsChanger = Stats.builder()
            .setDex(0)
            .setPrc(-2)
            .build();

    public PowerShield(Owner ownedBy) {
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
