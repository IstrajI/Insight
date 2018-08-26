package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.domain.EquipmentInteractor;

@InjectViewState
public class BottomPanelPresenter extends MvpPresenter<IBottomPanelView>{
    private EquipmentInteractor equipmentInteractor;

    private boolean isBottomPanelOpen;
    private float openYPosition;
    private float closeYPosition;

    public BottomPanelPresenter(final Context context) {
        equipmentInteractor = new EquipmentInteractor(context);
    }

    public void loadPlayerEquipment() {
        getViewState().showPlayerEquipment(equipmentInteractor.getEquipmentsOwnedBy(Equipment.Owner.PLAYER));
    }

    public void openCloseBottomPanel() {
        getViewState().moveYTo(isBottomPanelOpen ? closeYPosition : openYPosition);
        isBottomPanelOpen = !isBottomPanelOpen;
    }

    public void initOpenClosePositions(final float openY, final float closeY) {
        openYPosition = openY;
        closeYPosition = closeY;
         isBottomPanelOpen = true;
    }
}
