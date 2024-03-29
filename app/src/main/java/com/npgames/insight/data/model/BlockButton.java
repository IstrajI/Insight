package com.npgames.insight.data.model;

import android.util.Log;

import java.io.Serializable;

import static com.npgames.insight.data.model.BlockArea.BlockType.BUTTON;


public class BlockButton extends BlockArea implements Serializable{
    private int height;
    private boolean isEnable;
    private int paragraphNumber;

    public BlockButton(final String content){
        this.type = BUTTON;
        this.content = content;
        this.isEnable = true;
        this.paragraphNumber = Integer.parseInt(content);
    }

    @Override
    public int getViewHeight() {
        return 210;
    }

    public void setEnable(final boolean isEnable) {
        Log.d("TestPishCheck", "number" +paragraphNumber +" isEnable = " +isEnable);
        this.isEnable = isEnable;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public int getParagraphNumber() {
        return paragraphNumber;
    }
}
