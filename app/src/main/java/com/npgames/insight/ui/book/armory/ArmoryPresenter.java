package com.npgames.insight.ui.book.armory;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Equipment;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ArmoryPresenter extends MvpPresenter<ArmoryView> {

    private List<Equipment> equipments;

    public void takeOnEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
    }

    public void putOutEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.PLAYER);
    }
}
