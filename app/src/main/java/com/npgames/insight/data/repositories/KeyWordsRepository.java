package com.npgames.insight.data.repositories;

import android.content.Context;

import com.npgames.insight.data.db.KeyWordsPreferences;

import java.util.Set;

public class KeyWordsRepository {
    private static KeyWordsRepository keyWordsRepository;
    private KeyWordsPreferences keyWordsPreferences;
    private Set<String> keyWords;

    public static KeyWordsRepository getInstance(final Context context) {
        if (keyWordsRepository == null) {
            keyWordsRepository = new KeyWordsRepository(context);
        }

        return keyWordsRepository;
    }

    KeyWordsRepository(final Context context) {
        keyWordsPreferences = KeyWordsPreferences.getInstance(context);
        keyWords = keyWordsPreferences.loadKeyWords();
    }

    public Set<String> getKeyWords() {
        return keyWords;
    }

    public void addKeyWord(final @KeyWordsPreferences.KeyWords String keyWord) {
        keyWords.add(keyWord);
    }
}
