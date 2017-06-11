package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public class Targeter extends Equipment {
    public static final String SHARED_PROPERTY_NAME = "TARGETER";
    private final int NAME_RES_ID = R.string.armory_equipment_targeter_title;
    private final int DESCRIPTION_RES_ID = R.string.armory_equipment_targeter_description;
    private final int wereDebuff = -2;
    private final int wereBonus = 3;

    public Targeter(Equipment.Owner ownedBy) {
        super(ownedBy);
        setNameResource(NAME_RES_ID);
        setDescriptionResource(DESCRIPTION_RES_ID);
    }

    @Override
    public void wearChangeStats(Player player) {
        player.addPrc(wereBonus);
        player.addDex(wereDebuff);
    }

    @Override
    public void unwearChangeStats(Player player) {
        player.addPrc(-wereBonus);
        player.addDex(-wereDebuff);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
