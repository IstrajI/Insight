package com.npgames.insight.data.paragraph;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerButtons;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerDex;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerPrc;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.ui.book.Pagination;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ParagraphRepository {
    public static final int FIRST_PARAGRAPH_NUMBER = 500;
    private static ParagraphRepository paragraphRepository;
    private final Resources resources;
    private Paragraph paragraph;
    private final String packageName;
    private final ParagraphPreferences paragraphPreferences;
    private int currentParagraph;
    private Set<String> specialVisitedParagraphs;
    private List<String> watchingParagraphs = Arrays.asList("100", "60", "34");

    private int distributedDexPoints;
    private int distributedPrcPoints;
    private int pointsTodistribute;

    public static ParagraphRepository getInstance(final Context context) {
        if (paragraphRepository == null) {
            paragraphRepository = new ParagraphRepository(context);
        }

        return paragraphRepository;
    }

    ParagraphRepository(final Context context) {
        Log.d("TestPish","constructor");
        resources = context.getResources();
        packageName = context.getPackageName();

        paragraphPreferences = ParagraphPreferences.getInstance(context);
        currentParagraph = paragraphPreferences.loadCurrentParagraphNumber();
        specialVisitedParagraphs = paragraphPreferences.loadSpecialVisitedParagraphs();

        distributedDexPoints = loadDistributedDexPoints();
        distributedPrcPoints = loadDistributedPrcPoints();
        pointsTodistribute = loadPointsToDistribute();
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

        if (paragraphNumber == 501) {
            blockAreas.add(new BlockCreatePlayerDex());
            blockAreas.add(new BlockCreatePlayerPrc());
            blockAreas.add(new BlockCreatePlayerButtons());
        }

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

        final Paragraph loadedParagraph = loadParagraph(paragraphNumber, availableHeight);

        if (loadedParagraph != null) {
            paragraph = loadedParagraph;
            paragraph.wasActionPressed = loadWasActionPressed();
        }

        return loadedParagraph;
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

    public void resetSpecialVisitedParagraphs() {
        specialVisitedParagraphs = Collections.emptySet();
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
    public void saveParagraphNumber() {
        paragraphPreferences.saveCurrentParagraphNumber(paragraph.paragraphNumber);
    }

    private int loadSavedParagraphNumber() {
        return paragraphPreferences.loadCurrentParagraphNumber();
    }

    public void resetParagraphNumber() {
        currentParagraph = FIRST_PARAGRAPH_NUMBER;
    }

    //---------------------------- Create Player ---------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //---------------------------- Dex -------------------------------------------------------------
    public int getDistributedDexPoints() {
        return distributedDexPoints;
    }

    public void updateDistributedDexPoints(final int diff) {
        distributedDexPoints += diff;
    }

    public void saveDistributedDexPoints() {
        paragraphPreferences.saveDistributedDexPoints(distributedDexPoints);
    }

    private int loadDistributedDexPoints() {
        return paragraphPreferences.loadDistributedDexPoints();
    }

    public void resetDistributedDexPoints() {
        Log.d("TestPish", "reset");
        distributedDexPoints = 0;
        //paragraphPreferences.saveDistributedDexPoints(0);
    }

    //---------------------------- PRC -------------------------------------------------------------
    public int getDistributedPrcPoints() {
        return distributedPrcPoints;
    }

    public void updateDistributedPrcPoints(final int diff) {
        distributedPrcPoints += diff;
    }

    public void saveDistributedPrcPoints() {
        paragraphPreferences.saveDistributedPrcPoints(distributedPrcPoints);
    }

    private int loadDistributedPrcPoints() {
        return paragraphPreferences.loadDistributedPrcPoints();
    }

    public void resetDistributedPrcPoints() {
        distributedPrcPoints = 0;
        //paragraphPreferences.saveDistributedPrcPoints(0);
    }

    //---------------------------- D points --------------------------------------------------------
    public int getPointsToDistribute() {
        return pointsTodistribute;
    }

    public void updatePointsToDistribute(final int diff) {
        pointsTodistribute += diff;
    }

    private int loadPointsToDistribute() {
        return paragraphPreferences.loadPointsToDistribute();
    }

    public void savePointsToDistribute() {
        paragraphPreferences.savePointsToDistribute(pointsTodistribute);
    }

    public void resetPointsToDistribute() {
        pointsTodistribute = 4;
        //paragraphPreferences.savePointsToDistribute(4);
    }
}
