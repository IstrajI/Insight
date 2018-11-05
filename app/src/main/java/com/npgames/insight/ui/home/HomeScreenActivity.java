package com.npgames.insight.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.npgames.insight.R;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener {
    @BindView(R.id.button_home_continue)
    protected TextView continueButton;
    @BindView(R.id.button_home_new_game)
    protected TextView newGameButton;
    @BindView(R.id.button_home_about)
    protected TextView aboutGameButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
}
