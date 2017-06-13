package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class Electroshock extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "ELECTROSHOCK";
    private final int wereDebuff = -1;
    private final int NAME_RES_ID = R.string.armory_equipment_electroshock_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_electroshock_description;

    public Electroshock(final Equipment.Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
    }

    @Override
    public boolean wearChangeStats(final Player player) {
        if (!canWearEquipment(player, wereDebuff)) return false;
        player.addDex(wereDebuff);
        return true;
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
