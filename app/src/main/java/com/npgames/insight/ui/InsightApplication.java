package com.npgames.insight.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

//import com.npgames.insight.application.MusicService;

public class InsightApplication extends Application {
/*        final MusicService musicService;

    public InsightApplication() {
        super();
        musicService = new MusicService();
    }*/

    int consumersAmount = 0;
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                consumersAmount++;
                Log.d("TestPish", "onResume");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("TestPish", "paused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
/*                consumersAmount--;
                if (consumersAmount == 0) musicService.pauseMusic();
                Log.d("TestPish", "onStop");*/
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //musicService.destroy();
            }
        });
    }

/*    public void setMusic(final int musicResource) {
        musicService.setMusic(getApplicationContext(), musicResource);
    }

    public void pauseMusic() {
        musicService.pauseMusic();
    }*/


}
