package com.npgames.insight.ui.book.top_panel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.npgames.insight.R;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsInfoDialog extends DialogFragment {

    public static final String STATS_INFO_DIALOG_TAG = "STATS_INFO_DIALOG_TAG";

    @BindView(R.id.tip_dialog_root)
    ConstraintLayout mConstraintLayout;

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(final @NonNull LayoutInflater inflater, final @Nullable ViewGroup container,
                             final @Nullable Bundle savedInstanceState) {
        final View statsInfoDialog = inflater.inflate(R.layout.fragment_tip_dialog, container);
        ButterKnife.bind(this, statsInfoDialog);

        final Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);


        final TextView textView = new TextView(getActivity());

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        textView.setId(ViewCompat.generateViewId());
        textView.setLayoutParams(layoutParams);

        Log.d("TestPish", "width "+getArguments().getInt("WIDTH"));
        Log.d("TestPish", "height "+getArguments().getInt("HEIGHT"));

        Log.d("TestPish", "X "+getArguments().getFloat("X"));
        Log.d("TestPish", "Y "+getArguments().getFloat("Y"));
/*
        textView.setWidth(getArguments().getInt("WIDTH"));
        textView.setHeight(getArguments().getInt("HEIGHT"));*/

        textView.setLeft((int) getArguments().getFloat("X"));
        textView.setTop((int) getArguments().getFloat("Y"));
        textView.setTextAppearance(getContext(), R.style.D);
        textView.setText(getArguments().getString("TEXT"));
        textView.setTextSize(getArguments().getFloat("TEXT_SIZE"));

        final ConstraintSet set = new ConstraintSet();
        mConstraintLayout.addView(textView);
        set.clone(mConstraintLayout);
        set.connect(textView.getId(), ConstraintSet.TOP, mConstraintLayout.getId(), ConstraintSet.TOP, 0);
        set.connect(textView.getId(), ConstraintSet.END, mConstraintLayout.getId(), ConstraintSet.END, 0);
        set.applyTo(mConstraintLayout);

        mConstraintLayout.invalidate();

        return statsInfoDialog;
    }
}
