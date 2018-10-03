package com.npgames.insight.ui.book.armory;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.CONFIRMATION_TEXT;

public class EquipmentDialogFragment extends DialogFragment implements View.OnClickListener{
    public static final String TAG = "EQUIPMENT_DIALOG_TAG";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    @BindView(R.id.equipment_more_description_text_view)
    protected TextView descriptionTextView;
    @BindView(R.id.equipment_more_item_picture_image_view)
    protected ImageView itemPictureImageView;

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View dialogView = inflater.inflate(R.layout.dialog_fragment_equipment_more, null);
        ButterKnife.bind(this, dialogView);

        descriptionTextView.setText(getArguments().getString(DESCRIPTION));
        itemPictureImageView.setImageDrawable(getResources().getDrawable(R.drawable.blaster3));
        return dialogView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String bodyText = getArguments().getString(CONFIRMATION_TEXT);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setOnKeyListener((dialogInterface, i, keyEvent) -> {
/*            if (i == KeyEvent.KEYCODE_BACK) {
                deathDialogListener.onClose();
            }*/

            return true;
        });
    }

    @Override
    public void onClick(final View v) {
    }
}
