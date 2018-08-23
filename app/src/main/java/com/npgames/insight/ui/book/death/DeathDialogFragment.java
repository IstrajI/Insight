package com.npgames.insight.ui.book.death;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.ui.book.page.GamePageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeathDialogFragment extends DialogFragment {

    @BindView(R.id.death_dialog_bottom_pattern_image_view)
    protected ImageView deathDialogBottomPanelImageView;

    @BindView(R.id.death_dialog_go_to_main_button)
    protected TextView deathDialogGoToMainMenuTextView;

    public static String DEATH_DIALOG_FRAGMENT_TAG = "DEATH_DIALOG_FRAGMENT_TAG";

    private IDeathDialogListener deathDialogListener;

    protected Drawable pressedButtonState;
    protected Drawable unpressedButtonState;

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View deathDialog = inflater.inflate(R.layout.fragment_death_dialog, container);
        ButterKnife.bind(this, deathDialog);

        pressedButtonState = getResources().getDrawable(R.drawable.bottom_action_4_xxx);
        unpressedButtonState = getResources().getDrawable(R.drawable.death_screen_new_5_bottom_xxx);
        return deathDialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        deathDialogListener.onClose();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener((dialogInterface, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK) {
                deathDialogListener.onClose();
            }

            return true;
        });

        deathDialogBottomPanelImageView.setOnTouchListener((view1, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    view1.setBackground(pressedButtonState);
                    deathDialogGoToMainMenuTextView.setTextColor(getResources().getColor(R.color.death_dialog_go_to_main_menu_text_action_color));
                    break;

                case MotionEvent.ACTION_UP:
                    deathDialogListener.onClose();
                    break;
            }

            return true;
        });
    }



    public void setOnDeathDialogListener(final IDeathDialogListener iDeathDialogListener) {
        this.deathDialogListener = iDeathDialogListener;
    }

    public interface IDeathDialogListener {
        void onClose();
    }
}
