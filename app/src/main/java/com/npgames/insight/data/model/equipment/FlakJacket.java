package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class FlakJacket extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "FLAK_JACKET";
    private final int NAME_RES_ID = R.string.armory_equipment_flak_jacket_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_flak_jacket_description;
    private final int wereDebuff = -2;

    public FlakJacket(final Equipment.Owner ownedBy) {
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
