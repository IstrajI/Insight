package com.npgames.insight.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.npgames.insight.R;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;
import com.npgames.insight.ui.book.bottom_new.BottomPanelPresenter;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener, HomeScreenView {
    @BindView(R.id.button_home_continue)
    protected TextView continueButton;
    @BindView(R.id.button_home_new_game)
    protected TextView newGameButton;
    @BindView(R.id.button_home_about)
    protected TextView aboutGameButton;

    @InjectPresenter
    HomeScreenPresenter homeScreenPresenter;
    @ProvidePresenter
    HomeScreenPresenter provideHomeScrreenPresenter() {
        return new HomeScreenPresenter(getApplicationContext());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TestPish", "Home: onCreate");
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TestPish", "Home: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TestPish", "Home: onResume");
        homeScreenPresenter.checkContinueButtonState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TestPish", "Home: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestPish", "Home: onDestroy");
    }

    @Override
    protected void bindViews(){
        continueButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
        aboutGameButton.setOnClickListener(this);
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
