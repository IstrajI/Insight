package com.npgames.insight.ui.player;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.TextAlignment;
import com.npgames.insight.R;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CreatePlayerActivity extends BaseMvpActivity implements CreatePlayerView {

    @BindView(R.id.text_view_game_paragraph_text)
    protected DocumentView overviewDocumentView;
    @BindView(R.id.document_view_create_character_skill_info)
    protected DocumentView skillInfoDocumentView;
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

    @InjectPresenter
    CreatePlayerPresenter createPlayerPresenter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_character);
    }

    @Override
    protected void bindViews() {
        initDocumentView(overviewDocumentView);
        initDocumentView(skillInfoDocumentView);

        overviewDocumentView.setText(getResources().getString(R.string.create_character_overview));
        skillInfoDocumentView.setText(getResources().getString(R.string.create_character_skill_info_hp));
    }

    private void initDocumentView(final DocumentView documentView) {
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setTextColor(getResources().getColor(R.color.colorTextPrimary));
        documentView.getDocumentLayoutParams().setTextSize(16);
    }


    @OnClick({R.id.button_create_character_dex_points_plus,
            R.id.button_create_character_prc_points_plus,
            R.id.button_create_character_prc_points_minus,
            R.id.button_create_character_dex_points_minus,
            R.id.button_create_character_reset,
            R.id.button_create_character_continue})
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
            case R.id.button_create_character_continue:
                createPlayerPresenter.requestPoints();
                break;
            case R.id.button_create_character_reset:
                createPlayerPresenter.resetPoints();
                break;
        }
    }

    @OnClick({R.id.button_create_character_hp_more,
            R.id.button_create_character_aur_more,
            R.id.button_create_character_prc_more,
            R.id.button_create_character_dex_more})
    public void onMoreClick(final Button moreButton) {
        switch (moreButton.getId()) {
            case R.id.button_create_character_hp_more:
                skillInfoDocumentView.setText(getResources().getString(R.string.create_character_skill_info_hp));
                break;
            case R.id.button_create_character_aur_more:
                skillInfoDocumentView.setText(getResources().getString(R.string.create_character_skill_info_aur));
                break;
            case R.id.button_create_character_dex_more:
                skillInfoDocumentView.setText(getResources().getString(R.string.create_character_skill_info_dex));
                break;
            case R.id.button_create_character_prc_more:
                skillInfoDocumentView.setText(getResources().getString(R.string.create_character_skill_info_prc));
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
}
