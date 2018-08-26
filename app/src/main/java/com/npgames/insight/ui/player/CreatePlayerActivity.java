package com.npgames.insight.ui.player;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.npgames.insight.R;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CreatePlayerActivity extends BaseMvpActivity implements CreatePlayerView{
    @BindView(R.id.text_view_game_paragraph_text)
    protected TextView overviewTextView;
    @BindView(R.id.text_view_create_character_skill_info)
    protected TextView skillInfoTextView;
    @BindView(R.id.text_view_create_character_hp_points)
    protected TextView hpPointsTextView;
    @BindView(R.id.text_view_create_character_aur_points)
    protected TextView aurPointsTextView;
    @BindView(R.id.text_view_create_character_dex_points)
    protected TextView dexPointsTextView;
    @BindView(R.id.text_view_create_character_prc_points)
    protected TextView prcPointsTextView;
    @BindView(R.id.text_view_create_character_points_to_distribute)
    protected TextView pointsToDistributeTextView;

    @BindView(R.id.button_create_character_continue)
    protected Button continueButton;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_character);
    }

    @Override
    protected void bindViews() {

        overviewTextView.setText(getResources().getString(R.string.create_character_overview));
        skillInfoTextView.setText(getResources().getString(R.string.create_character_skill_info_hp));
    }

/*
    @OnClick({R.id.button_create_character_dex_points_plus,
            R.id.button_create_character_prc_points_plus,
            R.id.button_create_character_prc_points_minus,
            R.id.button_create_character_dex_points_minus,
            R.id.button_create_character_reset})
    public void onSkillPointsClick(final Button button) {
        final int ONE_SKILL_POINT = 1;
        switch(button.getId()) {
            case R.id.button_create_character_dex_points_plus:
                createPlayerPresenter.changeDexPoints(ONE_SKILL_POINT);
                break;
            case R.id.button_create_character_prc_points_plus:
                createPlayerPresenter.changePrcPoints(ONE_SKILL_POINT);
                break;
            case R.id.button_create_character_dex_points_minus:
                createPlayerPresenter.changeDexPoints(-ONE_SKILL_POINT);
                break;
            case R.id.button_create_character_prc_points_minus:
                createPlayerPresenter.changePrcPoints(-ONE_SKILL_POINT);
                break;
            case R.id.button_create_character_reset:
                createPlayerPresenter.resetPoints();
                break;
        }
    }
*/

    @OnClick({R.id.button_create_character_hp_more,
            R.id.button_create_character_aur_more,
            R.id.button_create_character_prc_more,
            R.id.button_create_character_dex_more})
    public void onMoreClick(final Button moreButton) {
        switch (moreButton.getId()) {
            case R.id.button_create_character_hp_more:
                skillInfoTextView.setText(getResources().getString(R.string.create_character_skill_info_hp));
                break;
            case R.id.button_create_character_aur_more:
                skillInfoTextView.setText(getResources().getString(R.string.create_character_skill_info_aur));
                break;
            case R.id.button_create_character_dex_more:
                skillInfoTextView.setText(getResources().getString(R.string.create_character_skill_info_dex));
                break;
            case R.id.button_create_character_prc_more:
                skillInfoTextView.setText(getResources().getString(R.string.create_character_skill_info_prc));
                break;
        }
    }


    public void updateDexPoints(final int newDexPoints, final int pointsToDistribute) {
        pointsToDistributeTextView.setText(String.valueOf(pointsToDistribute));
        dexPointsTextView.setText(String.valueOf(newDexPoints));
    }

    public void updatePrcPoints(final int newPrcPoints, final int pointsToDistribute) {
        pointsToDistributeTextView.setText(String.valueOf(pointsToDistribute));
        prcPointsTextView.setText(String.valueOf(newPrcPoints));
    }

    public void resetPlayerSkillPoints(final int newSkillPoints, final int dex, final int prc) {
        pointsToDistributeTextView.setText(String.valueOf(newSkillPoints));
        dexPointsTextView.setText(String.valueOf(dex));
        prcPointsTextView.setText(String.valueOf(prc));
    }

    @Override
    public void sharePoints(final int dex, final int prc) {
        final Intent intent = new Intent();
        intent.putExtra("DEX", dex);
        intent.putExtra("PRC", prc);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void changeContinueStatus(final boolean status) {
        if (continueButton.isEnabled() == status) return;
        continueButton.setEnabled(status);
    }
}
