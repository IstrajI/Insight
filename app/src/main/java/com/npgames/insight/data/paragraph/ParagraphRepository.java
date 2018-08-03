package com.npgames.insight.data.paragraph;

import android.content.Context;
import android.content.res.Resources;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.book.Pagination;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ParagraphRepository {
    private static ParagraphRepository paragraphRepository;
    private final Resources resources;
    private Paragraph paragraph;
    private final String packageName;
    private final ParagraphPreferences paragraphPreferences;
    private int currentParagraph;
    private Set<String> specialVisitedParagraphs;
    private List<String> watchingParagraphs = Arrays.asList("100", "60", "34");

    public static ParagraphRepository getInstance(final Context context) {
        if (paragraphRepository == null) {
            paragraphRepository = new ParagraphRepository(context);
        }

        return paragraphRepository;
    }

    ParagraphRepository(final Context context) {
        resources = context.getResources();
        packageName = context.getPackageName();

        paragraphPreferences = ParagraphPreferences.getInstance(context);
        currentParagraph = paragraphPreferences.loadCurrentParagraphNumber();
        specialVisitedParagraphs = paragraphPreferences.loadSpecialVisitedParagraphs();
    }

    private Paragraph loadParagraph(final int paragraphNumber, final int availableHeight) {
        final String paragraphResName = ParagraphParser.formatParagraphResName(paragraphNumber);
        final int paragraphResId = resources.getIdentifier(paragraphResName, "string", packageName);
        final String paragraphString;

        try {
             paragraphString = resources.getString(paragraphResId);
        } catch (Resources.NotFoundException ex) {
            return null;
        }

        final List<BlockArea> blockAreas = ParagraphParser.parse(paragraphString);
        final Pagination pagination = new Pagination();
        final Paragraph paragraph = pagination.createParagraphModel(blockAreas, availableHeight);
        paragraph.paragraphNumber = paragraphNumber;
        return paragraph;
    }

    public Paragraph getSavedParagraph(final int availableHeight) {
        final int currentParagraphNumber = loadSavedParagraphNumber();
        paragraph = loadParagraph(currentParagraphNumber, availableHeight);
        paragraph.wasActionPressed = loadWasActionPressed();
        return paragraph;
    }

    public Paragraph getNextParagraph(final int paragraphNumber, final int availableHeight) {
        if (isWathcingParagraphs(paragraphNumber)) {
            addSpecialVisitedParagraphs(paragraphNumber);
        }

        paragraph = loadParagraph(paragraphNumber, availableHeight);
        paragraph.wasActionPressed = false;
        return paragraph;
    }

    public void changeJumpsButtonStatus(final int jumpPosition, final boolean isEnabled) {
        paragraph.getJumps().get(jumpPosition).setEnable(isEnabled);
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    //---------------------------- Visited Paragraphs ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public Set<String> getSpecialVisitedParagraphs() {
        return specialVisitedParagraphs;
    }

    public boolean isParagraphVisited(final int paragraphNumber) {
        return specialVisitedParagraphs.contains(String.valueOf(paragraphNumber));
    }

    public void addSpecialVisitedParagraphs(final int paragraphNumber) {
        specialVisitedParagraphs.add(String.valueOf(paragraphNumber));
    }

    public Set<String> loadSpecialVisitedParagraphs() {
        return paragraphPreferences.loadSpecialVisitedParagraphs();
    }

    public void saveSpecialVisitedParagraphs() {
        paragraphPreferences.saveSpecialVisitedParagraphs(specialVisitedParagraphs);
    }

    public boolean isWathcingParagraphs(final int paragraphNumber) {
        return watchingParagraphs.contains(String.valueOf(paragraphNumber));
    }

    //---------------------------- Was Action Pressed ----------------------------------------------
    //----------------------------------------------------------------------------------------------
    private boolean loadWasActionPressed() {
        return paragraphPreferences.loadWasActionPressed();
    }

    public void saveWasActionPressed() {
        paragraphPreferences.saveWasActionPressed(paragraph.wasActionPressed);
    }

    //---------------------------- Current Paragraph -----------------------------------------------
    //----------------------------------------------------------------------------------------------
    public int getParagraphNumber() {
        return currentParagraph;
    }

    public void saveParagraphNumber() {
        paragraphPreferences.saveCurrentParagraphNumber(paragraph.paragraphNumber);
    }

    private int loadSavedParagraphNumber() {
        return paragraphPreferences.loadCurrentParagraphNumber();
    }
}
