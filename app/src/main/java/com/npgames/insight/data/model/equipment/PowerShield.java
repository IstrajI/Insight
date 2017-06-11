package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class PowerShield extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "POWER_SHIELD";
    private final int NAME_RES_ID = R.string.armory_equipment_power_shield_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_power_shield_description;
    private final int wereDebuff = -2;

    public PowerShield(Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
    }

    @Override
    public void wearChangeStats(Player player) {
        player.addPrc(wereDebuff);
    }

    @Override
    public void unwearChangeStats(Player player) {
        player.addPrc(-wereDebuff);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
