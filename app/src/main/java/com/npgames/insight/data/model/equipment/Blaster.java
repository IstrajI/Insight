package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class Blaster extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "BLASTER";
    private final int wereDebuff = -1;
    private final int NAME_RES_ID = R.string.armory_equipment_blaster_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_blaster_description;

    public Blaster(final Equipment.Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
    }

    @Override
    public void wearChangeStats(final Player player) {
        player.addDex(wereDebuff);
    }

    @Override
    public void unwearChangeStats(final Player player) {
        player.addDex(-wereDebuff);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
