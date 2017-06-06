package com.npgames.insight.data.model;

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

    public Equipment(final EquipmentType type, final Owner ownedBy, boolean isActive) {
        this.type = type;
        this.ownedBy = ownedBy;
        this.isActive = isActive;
    }

    public Equipment(final EquipmentType type, final boolean isActive) {
        this.type = type;
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
}
