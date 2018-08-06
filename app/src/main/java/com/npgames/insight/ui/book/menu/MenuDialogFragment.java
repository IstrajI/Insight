package com.npgames.insight.ui.book.menu;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.npgames.insight.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDialogFragment extends DialogFragment implements View.OnClickListener{
    @BindView(R.id.menu_dialog_go_to_main_menu_button)
    protected Button mainMenuButton;

    protected MenuDialogClickListener menuDialogClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View deathDialog = inflater.inflate(R.layout.fragment_menu_dialog, null);
        ButterKnife.bind(this, deathDialog);

        mainMenuButton.setOnClickListener(this);
        return deathDialog;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setOnClickListener(final MenuDialogClickListener menuDialogClickListener) {
        this.menuDialogClickListener = menuDialogClickListener;
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()) {
            case R.id.menu_dialog_go_to_main_menu_button:
                menuDialogClickListener.menuDialogOnGoToMainMenuClick();
                break;
        }
    }

    public interface MenuDialogClickListener {
        void menuDialogOnGoToMainMenuClick();
    }
}
