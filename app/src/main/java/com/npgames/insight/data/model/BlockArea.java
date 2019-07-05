package com.npgames.insight.data.model;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

import static com.npgames.insight.data.model.BlockArea.BlockType.ACTION;
import static com.npgames.insight.data.model.BlockArea.BlockType.BUTTON;
import static com.npgames.insight.data.model.BlockArea.BlockType.CREATE_PLAYER_BUTTONS;
import static com.npgames.insight.data.model.BlockArea.BlockType.CREATE_PLAYER_DEX;
import static com.npgames.insight.data.model.BlockArea.BlockType.CREATE_PLAYER_PRC;
import static com.npgames.insight.data.model.BlockArea.BlockType.IMAGE;
import static com.npgames.insight.data.model.BlockArea.BlockType.TEXT;

public abstract class BlockArea {
    @IntDef({TEXT, BUTTON, IMAGE, ACTION, CREATE_PLAYER_DEX, CREATE_PLAYER_PRC, CREATE_PLAYER_BUTTONS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BlockType {
        int TEXT = 0;
        int BUTTON = 1;
        int IMAGE = 2;
        int ACTION = 3;
        int CREATE_PLAYER_DEX = 4;
        int CREATE_PLAYER_PRC = 5;
        int CREATE_PLAYER_BUTTONS = 6;
    }

    public @BlockArea.BlockType int type;
    public String content;

    public abstract int getViewHeight();
}
