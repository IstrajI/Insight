package com.npgames.insight.ui.book.equipmentDialog;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.MvpDialogFragmentCompat;
import com.npgames.insight.R;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.BOTTOM_ACTION_CONFIRM_DIALOG_TAG;
import static com.npgames.insight.ui.book.bottom_new.actions.BottomActionConfirmDialog.CONFIRMATION_TEXT;

public class EquipmentDialogFragment extends MvpDialogFragmentCompat implements View.OnClickListener, BottomActionConfirmDialog.BottomActionConfirmListener, EquipmentView{
    public static final String TAG = "EQUIPMENT_DIALOG_TAG";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String DRAWABLE_ID = "DRAWABLE_ID";
    public static final String DROP = "DROP";
    public static final String TYPE = "TYPE";

    private EquipmentDialogFragmentInterface equipmentDialogFragmentInterface;

    private final String DROP_ITEM_CONFIRMATION_TEXT = "Вы уверены что хотите оставить снаряжение? Оно будет безвозратно утрачено";

    @BindView(R.id.equipment_more_description_text_view)
    protected TextView descriptionTextView;
    @BindView(R.id.equipment_more_item_picture_image_view)
    protected ImageView itemPictureImageView;
    @BindView(R.id.equipment_more_title_text_view)
    protected TextView titleTextView;
    @BindView(R.id.bottom_action_confirm_dialog_deny_button_text_view)
    protected TextView buttonTextView;
    @BindView(R.id.equipment_more_bottom_panel_discard_image_view)
    protected ImageView discardImageView;

    @InjectPresenter
    EquipmentDialogPresenter equipmentDialogPresenter;
    @ProvidePresenter
    EquipmentDialogPresenter provideEquipmentDialogPresenter() {
        return new EquipmentDialogPresenter(getActivity());
    }

    public static EquipmentDialogFragment createEquipmentDialogFragment(final String name, final String description, final int drawableID, final String type, final boolean isDropEnabled) {
        final Bundle bundle = new Bundle();
        bundle.putString(TYPE, type);
        bundle.putString(NAME, name);
        bundle.putString(DESCRIPTION, description);
        bundle.putInt(DRAWABLE_ID, drawableID);
        bundle.putBoolean(DROP, isDropEnabled);

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

        Log.d("TestPish", "presenter " +(equipmentDialogPresenter == null));

        final Bundle bundle = getArguments();
        titleTextView.setText(bundle.getString(NAME));
        descriptionTextView.setText(bundle.getString(DESCRIPTION));
        itemPictureImageView.setImageDrawable(getResources().getDrawable(bundle.getInt(DRAWABLE_ID)));

        if (bundle.getBoolean(DROP)) {
            discardImageView.setImageDrawable(getResources().getDrawable(R.drawable.selector_delete_button));
            discardImageView.setOnClickListener(view -> {
                final BottomActionConfirmDialog bottomActionConfirmDialog = new BottomActionConfirmDialog();

                final Bundle confirmationBundle = new Bundle();
                confirmationBundle.putString(CONFIRMATION_TEXT, DROP_ITEM_CONFIRMATION_TEXT);
                bottomActionConfirmDialog.setArguments(confirmationBundle);

                bottomActionConfirmDialog.setConfirmationListener(this);
                bottomActionConfirmDialog.show(getFragmentManager(), BOTTOM_ACTION_CONFIRM_DIALOG_TAG);
            });
        } else {
            discardImageView.setImageDrawable(getResources().getDrawable(R.drawable.items_more_bottom_panel_left_no_discard));
        }

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

        buttonTextView.setOnClickListener(view1 -> {
            getDialog().cancel();
        });

    }

    @Override
    public void onClick(final View v) {
    }

    @Override
    public void onConfirm(final int requestCode) {
        equipmentDialogPresenter.dropEquipment(getArguments().getString(TYPE));
        equipmentDialogFragmentInterface.onItemDropped();
        dismiss();
    }

    public interface EquipmentDialogFragmentInterface {
        void onItemDropped();
    }

    public void setEquipmentDialogFragment(final EquipmentDialogFragmentInterface equipmentDialogFragment) {
        this.equipmentDialogFragmentInterface = equipmentDialogFragment;
    }
}
