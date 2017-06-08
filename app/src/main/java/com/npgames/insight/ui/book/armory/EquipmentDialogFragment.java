package com.npgames.insight.ui.book.armory;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EquipmentDialogFragment extends DialogFragment{



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        final View dialogView = inflater.inflate(R.layout.dialog_fragment_equipment_more, null);
        //ButterKnife.bind(this, dialogView);
        return dialogView;
    }

    public void onClick(View v) {
    }

}
