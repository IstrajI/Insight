package com.npgames.insight.data.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class KeyWord {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            KeyWords.SHINE
    })

    public @interface KeyWords {
        public String SHINE = "SHINE";
    }

}
