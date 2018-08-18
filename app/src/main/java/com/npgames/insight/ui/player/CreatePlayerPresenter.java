package com.npgames.insight.ui.player;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.stats.StatsRepository;
import com.npgames.insight.domain.CreatePlayerInteractor;

@InjectViewState
public class CreatePlayerPresenter extends MvpPresenter<ICreatePlayerView> {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private int pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;

    private CreatePlayerInteractor createPlayerInteractor;

    CreatePlayerPresenter(final Context context) {
        createPlayerInteractor = new CreatePlayerInteractor(context);
    }


    public void loadCreatePanelData() {
        getViewState().showPointsToDistribute(pointsToDistribute);

        getViewState().stateMaxPointsToDistribute();
    }

    public void dexMinus() {
        final int resultDex = createPlayerInteractor.dexMinus();
        getViewState().showDexPoints(resultDex);
        checkPointsAmount();
    }

    public void dexPlus() {
        final int resultDex = createPlayerInteractor.dexPlus();
        getViewState().showDexPoints(resultDex);
        checkPointsAmount();
    }

    public void prcMinus() {
        final int resultPrc = createPlayerInteractor.prcMinus();
        getViewState().showPrcPoints(resultPrc);
        checkPointsAmount();
    }

    public void prcPlus() {
        final int resultPrc = createPlayerInteractor.prcPlus();
        getViewState().showPrcPoints(resultPrc);
        checkPointsAmount();
    }

    private void checkPointsAmount() {
        final int pointsToDistribute = createPlayerInteractor.getPointsToDistribute();

        if (pointsToDistribute == 0) {
            getViewState().stateNoPointsToDistribute();
        } else if (pointsToDistribute == MAX_POINTS_TO_DISTRIBUTE){
            getViewState().stateMaxPointsToDistribute();
        } else {
            getViewState().stateSomePointsToDistribute();
        }

        if (createPlayerInteractor.isDexMin()) {
            getViewState().disableDexMinus();
        }

        if (createPlayerInteractor.isPrcMin()) {
            getViewState().disablePrcMinus();
        }
    }

    public void addPointsToPlayer() {
        createPlayerInteractor.addPointsToPlayer();
    }

    public void resetPoints() {
        pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;

        loadCreatePanelData();
    }


}
