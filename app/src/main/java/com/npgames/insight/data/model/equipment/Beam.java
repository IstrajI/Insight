package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class Beam extends Equipment{
    public static final String SHARED_PROPERTY_NAME = "BEAM";
    private final int wereDebuff = -2;
    private final int NAME_RES_ID = R.string.armory_equipment_beam_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_beam_description;
    private final int dexDebuff = -1;

    public Beam(final Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
        setDexDebuff(dexDebuff);
    }

    @Override
    public boolean wearChangeStats(final Player player) {
        if (!canWearEquipment(player)) return false;
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
