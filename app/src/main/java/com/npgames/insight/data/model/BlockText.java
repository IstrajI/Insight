package com.npgames.insight.data.model;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.io.Serializable;

import static com.npgames.insight.data.model.BlockArea.BlockType.TEXT;

/**
 * Created by Влад on 26.10.2017.
 */

//TODO: changing of orientation will not affect to changing width
public class BlockText extends BlockArea implements Serializable{
    public transient static TextPaint DEF_TEXT_PAINT;
    public static float DEF_SPACING_MULTIPLIER;
    public static float DEF_SPACING_ADD;
    public static int DEF_WIDTH;

    public static final Layout.Alignment ALIGNMENT = Layout.Alignment.ALIGN_NORMAL;
    public static final boolean INCLUDE_PADDING = true;

    public transient StaticLayout layout;
    public @BlockArea.BlockType int type = TEXT;

    public BlockText(final String text) {
        content = text;
        layout = new StaticLayout(text,
                DEF_TEXT_PAINT,
                DEF_WIDTH,
                ALIGNMENT,
                DEF_SPACING_MULTIPLIER,
                DEF_SPACING_ADD,
                INCLUDE_PADDING);
    }

    @Override
    public int getViewHeight() {
        return layout.getLineBottom(layout.getLineCount() - 1);
    }
}
