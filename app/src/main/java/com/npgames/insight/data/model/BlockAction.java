package com.npgames.insight.data.model;

import java.io.Serializable;

public class BlockAction extends BlockArea implements Serializable{

    final int code;
    private boolean isEnable;

    public BlockAction(final String content, final int code) {
        this.code = code;
        this.content = content;
        this.type = BlockType.ACTION;
        this.isEnable = true;
    }
    @Override
    public int getViewHeight() {
        return 210;
    }

    public int getCode() {
        return code;
    }

    public void setEnable(final boolean isEnable) {
        this.isEnable = isEnable;
    }

    public boolean isEnable() {
        return this.isEnable;
    }
}
