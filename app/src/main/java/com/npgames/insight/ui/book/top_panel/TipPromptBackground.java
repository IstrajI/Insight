package com.npgames.insight.ui.book.top_panel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import uk.co.samuelwall.materialtaptargetprompt.extras.PromptOptions;
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.FullscreenPromptBackground;

public class TipPromptBackground extends FullscreenPromptBackground {

    public TipPromptBackground() {

    }

    @Override
    public void prepare(@NonNull PromptOptions options, boolean clipToBounds,
                        @NonNull Rect clipBounds) {
        super.prepare(options, clipToBounds, clipBounds);


    }

    @Override
    public void update(@NonNull PromptOptions prompt, float revealModifier, float alphaModifier) {
        super.update(prompt, revealModifier, alphaModifier);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        final RectF dimBounds = new RectF();
        dimBounds.set(0, 0, getDisplayMetrics().widthPixels, getDisplayMetrics().heightPixels);

        final Paint dimPaint = new Paint();
        dimPaint.setColor(Color.BLACK);

        canvas.drawRect(dimBounds, dimPaint);
    }
}
