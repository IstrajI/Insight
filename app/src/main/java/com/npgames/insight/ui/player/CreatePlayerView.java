package com.npgames.insight.ui.player;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.model.Player;

public interface CreatePlayerView extends MvpView{
    void updateDexPoints(final int newDexPoints, final int pointsToDistribute);
    void updatePrcPoints(final int newPrcPoints, final int pointsToDistribute);
    void resetPlayerSkillPoints(final int newSkillPoints, final int initDex, final int initPrc);
    void sharePoints(final int dex, final int prc);
}
