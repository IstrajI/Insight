package com.npgames.insight.data.directory;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DirectoryRepository {
    private static DirectoryRepository directoryRepository;
    private final Resources resources;
    private final String packageName;

    private DirectoryRepository(final Context context) {
        this.resources = context.getResources();
        this.packageName = context.getPackageName();
    }

    public static DirectoryRepository getInstance(final Context context) {
        if (directoryRepository == null) {
            directoryRepository = new DirectoryRepository(context);
        }
        return directoryRepository;
    }

    public List<DirectoryItem> getDirectoryList() {
        final List<DirectoryItem> directoryItems = new ArrayList<>();

        int counter = 1;
        Pair<Integer, Integer> directoryResources = getDirectoryResources(counter);
        int directoryTitleResId = directoryResources.first;
        int directoryContentResId = directoryResources.second;

        while (directoryTitleResId != 0 && directoryContentResId != 0) {
            final DirectoryItem directoryItem = new DirectoryItem();

            try {
                directoryItem.title = resources.getString(directoryTitleResId);
                directoryItem.content = resources.getString(directoryContentResId);
                directoryItems.add(directoryItem);
            } catch (Resources.NotFoundException ex) {

            }

            counter++;
            directoryResources = getDirectoryResources(counter);
            directoryTitleResId = directoryResources.first;
            directoryContentResId = directoryResources.second;
        }

        return directoryItems;
    }

    private Pair<Integer, Integer> getDirectoryResources(final int number) {
        final String directoryTitleResName = formatTitleResName(number);
        final String directoryContentResName = formatTitleResContent(number);

        final int directoryTitleResId = resources.getIdentifier(directoryTitleResName, "string", packageName);
        final int directoryContentResId = resources.getIdentifier(directoryContentResName, "string", packageName);

        return new Pair<>(directoryTitleResId, directoryContentResId);
    }

    final String DIRECTORY_TITLE_RES_STRING = "directory_title_";
    final String DIRECTORY_CONTENT_RES_STRING = "directory_description_";

    private String formatTitleResName(final int number) {
        return DIRECTORY_TITLE_RES_STRING + number;
    }

    private String formatTitleResContent(final int number) {
        return DIRECTORY_CONTENT_RES_STRING + number;
    }

}
