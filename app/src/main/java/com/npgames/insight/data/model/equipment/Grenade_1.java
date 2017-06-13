package com.npgames.insight.data.model.equipment;

public class Grenade_1 extends Grenade{
    public static final String SHARED_PROPERTY_NAME = "GRENADE_1";
    public Grenade_1(Owner ownedBy) {
        super(ownedBy);
    }

    @Override
    public String getSharedPropertyName() {
        return SHARED_PROPERTY_NAME;
    }
}
