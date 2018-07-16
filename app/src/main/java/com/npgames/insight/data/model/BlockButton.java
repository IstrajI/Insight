package com.npgames.insight.data.model;

import java.io.Serializable;

import static com.npgames.insight.data.model.BlockArea.BlockType.BUTTON;

/**
 * Created by Влад on 27.10.2017.
 */

public class BlockButton extends BlockArea implements Serializable{
    private int height;

    public BlockButton(final String content){
        this.type = BUTTON;
        this.content = content;
    }

    @Override
    public int getViewHeight() {
        return 210;
    }
}
