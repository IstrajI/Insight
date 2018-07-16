package com.npgames.insight.data.model;

import java.io.Serializable;

public class BlockAction extends BlockArea implements Serializable{

    BlockAction(final String content, final String code) {
        this.type = BlockType.ACTION;
    }
    @Override
    public int getViewHeight() {
        return 210;
    }
}
