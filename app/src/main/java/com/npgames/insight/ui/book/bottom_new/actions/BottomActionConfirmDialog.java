package com.npgames.insight.ui.book.bottom_new.actions;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomActionConfirmDialog extends DialogFragment {
    public static final String CONFIRMATION_TEXT = "CONFIRMATION_TEXT";
    public static final String REQUEST_CODE = "REQUEST_CODE";
    private String text;

    @BindView(R.id.bottom_action_confirm_dialog_body_text_view)
    protected TextView bodyTextView;
    @BindView(R.id.bottom_action_confirm_dialog_deny_button_text_view)
    protected TextView denyButtonTextView;
    @BindView(R.id.bottom_action_confirm_dialog_accept_button_text_view)
    protected TextView acceptButtonTextView;

    public static String BOTTOM_ACTION_CONFIRM_DIALOG_TAG = "BOTTOM_ACTION_CONFIRM_DIALOG_TAG";
    private BottomActionConfirmListener confirmListener;

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

        final String bodyText = getArguments().getString(CONFIRMATION_TEXT);
        bodyTextView.setText(bodyText);

        denyButtonTextView.setOnTouchListener(denyButtonTouchListener);
        acceptButtonTextView.setOnTouchListener(acceptButtonTouchListener);

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

    private View.OnTouchListener acceptButtonTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            switch(motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    final Drawable pressedAcceptButton = getResources().getDrawable(R.drawable.confirm_dialog_button_active);
                    acceptButtonTextView.setBackground(pressedAcceptButton);
                    break;

                case MotionEvent.ACTION_UP:
                    view.performClick();
                    confirmListener.onConfirm(getArguments().getInt(REQUEST_CODE));

                case MotionEvent.ACTION_CANCEL:
                    final Drawable defaultAcceptButton = getResources().getDrawable(R.drawable.confirm_dialog_button);
                    acceptButtonTextView.setBackground(defaultAcceptButton);
                    break;
            }
            return true;
        }
    };

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

    public void setConfirmationListener(final BottomActionConfirmListener bottomActionConfirmListener) {
        this.confirmListener = bottomActionConfirmListener;
    }

    public interface BottomActionConfirmListener {
        void onConfirm(int requestCode);
    }
}
