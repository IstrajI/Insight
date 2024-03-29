package com.npgames.insight.ui.book.armory;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.Equipment;

import java.util.List;

public interface ArmoryView extends MvpView{
    void showEquipment(List<Equipment> equipments);
    void showStats(Stats stats);
}
