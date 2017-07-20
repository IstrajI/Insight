package com.npgames.insight.ui;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ActionsMenuPresenter extends MvpPresenter<ActionsMenuView>{

    private boolean isActionsMenuOpen = false;

    public void changeActionsMenuStatus() {
        isActionsMenuOpen = !isActionsMenuOpen;
        getViewState().openOrCloseActionsMenu(isActionsMenuOpen);
    }
}
