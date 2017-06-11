package com.npgames.insight.ui.book.armory;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.data.model.equipment.Equipment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentDialogFragment extends DialogFragment implements View.OnClickListener{
    @BindView(R.id.text_view_dialog_fragment_equipment_more_name)
    protected TextView nameTextView;
    @BindView(R.id.text_view_dialog_fragment_equipment_more_description)
    protected TextView descriptionTextView;
    @BindView(R.id.button_dialog_fragment_equipment_more_close)
    protected Button closeButton;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View dialogView = inflater.inflate(R.layout.dialog_fragment_equipment_more, null);
        ButterKnife.bind(this, dialogView);

        nameTextView.setText(getArguments().getString(Equipment.NAME));
        descriptionTextView.setText(getArguments().getString(Equipment.DESCRIPTION));
        closeButton.setOnClickListener(this);

        return dialogView;
    }

    @Override
    public void onClick(final View v) {
        switch(v.getId()) {
            case R.id.button_dialog_fragment_equipment_more_close:
                dismiss();
                break;
        }
    }

}
