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
    public static final String DRAWABLE_ID = "DRAWABLE_ID";

    @BindView(R.id.equipment_more_description_text_view)
    protected TextView descriptionTextView;
    @BindView(R.id.equipment_more_item_picture_image_view)
    protected ImageView itemPictureImageView;
    @BindView(R.id.equipment_more_title_text_view)
    protected TextView titleTextView;

    public static EquipmentDialogFragment createEquipmentDialogFragment(final String name, final String description, final int drawableID) {
        final Bundle bundle = new Bundle();
        bundle.putString(NAME, name);
        bundle.putString(DESCRIPTION, description);
        bundle.putInt(DRAWABLE_ID, drawableID);

        final EquipmentDialogFragment equipmentDialogFragment = new EquipmentDialogFragment();
        equipmentDialogFragment.setArguments(bundle);
        return equipmentDialogFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View dialogView = inflater.inflate(R.layout.dialog_fragment_equipment_more, null);
        ButterKnife.bind(this, dialogView);

        final Bundle bundle = getArguments();
        titleTextView.setText(bundle.getString(NAME));
        descriptionTextView.setText(bundle.getString(DESCRIPTION));
        itemPictureImageView.setImageDrawable(getResources().getDrawable(bundle.getInt(DRAWABLE_ID)));
        return dialogView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
