package com.npgames.insight.ui.all.presentation.player;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.equipment.Equipment;

import java.util.List;

public interface PlayerView extends MvpView {
    void showEquipmentsOwnedBy(List<Equipment> equipments);
    void showStats(int hp, int aur, int prc, int dex, int time, int amn);
    void showPlayerOwnEquipment();
}
