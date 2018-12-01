package com.npgames.insight.ui.home;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.domain.GameInteractor;

@InjectViewState

public class HomeScreenPresenter extends MvpPresenter<HomeScreenView> {

    private GameInteractor gameInteractor;

    public HomeScreenPresenter(final Context context) {
        gameInteractor = new GameInteractor(context);
    }

    public void checkContinueButtonState() {
        if (gameInteractor.isContinueGameAvailable()) {
            getViewState().setContinueButtonEnabled();
        } else {
            getViewState().setContinueButtonDisabled();
        }
    }
}
