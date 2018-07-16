package com.npgames.insight.data.model;

import android.app.Activity;
import android.text.TextPaint;
import android.widget.Button;

import com.npgames.insight.ui.book.Pagination;

import org.w3c.dom.Text;

/**
 * Created by Влад on 22.10.2017.
 */

public class TextAreaModel {


    private String sourceText;
    private int width;
    private int pageMaxHeight;
    private TextPaint pagePaint;
    private float lineSpacingMultiplier;
    private float lineSpacingExtra;
    private boolean includeFontPadding;


    public TextAreaModel() {

    }




    public String getSourceText() {
        return sourceText;
    }

    public int getWidth() {
        return width;
    }

    public int getPageMaxHeight() {
        return pageMaxHeight;
    }

    public TextPaint getPagePaint() {
        return pagePaint;
    }

    public float getLineSpacingMultiplier() {
        return lineSpacingMultiplier;
    }

    public float getLineSpacingExtra() {
        return lineSpacingExtra;
    }

    public boolean isIncludeFontPadding() {
        return includeFontPadding;
    }

    public static TextAreaModel.Builder newBuilder() {
        return new TextAreaModel().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setText(final String text) {
            sourceText = text;
            return this;
        }

        public Builder setWidth(final int width) {
            TextAreaModel.this.width = width;
            return this;
        }

        public Builder setMaxHeight(final int maxHeight) {
            pageMaxHeight = maxHeight;
            return this;
        }

        public Builder setPagePaint(final TextPaint textPaint) {
            pagePaint = textPaint;
            return this;
        }

        public Builder setSpacingMult(final float spacingMult) {
            lineSpacingMultiplier = spacingMult;
            return this;
        }

        public Builder setSpacingSpacingAdd(final float lineSpacingExtra) {
            TextAreaModel.this.lineSpacingExtra = lineSpacingExtra;
            return this;
        }

        public Builder setIncludePad(final boolean includePad) {
            includeFontPadding = includePad;
            return this;
        }

        public TextAreaModel build() {
            return TextAreaModel.this;
        }
    }
}
