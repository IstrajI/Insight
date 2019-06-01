package com.npgames.insight.ui.book.bottom_new;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Equipment;

import java.util.List;

public interface IBottomPanelView extends MvpView{
    void moveYTo(float y);
    void showPlayerEquipment(final List<Equipment> equipmentList);
    void onOpenBottomPanel();
    void onCloseBottomPanel();

    void showAvailableAllActionsState();
    void showAvailableFindActionsState();
    void showDisabledAllActionsState();
    void showDisabledArmoryActionsState();
    void disableMedBayActionsState();
}
