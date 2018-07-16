package com.npgames.insight.data.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Влад on 26.10.2017.
 */

public class PageBlock {

    public static final int TEXT = 0;
    public static final int BUTTON = 1;
    public static final int IMAGE = 2;

    @IntDef({TEXT, BUTTON, IMAGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BlockTypes{}
    private enum TYPE {
        TEXT,
        BUTTON,
        IMAGE}

    public @PageBlock.BlockTypes int type;
    public String content;
}
