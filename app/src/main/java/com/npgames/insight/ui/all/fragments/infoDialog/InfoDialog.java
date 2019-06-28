package com.npgames.insight.ui.all.fragments.infoDialog;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoDialog extends DialogFragment {
    public static final String INFO_DIALOG_TAG = "INFO_DIALOG_TAG";
    public static String INFO_DIALOG_MESSAGE = "INFO_DIALOG_MESSAGE";
    public static String INFO_DIALOG_ICON = "INFO_DIALOG_ICON";
    public static String INFO_DIALOG_TITLE = "INFO_DIALOG_TITLE";

    @BindView(R.id.bottom_action_confirm_dialog_body_text_view)
    protected TextView bodyTextView;
    @BindView(R.id.info_dialog_icon_image_view)
    protected ImageView iconImageView;
    @BindView(R.id.info_dialog_title_text_view)
    protected TextView titleTextView;
    @BindView(R.id.bottom_action_confirm_dialog_deny_button_text_view)
    protected TextView denyButtonTextView;


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_info_dialog, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String bodyText = getArguments().getString(INFO_DIALOG_MESSAGE);
        bodyTextView.setText(bodyText);

        final int icon = getArguments().getInt(INFO_DIALOG_ICON, 0);
        if (icon != 0) {
            iconImageView.setImageDrawable(getResources().getDrawable(icon));
        } else {
            iconImageView.setVisibility(View.GONE);
        }

        final String title = getArguments().getString(INFO_DIALOG_TITLE, "");
        if (!title.isEmpty()) {
            titleTextView.setText(title);
        } else {
            titleTextView.setVisibility(View.GONE);
        }

        denyButtonTextView.setOnTouchListener(denyButtonTouchListener);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener((dialogInterface, i, keyEvent) -> {
/*            if (i == KeyEvent.KEYCODE_BACK) {
                deathDialogListener.onClose();
            }*/

            return true;
        });
    }

    private View.OnTouchListener denyButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            switch(motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final Drawable pressedDenyButton = getResources().getDrawable(R.drawable.confirm_dialog_button_active);
                    denyButtonTextView.setBackground(pressedDenyButton);
                    break;

                case MotionEvent.ACTION_UP:
                    view.performClick();
                    dismiss();

                case MotionEvent.ACTION_CANCEL:
                    final Drawable defaultDenyButton = getResources().getDrawable(R.drawable.confirm_dialog_button);
                    denyButtonTextView.setBackground(defaultDenyButton);

                    break;
            }
            return true;
        }
    };
}
