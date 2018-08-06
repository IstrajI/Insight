package com.npgames.insight.ui.book.death;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.npgames.insight.R;

public class DeathDialogFragment extends DialogFragment {

    public static String DEATH_DIALOG_FRAGMENT_TAG = "DEATH_DIALOG_FRAGMENT_TAG";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View deathDialog = inflater.inflate(R.layout.fragment_death_dialog, null);
        return deathDialog;
    }
}
