package com.npgames.insight.data.model;

import java.io.Serializable;

public class BlockAction extends BlockArea implements Serializable{
    private boolean isEnable;

    public BlockAction(final String content) {
        this.content = content;
        this.type = BlockType.ACTION;
        this.isEnable = true;
    }
    @Override
    public int getViewHeight() {
        return 210;
    }

    public void setEnable(final boolean isEnable) {
        this.isEnable = isEnable;
    }

    public boolean isEnable() {
        return this.isEnable;
    }
}
