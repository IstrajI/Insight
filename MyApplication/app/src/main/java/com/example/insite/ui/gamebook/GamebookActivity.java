package com.example.insite.ui.gamebook;

import android.os.Bundle;
import androidx.core.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.insite.R;
import com.example.insite.application.Constants;
import com.example.insite.ui.all.adapters.GamepagesAdapter;
import com.example.insite.ui.gamebook.gamepage.GamepageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamebookActivity extends AppCompatActivity{

    @BindView(R.id.view_pager_game_book)
    protected ViewPager gameBookViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_book);
        ButterKnife.bind(this);

        final Bundle firstPageBundle = new Bundle();
        firstPageBundle.putString(Constants.Bundles.ID, "1");
        final GamepageFragment firstFragment = GamepageFragment.newInstance();
        firstFragment.setArguments(firstPageBundle);

        final Bundle secondPageBundle = new Bundle();
        secondPageBundle.putString(Constants.Bundles.ID, "2");
        final GamepageFragment secondFragment = GamepageFragment.newInstance();
        secondFragment.setArguments(secondPageBundle);


        final GamepagesAdapter adapter = new GamepagesAdapter(getSupportFragmentManager());
        adapter.addFragment(firstFragment, "1");
        adapter.addFragment(secondFragment, "2");

        gameBookViewPager.setOffscreenPageLimit(adapter.getCount());
        gameBookViewPager.setAdapter(adapter);



    }
}
