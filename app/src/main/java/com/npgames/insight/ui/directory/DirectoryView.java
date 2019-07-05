package com.npgames.insight.ui.directory;

import com.arellomobile.mvp.MvpView;
import com.npgames.insight.data.directory.DirectoryItem;

import java.util.List;

public interface DirectoryView extends MvpView {
    void showDirectory(List<DirectoryItem> directoryItem);
}
