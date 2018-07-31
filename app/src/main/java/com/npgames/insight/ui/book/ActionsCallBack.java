package com.npgames.insight.ui.book;

import com.npgames.insight.data.db.GamePreferences;

/**
 * Created by i_straj_i on 24.07.2018.
 */

public interface ActionsCallBack {
    void onDeathAction();
    void onAchievementUnlocked(@GamePreferences.Achievements String achievement);
}
