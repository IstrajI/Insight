package com.npgames.insight.ui.player;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.domain.CreatePlayerInteractor;

@InjectViewState
public class CreatePlayerPresenter extends MvpPresenter<ICreatePlayerView> {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;

    private CreatePlayerInteractor createPlayerInteractor;
    private ParagraphRepository paragraphRepository;

    CreatePlayerPresenter(final Context context) {
        createPlayerInteractor = new CreatePlayerInteractor(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
    }


    public void loadCreatePanelData() {
        determinateViewState();
        getViewState().showDexPoints(createPlayerInteractor.getDex());
        getViewState().showPrcPoints(createPlayerInteractor.getPrc());
    }

    public void dexMinus() {
        final int resultDex = createPlayerInteractor.dexMinus();
        getViewState().showDexPoints(resultDex);
        minusCheck();
    }

    public void dexPlus() {
        final int resultDex = createPlayerInteractor.dexPlus();
        getViewState().showDexPoints(resultDex);
        plusCheck();
    }

    public void prcMinus() {
        final int resultPrc = createPlayerInteractor.prcMinus();
        getViewState().showPrcPoints(resultPrc);
        minusCheck();
    }

    public void prcPlus() {
        final int resultPrc = createPlayerInteractor.prcPlus();
        getViewState().showPrcPoints(resultPrc);
        plusCheck();
    }

    public void determinateViewState() {
        if (createPlayerInteractor.getPointsToDistribute() == 0) {
            getViewState().stateNoPointsToDistribute();
        } else if (createPlayerInteractor.getPointsToDistribute() == MAX_POINTS_TO_DISTRIBUTE) {
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


    boolean maxWasReached = false;

    public void plusCheck() {
        if (createPlayerInteractor.getPointsToDistribute() == 0) {
            getViewState().onPointsHaveBeenDistributeListener();
            maxWasReached = true;
        }

        determinateViewState();
    }

    public void minusCheck() {
        if (createPlayerInteractor.getPointsToDistribute() != MAX_POINTS_TO_DISTRIBUTE
                && maxWasReached) {
            maxWasReached = false;
            getViewState().notMaxPoints();
        }

        determinateViewState();
    }


    public void addPointsToPlayer() {
        createPlayerInteractor.addPointsToPlayer();
    }

    public void resetPoints() {
        //pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;

        loadCreatePanelData();
    }

    public void savePoints() {
        createPlayerInteractor.saveDistributedPoints();
    }
}
