package com.npgames.insight.ui.book.armory;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Equipment;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ArmoryPresenter extends MvpPresenter<ArmoryView> {



    public void loadEquipment(final Context context) {
        /*equipments = GamePreferences.getInstance(context).loadEquipment(GamePreferences.EQPTOwners.ARRMORY, true);
        getViewState().updateEquipment(equipments);*/
    }

    public void equipmentClick(final int position) {

    }

    public void takeOnEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.ARRMORY);
    }

    public void putOutEquipment(final Equipment equipment) {
        equipment.setOwnedBy(Equipment.Owner.PLAYER);
    }
}
