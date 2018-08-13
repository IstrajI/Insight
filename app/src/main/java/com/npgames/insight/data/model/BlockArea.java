package com.npgames.insight.data.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.npgames.insight.data.model.BlockArea.BlockType.ACTION;
import static com.npgames.insight.data.model.BlockArea.BlockType.BUTTON;
import static com.npgames.insight.data.model.BlockArea.BlockType.CREATE_CHARACTER;
import static com.npgames.insight.data.model.BlockArea.BlockType.IMAGE;
import static com.npgames.insight.data.model.BlockArea.BlockType.TEXT;

public abstract class BlockArea {
    @IntDef({TEXT, BUTTON, IMAGE, ACTION, CREATE_CHARACTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BlockType {
        int TEXT = 0;
        int BUTTON = 1;
        int IMAGE = 2;
        int ACTION = 3;
        int CREATE_CHARACTER = 4;
    }

    public @BlockArea.BlockType int type;
    public String content;

    public abstract int getViewHeight();
}
