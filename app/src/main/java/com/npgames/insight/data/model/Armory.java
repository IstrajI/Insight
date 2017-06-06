package com.npgames.insight.data.model;

import java.util.List;

public class Armory {

    public Armory() {

    }

    public List<Equipment> equipments;

    public Equipment getEquipment(Equipment.EquipmentType name) {
        for (Equipment equipment: equipments) {
            if (equipment.getType() == name) return equipment;
        }
        return null;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
