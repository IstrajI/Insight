package com.npgames.insight.data.model.equipment;

import com.npgames.insight.R;
import com.npgames.insight.data.model.Player;

public abstract class Equipment {
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";

    private int dexDebuff = 0;

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

    public int getDexDebuff() {
        return this.dexDebuff;
    }
    public void setDexDebuff(final int dexDebuff) {
        this.dexDebuff = dexDebuff;
    }

    public abstract String getSharedPropertyName();

    public boolean canWearEquipment(final Player player) {
        return (player.getDex() + this.dexDebuff >= player.getDexMin());
    }

    public boolean isOwner(final Owner owner) {
        return ownedBy == owner;
    }
}
