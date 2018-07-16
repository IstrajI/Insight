package com.npgames.insight.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomPanel extends BottomSheetDialog {
/*    @BindView(R.id.button_bottom_panel_find)
    protected Button findButton;*/

    public BottomPanel(@NonNull Context context) {
        super(context);

        final ViewGroup root = (ViewGroup) getLayoutInflater().inflate(R.layout.layout_bottom_panel, null);
        setContentView(root);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) root.getParent());
        BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // do something
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // do something
            }
        };

    }

    public BottomPanel(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected BottomPanel(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
