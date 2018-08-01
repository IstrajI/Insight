package com.npgames.insight.domain;

import android.content.Context;
import android.util.SparseArray;
import com.npgames.insight.data.game.GameRepository;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.keywords.KeyWordsRepository;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;
import java.util.List;
import java.util.concurrent.Callable;

public class GameInteractor {
    private final StatsRepository statsRepository;
    private final EquipmentRepository equipmentRepository;
    private final KeyWordsRepository keyWordsRepository;
    private final ParagraphRepository paragraphRepository;
    private final GameRepository gameRepository;
    private final SparseArray<Callable<Void>> jumpStateChecker = new SparseArray<>();

    final int FIRST_PARAGRAPH_NUMBER = 500;

    public GameInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
        equipmentRepository = EquipmentRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
        keyWordsRepository = KeyWordsRepository.getInstance(context);
        gameRepository = GameRepository.getInstance(context);

        jumpStateChecker.put(26, paragraph26JumpConditions());
        jumpStateChecker.put(59, paragraph59JumpConditions());
        jumpStateChecker.put(32, paragraph32JumpConditions());
        jumpStateChecker.put(67, paragraph67JumpConditions());
        jumpStateChecker.put(327, paragraph327JumpConditions());
    }

    public Paragraph startNewGame(final int availableHeight) {
        statsRepository.resetStats();
        equipmentRepository.resetEquipment();
        keyWordsRepository.resetKeyWords();

        return nextParagraph(FIRST_PARAGRAPH_NUMBER, availableHeight);
    }

    public void saveGame() {
        equipmentRepository.saveEquipment();
        gameRepository.saveAchievements();
        keyWordsRepository.saveKeyWords();
        paragraphRepository.saveParagraphNumber();
        paragraphRepository.saveWasActionPressed();
        statsRepository.saveStats();
    }

    public Paragraph loadSavedParagraph(final int availableHeight) {
        final Paragraph nextParagraph = paragraphRepository.getSavedParagraph(availableHeight);
        checkJumpStatus(nextParagraph);

        return nextParagraph;
    }

    public Paragraph nextParagraph(final int paragraphNumber, final int availableHeight) {
        final Paragraph nextParagraph = paragraphRepository.getNextParagraph(paragraphNumber, availableHeight);
        checkJumpStatus(nextParagraph);

        return nextParagraph;
    }

    private void checkJumpStatus(final Paragraph paragraph) {
        if (paragraph.hasActions() && !paragraph.wasActionPressed) {

            for (final BlockButton jump: paragraphRepository.getParagraph().getJumps()) {
                jump.setEnable(false);
            }

        } else {
            checkJumpsConditions();
        }
    }

    public void enableJumpsDisableActions() {
        final List<BlockArea> blockAreas = paragraphRepository.getParagraph().getBlockAreas();

        for (final BlockArea blockArea : blockAreas) {
            if (blockArea.type == BlockArea.BlockType.ACTION) {
                ((BlockAction) blockArea).setEnable(false);
            }

            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                ((BlockButton) blockArea).setEnable(true);
            }
        }
    }

    //------------------------------- Jumps Checker ------------------------------------------------
    //----------------------------------------------------------------------------------------------

    private void checkJumpsConditions() {
        try {
            jumpStateChecker.get(paragraphRepository.getParagraph().paragraphNumber).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Callable<Void> paragraph26JumpConditions() {
        if (statsRepository.getStats().getPrc() >= 7) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        }

        return null;
    }

    private Callable<Void> paragraph59JumpConditions() {
        if (statsRepository.getStats().getDex() >= 7) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        }

        return null;
    }

    private Callable<Void> paragraph32JumpConditions() {
        if (equipmentRepository.isOwnedBy(Equipment.TYPE.OPEN_SPACE_EQUIPMENT, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }

    private Callable<Void> paragraph67JumpConditions() {

        if (equipmentRepository.isOwnedBy(Equipment.TYPE.BLASTER, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        if (equipmentRepository.isOwnedBy(Equipment.TYPE.BEAM, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        }

        if (equipmentRepository.isOwnedBy(Equipment.TYPE.POWER_SHIELD, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(2, false);
        }

        return null;
    }

    private Callable<Void> paragraph327JumpConditions() {
        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.GRENADE, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }
}
