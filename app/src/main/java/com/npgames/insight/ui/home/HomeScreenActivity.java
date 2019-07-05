package com.npgames.insight.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.Task;
import com.npgames.insight.R;
//import com.npgames.insight.application.MusicService;
import com.npgames.insight.ui.ActivityNavigator;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;
import com.npgames.insight.ui.book.GameBookActivity;

import butterknife.BindView;

public class HomeScreenActivity extends BaseMvpActivity implements View.OnClickListener, HomeScreenView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    @BindView(R.id.button_home_continue)
    protected TextView continueButton;
    @BindView(R.id.button_home_new_game)
    protected TextView newGameButton;
    @BindView(R.id.button_home_about)
    protected TextView aboutGameButton;
    @BindView(R.id.button_home_directory)
    protected TextView homeDirectoryButton;
    @BindView(R.id.button_home_achievements)
    protected TextView achivementsGameButton;
    private boolean serviceBound;
    @InjectPresenter
    HomeScreenPresenter homeScreenPresenter;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient googleSignInClient;
    private int SIGN_IN_REQUEST_CODE = 5;

    @Override
    public void onConnected(@Nullable final Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(final int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {

    }

    @ProvidePresenter
    HomeScreenPresenter provideHomeScrreenPresenter() {
        return new HomeScreenPresenter(getApplicationContext());
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, options);

/*        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .addApi(Drive.API)
                .build();

        googleApiClient.connect();*/
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //((InsightApplication)getApplication()).setMusic(R.raw.main_menu_sound);
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
        achivementsGameButton.setOnClickListener(this);
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

            case R.id.button_home_achievements: {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
                //ActivityNavigator.startAchivementsActivity(this);
                break;
            }
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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            final Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //
            } catch (ApiException e) {
                ActivityNavigator.startAchivementsActivity(this);
            }
        }
    }
}
