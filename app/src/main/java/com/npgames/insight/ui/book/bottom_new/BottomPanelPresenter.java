package com.npgames.insight.ui.book.bottom_new;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class BottomPanelPresenter extends MvpPresenter<IBottomPanelView>{
    private boolean isBottomPanelOpen = true;
    private float openYPosition;
    private float closeYPosition;

    public void changeYPosition(final float currentY, final float height, final float topUiHeight) {
        if (openYPosition == 0 && closeYPosition == 0) {
            initStatesYPositions(currentY, height, topUiHeight);
        }

        getViewState().moveYTo(isBottomPanelOpen ? closeYPosition : openYPosition);
        isBottomPanelOpen = !isBottomPanelOpen;
    }

    private void initStatesYPositions(final float currentY, final float height, final float topUiHeight) {
        openYPosition = currentY;
        closeYPosition = currentY + height - topUiHeight / 2;
    }
}
