package com.npgames.insight.ui.player;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.dao.GamePreferences;
import com.npgames.insight.data.model.Player;

@InjectViewState
public class CreatePlayerPresenter extends MvpPresenter<CreatePlayerView> {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private int pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;
    private int dex = Player.INIT_DEX;
    private int prc = Player.INIT_PRC;

    public void changeDexPoints(final int dexPoints) {
        if (pointsToDistribute - dexPoints < 0 || pointsToDistribute-dexPoints > MAX_POINTS_TO_DISTRIBUTE
                || dex + dexPoints < Player.INIT_DEX) return;
        dex += dexPoints;
        pointsToDistribute -= dexPoints;
        getViewState().updateDexPoints(dex, pointsToDistribute);
    }

    public void changePrcPoints(final int prcPoints) {
        if (pointsToDistribute - prcPoints < 0 || pointsToDistribute-prcPoints > MAX_POINTS_TO_DISTRIBUTE
                || prc + prcPoints < Player.INIT_PRC) return;
        prc += prcPoints;
        pointsToDistribute -= prcPoints;
        getViewState().updatePrcPoints(prc, pointsToDistribute);
    }

    void requestPoints() {
        getViewState().sharePoints(dex, prc);
    }

    void resetPoints() {
        pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;
        getViewState().resetPlayerSkillPoints(MAX_POINTS_TO_DISTRIBUTE, Player.INIT_DEX, Player.INIT_PRC);
    }
}
