package com.npgames.insight.data.model;

import java.io.Serializable;

import static com.npgames.insight.data.model.BlockArea.BlockType.BUTTON;

/**
 * Created by Влад on 27.10.2017.
 */

public class BlockButton extends BlockArea implements Serializable{
    private int height;
    private boolean isEnable;
    public BlockButton(final String content){
        this.type = BUTTON;
        this.content = content;
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
