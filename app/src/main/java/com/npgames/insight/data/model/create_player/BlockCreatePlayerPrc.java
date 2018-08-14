package com.npgames.insight.data.model.create_player;

import com.npgames.insight.data.model.BlockArea;

public class BlockCreatePlayerPrc extends BlockArea {
    private int prcPoints;

    @Override
    public int getViewHeight() {
        return 0;
    }

    public int getPrcPoints() {
        return prcPoints;
    }

    public void setPrcPoints(int prcPoints) {
        this.prcPoints = prcPoints;
    }
}
