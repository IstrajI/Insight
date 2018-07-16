package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;

public class FlakJacket extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "FLAK_JACKET";
    private final int NAME_RES_ID = R.string.armory_equipment_flak_jacket_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_flak_jacket_description;


    private Stats takeOnStatsChanger = Stats.builder()
            .setDex(-2)
            .setPrc(0)
            .build();

    private Stats takeOffStatsChanger = Stats.builder()
            .setDex(2)
            .setPrc(0)
            .build();

    public FlakJacket(final Equipment.Owner ownedBy) {
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
