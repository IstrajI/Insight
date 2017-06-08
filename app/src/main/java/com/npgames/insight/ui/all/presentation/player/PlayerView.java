package com.npgames.insight.ui.all.presentation.player;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Equipment;

import java.util.List;

public interface PlayerView extends MvpView {
    void showEquipmentsOwnedBy(final List<Equipment> equipments);
}
