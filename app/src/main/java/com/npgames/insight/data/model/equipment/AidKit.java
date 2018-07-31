/*
package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Stats;

public class AidKit extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "AID_KIT";
    public final int NAME_RES_ID = R.string.armory_equipment_aid_kit_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_aid_kit_description;

    private Stats takeOnStatsChanger = Stats.builder()
            .setDex(-1)
            .setPrc(0)
            .build();

    private Stats takeOffStatsChanger = Stats.builder()
            .setDex(1)
            .setPrc(0)
            .build();

    public AidKit(final Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
        setTakeOnStatsChanger(takeOnStatsChanger);
        setTakeOffStatsChanger(takeOffStatsChanger);
    }

    @Override
    public static String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
*/
