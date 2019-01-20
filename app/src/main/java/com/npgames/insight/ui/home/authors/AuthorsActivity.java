package com.npgames.insight.ui.home.authors;

import android.os.Bundle;
import com.npgames.insight.R;
import com.npgames.insight.ui.InsightApplication;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;

public class AuthorsActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ((InsightApplication) getApplication()).setMusic(R.raw.main_menu_sound);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void bindViews() {

    }
}
