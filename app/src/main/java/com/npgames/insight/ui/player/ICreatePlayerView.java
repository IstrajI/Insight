package com.npgames.insight.ui.player;

import com.arellomobile.mvp.MvpView;

public interface ICreatePlayerView extends MvpView{
    void showDexPoints(final int dexPoints);
    void showPrcPoints(final int prcPoints);
    void showPointsToDistribute(final int pointsToDistribute);

    void stateNoPointsToDistribute();
    void stateMaxPointsToDistribute();
    void stateSomePointsToDistribute();

    void disableDexMinus();
    void disablePrcMinus();
}
