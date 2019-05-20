package com.npgames.insight.ui.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.application.MusicService;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.InsightApplication;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener, HomeScreenView {
    @BindView(R.id.button_home_continue)
    protected TextView continueButton;
    @BindView(R.id.button_home_new_game)
    protected TextView newGameButton;
    @BindView(R.id.button_home_about)
    protected TextView aboutGameButton;
    @BindView(R.id.button_home_directory)
    protected TextView homeDirectoryButton;
    private boolean serviceBound;
    @InjectPresenter
    HomeScreenPresenter homeScreenPresenter;

    @ProvidePresenter
    HomeScreenPresenter provideHomeScrreenPresenter() {
        return new HomeScreenPresenter(getApplicationContext());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((InsightApplication)getApplication()).setMusic(R.raw.main_menu_sound);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void bindViews() {
        continueButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
        aboutGameButton.setOnClickListener(this);
        homeDirectoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_home_continue:
                ActivityNavigator.startGameBookActivity(this, GameBookActivity.GameType.CONTINUE);
                break;
            case R.id.button_home_new_game:
                ActivityNavigator.startGameBookActivity(this, GameBookActivity.GameType.NEW_GAME);
                break;
            case R.id.button_home_about:
                ActivityNavigator.startAuthorsActivity(this);
                break;

            case R.id.button_home_directory:
                ActivityNavigator.startDirectoryActivity(this);
                break;
        }
    }

    @Override
    public void setContinueButtonEnabled() {
        continueButton.setEnabled(true);
        //continueButton.setBackground(getResources().getDrawable(R.drawable.main_menu_button_new_xxx_default));
    }

    @Override
    public void setContinueButtonDisabled() {
        continueButton.setEnabled(false);
        //continueButton.setBackground(getResources().getDrawable(R.drawable.main_menu_button_new_xxx_disabled));
    }
}
