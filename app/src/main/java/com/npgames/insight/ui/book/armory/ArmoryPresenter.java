package com.npgames.insight.ui.book.armory;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.stats.StatsRepository;
import com.npgames.insight.domain.EquipmentInteractor;

@InjectViewState
public class ArmoryPresenter extends MvpPresenter<ArmoryView>{
    private EquipmentRepository equipmentRepository;
    private StatsRepository statsRepository;
    private EquipmentInteractor equipmentInteractor;

    ArmoryPresenter(final Context context) {
        equipmentRepository = EquipmentRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);

        equipmentInteractor = new EquipmentInteractor(context);
    }

    public void loadEquipment() {
        getViewState().showEquipment(equipmentRepository.getEquipments());
    }

    void loadStatsPlayerStats() {
        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void takeOnEquipment(final Equipment equipmentOn) {
        equipmentInteractor.takeOnEquipment(equipmentOn);

        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }

    void takeOffEquipment(final Equipment equipmentOff) {
        equipmentInteractor.takeOffEquipment(equipmentOff);

        final Stats stats = statsRepository.getStats();
        getViewState().showStats(stats);
    }
}
