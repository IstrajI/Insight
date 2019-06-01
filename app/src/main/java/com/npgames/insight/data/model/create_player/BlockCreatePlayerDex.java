package com.npgames.insight.data.model.create_player;

import com.npgames.insight.data.model.BlockArea;

public class BlockCreatePlayerDex extends BlockArea {
    private int dexPoints;

    public BlockCreatePlayerDex(){
        this.type = BlockType.CREATE_PLAYER_DEX;
    }

    @Override
    public int getViewHeight() {
        return 450;
    }

    public int getDexPoints() {
        return dexPoints;
    }

    public void setDexPoints(int dexPoints) {
        this.dexPoints = dexPoints;
    }
}
