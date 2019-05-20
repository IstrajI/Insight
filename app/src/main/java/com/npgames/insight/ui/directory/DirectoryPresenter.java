package com.npgames.insight.ui.directory;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.npgames.insight.data.directory.DirectoryRepository;

@InjectViewState
public class DirectoryPresenter extends MvpPresenter<DirectoryView> {
    DirectoryRepository directoryRepository;

    DirectoryPresenter(final Context context) {
        directoryRepository = DirectoryRepository.getInstance(context);
    }

    public void loadDirectory() {
        getViewState().showDirectory(directoryRepository.getDirectoryList());
    }
}
