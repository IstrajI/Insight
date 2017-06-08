package com.npgames.insight.data.model;

import android.content.Context;

import com.npgames.insight.R;

public class Equipment {

    private EquipmentType type;
    private boolean isActive;
    private Owner ownedBy;
    private String name;

    public enum Owner {PLAYER, ARRMORY, TRASH}


    public enum EquipmentType {
        BLASTER,
        BEAM,
        GRENADES_LAN_1,
        GRENADES_LAN_2,
        GRENADES_LAN_3,
        ELECTROSHOCK,
        TARGETER,
        FLAK_JACKET,
        POWER_SHIELD,
        AID_KIT,
        OPEN_SPACE_EQPT
    }

    public Equipment(final EquipmentType type, final Owner ownedBy, final boolean isActive, final String name) {
        this.name = name;
        this.type = type;
        this.ownedBy = ownedBy;
        this.isActive = isActive;
    }

    public EquipmentType getType() {
        return type;
    }
    public void setType(EquipmentType type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public Owner getOwnedBy() {
        return ownedBy;
    }
    public void setOwnedBy(Owner ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByType(final Context context, final Equipment.EquipmentType type) {
        int resourceNameId = 0;

        switch(type) {
            case BLASTER:
                resourceNameId = R.string.armory_equipment_blaster_title;
                break;
            case BEAM:
                resourceNameId = R.string.armory_equipment_beam_title;
                break;
            case GRENADES_LAN_1:
                resourceNameId = R.string.armory_equipment_grenade_title;
                break;
            case GRENADES_LAN_2:
                resourceNameId = R.string.armory_equipment_grenade_title;
                break;
            case GRENADES_LAN_3:
                resourceNameId = R.string.armory_equipment_grenade_title;
                break;
            case ELECTROSHOCK:
                resourceNameId = R.string.armory_equipment_electroshock_title;
                break;
            case TARGETER:
                resourceNameId = R.string.armory_equipment_targeter_title;
                break;
            case FLAK_JACKET:
                resourceNameId = R.string.armory_equipment_flak_jacket_title;
                break;
            case POWER_SHIELD:
                resourceNameId = R.string.armory_equipment_power_shield_title;
                break;
            case AID_KIT:
                resourceNameId = R.string.armory_equipment_aid_kit_title;
                break;
            case OPEN_SPACE_EQPT:
                resourceNameId = R.string.armory_equipment_open_space_eqpt_title;
                break;
        }
        return context.getResources().getString(resourceNameId);
    }

    public static String getDescriptionByType(final Equipment.EquipmentType type) {

        switch(type) {
            case
        }
    }
}
