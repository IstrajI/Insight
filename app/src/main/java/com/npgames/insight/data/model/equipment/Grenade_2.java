package com.npgames.insight.data.model.equipment;

public class Grenade_2 extends Grenade{
    public static final String SHARED_PROPERTY_NAME = "GRENADE_2";
    public Grenade_2 (Owner ownedBy) {
        super(ownedBy);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
