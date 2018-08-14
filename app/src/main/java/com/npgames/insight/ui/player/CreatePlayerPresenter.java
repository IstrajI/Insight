package com.npgames.insight.ui.player;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.model.Player;

@InjectViewState
public class CreatePlayerPresenter extends MvpPresenter<CreatePlayerView> {
    private final int MAX_POINTS_TO_DISTRIBUTE = 4;
    private int pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;
    private int dex = Player.INIT_DEX;
    private int prc = Player.INIT_PRC;

    public void dexMinus() {
        //try to return points that wasn't belong to destributed points
        if (pointsToDistribute < MAX_POINTS_TO_DISTRIBUTE || dex > Player.INIT_DEX) {
            dex--;
            pointsToDistribute++;
        }
    }

    public void dexPlus() {
        //no points to destribute
        if (pointsToDistribute != 0) {
            dex ++;
            pointsToDistribute--;
        }
    }

    public void prcMinus() {
        //try to return points that wasn't belong to destributed points
        if (pointsToDistribute < MAX_POINTS_TO_DISTRIBUTE || prc > Player.INIT_PRC) {
            prc--;
            pointsToDistribute++;
        }
    }

    public void prcPlus() {
        //no points to destribute
        if (pointsToDistribute != 0) {
            prc ++;
            pointsToDistribute--;
        }
    }



    public void changeDexPoints(final int dexPoints) {
        if (pointsToDistribute - dexPoints < 0 || pointsToDistribute-dexPoints > MAX_POINTS_TO_DISTRIBUTE
                || dex + dexPoints < Player.INIT_DEX) return;
        dex += dexPoints;
        pointsToDistribute -= dexPoints;
        getViewState().updateDexPoints(dex, pointsToDistribute);

        checkContinueButtonStatus();
    }

    public void changePrcPoints(final int prcPoints) {
        if (pointsToDistribute - prcPoints < 0 || pointsToDistribute-prcPoints > MAX_POINTS_TO_DISTRIBUTE
                || prc + prcPoints < Player.INIT_PRC) return;
        prc += prcPoints;
        pointsToDistribute -= prcPoints;
        getViewState().updatePrcPoints(prc, pointsToDistribute);

        checkContinueButtonStatus();
    }

    private void checkContinueButtonStatus() {
        if (pointsToDistribute == 0) {
            getViewState().changeContinueStatus(true);
            return;
        }
        getViewState().changeContinueStatus(false);
    }

    void requestPoints() {
        getViewState().sharePoints(dex, prc);
    }

    void resetPoints() {
        pointsToDistribute = MAX_POINTS_TO_DISTRIBUTE;
        getViewState().resetPlayerSkillPoints(MAX_POINTS_TO_DISTRIBUTE, Player.INIT_DEX, Player.INIT_PRC);
    }
}
