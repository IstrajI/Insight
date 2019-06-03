package com.npgames.insight.ui.book.top_panel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import uk.co.samuelwall.materialtaptargetprompt.extras.PromptFocal;
import uk.co.samuelwall.materialtaptargetprompt.extras.PromptOptions;

public class TipPromptFocal extends PromptFocal {
    @Override
    public void update(@NonNull PromptOptions options, float revealModifier, float alphaModifier) {

    }

    @Override
    public void draw(final @NonNull Canvas canvas) {

        final RectF dimBounds = new RectF();
        dimBounds.set(left, top, left + width, top+height);

        final Paint dimPaint = new Paint();
        dimPaint.setColor(Color.RED);

        canvas.drawRect(dimBounds, dimPaint);
    }

    @Override
    public boolean contains(float x, float y) {
        return false;
    }

    @Override
    public void setColour(int colour) {

    }

    @NonNull
    @Override
    public RectF getBounds() {
        return null;
    }

    private int width;
    private int height;
    private float left;
    private float top;

    @Override
    public void prepare(final @NonNull PromptOptions options, final @NonNull View target,
                        final int[] promptViewPosition) {
        Log.d("TestPish", "Ok counstructor");
        width = target.getWidth();
        height = target.getHeight();
        left = target.getX();
        top = target.getY();
    }

    @Override
    public void prepare(@NonNull PromptOptions options, float targetX, float targetY) {
        Log.d("TestPish", "NOT Ok constructor");

        left = targetX;
        top = targetY;

    }

    @Override
    public void updateRipple(float revealModifier, float alphaModifier) {

    }
}
