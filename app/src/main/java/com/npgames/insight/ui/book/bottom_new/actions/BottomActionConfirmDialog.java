package com.npgames.insight.ui.book.bottom_new.actions;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomActionConfirmDialog extends DialogFragment {
    public static final String CONFIRMATION_TEXT = "CONFIRMATION_TEXT";
    private String text;

    @BindView(R.id.bottom_action_confirm_dialog_body_text_view)
    protected TextView confirmDialogBodyTextView;

    public static String BOTTOM_ACTION_CONFIRM_DIALOG_TAG = "BOTTOM_ACTION_CONFIRM_DIALOG_TAG";

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View actionConfirmDialog = inflater.inflate(R.layout.fragment_bottom_action_confirm_dialog, container);
        ButterKnife.bind(this, actionConfirmDialog);

        return actionConfirmDialog;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final String bodyText = getArguments().getString(CONFIRMATION_TEXT);
        confirmDialogBodyTextView.setText(bodyText);
    }
}
