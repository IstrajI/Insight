package com.npgames.insight.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.npgames.insight.R;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener {
    @BindView(R.id.button_home_continue)
    protected Button continueButton;
    @BindView(R.id.button_home_new_game)
    protected Button newGameButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void bindViews(){
        continueButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);
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
        }
    }
}
