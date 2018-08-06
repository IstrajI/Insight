package com.npgames.insight.ui.book.bottom_new;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.domain.EquipmentInteractor;

@InjectViewState
public class BottomPanelPresenter extends MvpPresenter<IBottomPanelView>{
    private EquipmentInteractor equipmentInteractor;

    private boolean isBottomPanelOpen = true;
    private float openYPosition;
    private float closeYPosition;

    public BottomPanelPresenter(final Context context) {
        equipmentInteractor = new EquipmentInteractor(context);
    }

    public void loadPlayerEquipment() {
        getViewState().showPlayerEquipment(equipmentInteractor.getEquipmentsOwnedBy(Equipment.Owner.PLAYER));
    }

    public void changeYPosition(final float currentY, final float height, final float topUiHeight) {
        if (openYPosition == 0 && closeYPosition == 0) {
            initStatesYPositions(currentY, height, topUiHeight);
        }

        getViewState().moveYTo(isBottomPanelOpen ? closeYPosition : openYPosition);
        isBottomPanelOpen = !isBottomPanelOpen;
    }

    private void initStatesYPositions(final float currentY, final float height, final float topUiHeight) {
        openYPosition = currentY;
        closeYPosition = currentY + height - topUiHeight / 2;
    }

    public void openCloseBottomPanel() {
    }

    public void initOpenClosePositions() {
    }
}
