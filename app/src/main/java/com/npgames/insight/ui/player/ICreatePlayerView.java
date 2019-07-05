package com.npgames.insight.ui.player;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ICreatePlayerView extends MvpView{
    void showDexPoints(final int dexPoints);
    void showPrcPoints(final int prcPoints);
    void showPointsToDistribute(final int pointsToDistribute);

    void stateNoPointsToDistribute();
    void stateMaxPointsToDistribute();
    void stateSomePointsToDistribute();


    void disableDexMinus();
    void disablePrcMinus();

    @StateStrategyType(SkipStrategy.class)
    void onPointsHaveBeenDistributeListener();
    @StateStrategyType(SkipStrategy.class)
    void notMaxPoints();
}
