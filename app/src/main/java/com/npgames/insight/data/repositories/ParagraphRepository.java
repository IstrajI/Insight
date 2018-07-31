package com.npgames.insight.data.repositories;

import android.content.Context;
import android.content.res.Resources;

import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.db.KeyWordsPreferences;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.book.Pagination;

import java.util.List;
import java.util.Set;

public class ParagraphRepository {
    private static ParagraphRepository paragraphRepository;
    private final Resources resources;
    private Paragraph paragraph;
    private final String packageName;

    public static ParagraphRepository getInstance(final Context context) {
        if (paragraphRepository == null) {
            paragraphRepository = new ParagraphRepository(context);
        }

        return paragraphRepository;
    }

    ParagraphRepository(final Context context) {
        resources = context.getResources();
        packageName = context.getPackageName();
    }

    public Paragraph getNextParagraph(final int paragraphNumber, final int availableHeight) {
        final String paragraphResName = ParagraphParser.formatParagraphResName(paragraphNumber);
        final int paragraphResId = resources.getIdentifier(paragraphResName, "string", packageName);
        final String paragraphString = resources.getString(paragraphResId);

        final List<BlockArea> blockAreas = ParagraphParser.parse(paragraphString);
        final Pagination pagination = new Pagination();
        paragraph = pagination.createParagraphModel(blockAreas, availableHeight);
        paragraph.paragraphNumber = paragraphNumber;
        return paragraph;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void changeJumpsButtonStatus(final int jumpPosition, final boolean isEnabled) {
        paragraph.getJumps().get(jumpPosition).setEnable(isEnabled);
    }
}
