package com.npgames.insight.ui.book.equipmentDialog;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.domain.EquipmentInteractor;

@InjectViewState
public class EquipmentDialogPresenter extends MvpPresenter<EquipmentView> {

    EquipmentInteractor equipmentInteractor;

    public EquipmentDialogPresenter(final Context context) {
        equipmentInteractor = new EquipmentInteractor(context);
    }

    public void dropEquipment(final String equipmentType) {
        equipmentInteractor.dropEquipment(equipmentType);
    }
}
