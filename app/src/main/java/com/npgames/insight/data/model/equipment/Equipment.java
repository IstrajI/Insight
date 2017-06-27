package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public abstract class Equipment {
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";

    private int NAME_RES_ID = R.string.armory_equipment_aid_kit_title;
    private int DESCRIPTION_RES_ID = R.string.armory_equipment_aid_kit_description;

    public enum Owner {PLAYER, ARRMORY, TRASH}

    private Owner ownedBy;

    public Equipment(final Owner ownedBy) {
        this.ownedBy = ownedBy;
    }

    public Owner getOwnedBy() {
        return ownedBy;
    }
    public void setOwnedBy(Owner ownedBy) {
        this.ownedBy = ownedBy;
    }

    public abstract boolean wearChangeStats(final Player player);
    public abstract void unwearChangeStats(final Player player);

    public int getNameResource() {
        return NAME_RES_ID;
    }
    public void setNameResource(final int nameResource) {
        this.NAME_RES_ID = nameResource;
    }

    public int getDescriptionResource() {
        return DESCRIPTION_RES_ID;
    }
    public void setDescriptionResource(final int descriptionResource) {
        this.DESCRIPTION_RES_ID = descriptionResource;
    }

    public abstract String getSharedPropertyName();

    protected boolean canWearEquipment(final int playerDex, final int dexDebuff) {
        return (playerDex + dexDebuff > playerDex);
    }

    public abstract boolean checkCanWearEquipment(final int playerDex, final int dexDebuff);

}
