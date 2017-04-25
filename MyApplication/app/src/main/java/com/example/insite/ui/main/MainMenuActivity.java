package com.example.insite.ui.main;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.example.insite.R;
import com.example.insite.ui.ActivityNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button_main_menu_start)
    Button startButton;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        startButton.setOnClickListener(this);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d("screen size ", "" +size.x);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_main_menu_start:
                ActivityNavigator.startGameBookActivity(getApplicationContext());
        }
    }
}
