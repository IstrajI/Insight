package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public abstract class Grenade extends Equipment{
    private final int NAME_RES_ID = R.string.armory_equipment_grenade_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_grenade_description;
    private final int wereDebuff = -1;

    public Grenade(final Equipment.Owner ownedBy) {
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
    public abstract String getSharedPropertyName();
}
