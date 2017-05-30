package com.npgames.insight.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.npgames.insight.R;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener {

    @BindView(R.id.button_home_start)
    protected Button startButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void bindViews() {
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.button_home_start:
                ActivityNavigator.startGameBookActivity(this);
                //ActivityNavigator.startCreatePlayerActivity(this);
                break;
        }
    }
}
