package com.npgames.insight.ui.home.authors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.npgames.insight.R;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;

public class AuthorsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);
    }
}
