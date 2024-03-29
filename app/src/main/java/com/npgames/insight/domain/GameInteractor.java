package com.npgames.insight.domain;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.npgames.insight.application.StringUtills;
import com.npgames.insight.data.game.GameRepository;
import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerButtons;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerDex;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.model.create_player.BlockCreatePlayerPrc;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.keywords.KeyWordsRepository;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import static com.npgames.insight.data.paragraph.ParagraphRepository.FIRST_PARAGRAPH_NUMBER;

public class GameInteractor {
    private final StatsRepository statsRepository;
    private final EquipmentRepository equipmentRepository;
    private final KeyWordsRepository keyWordsRepository;
    private final ParagraphRepository paragraphRepository;
    private final GameRepository gameRepository;
    private final SparseArray<Callable> jumpStateChecker = new SparseArray<>();

    public GameInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
        equipmentRepository = EquipmentRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
        keyWordsRepository = KeyWordsRepository.getInstance(context);
        gameRepository = GameRepository.getInstance(context);

        jumpStateChecker.put(26, this::paragraph26JumpConditions);
        jumpStateChecker.put(32, this::paragraph32JumpConditions);
        jumpStateChecker.put(40, this::paragraph40JumpConditions);
        jumpStateChecker.put(59, this::paragraph59JumpConditions);
        jumpStateChecker.put(67, this::paragraph67JumpConditions);
        jumpStateChecker.put(75, this::paragraph75JumpConditions);
        jumpStateChecker.put(327, this::paragraph327JumpConditions);
    }

    public Paragraph startNewGame(final int availableHeight) {
        clearGameSettings();
        gameRepository.saveContinueGameAvailable(true);
        return nextParagraph(500, availableHeight);
    }

    public void saveGame() {
        equipmentRepository.saveEquipment();
        gameRepository.saveAchievements();
        keyWordsRepository.saveKeyWords();
        paragraphRepository.saveParagraphNumber();
        paragraphRepository.saveSpecialVisitedParagraphs();
        paragraphRepository.saveWasActionPressed();
        statsRepository.saveStats();
    }

    public Paragraph loadSavedParagraph(final int availableHeight) {
        final Paragraph nextParagraph = paragraphRepository.getSavedParagraph(availableHeight);
        checkJumpStatus(nextParagraph);

        return nextParagraph;
    }

    public boolean isContinueGameAvailable() {
        Log.d("TestPish", "currentSavedParagraph = " +paragraphRepository.loadSavedParagraphNumber());
        return (paragraphRepository.loadSavedParagraphNumber() != 500);
    }

    public Paragraph nextParagraph(final int paragraphNumber, final int availableHeight) {
        final Paragraph nextParagraph = paragraphRepository.getNextParagraph(paragraphNumber, availableHeight);
        checkJumpStatus(nextParagraph);

/*        if (paragraphNumber == 501) {
            final List<BlockArea> blockAreas = nextParagraph.getBlockAreas();
            final BlockCreatePlayerDex blockCreatePlayerDex = ((BlockCreatePlayerDex) blockAreas.get(blockAreas.size() - 3));
            final BlockCreatePlayerPrc blockCreatePlayerPrc = ((BlockCreatePlayerPrc) blockAreas.get(blockAreas.size() - 2));
            final BlockCreatePlayerButtons blockCreatePlayerButtons = ((BlockCreatePlayerButtons) blockAreas.get(blockAreas.size() - 1));
            blockCreatePlayerDex.setDexPoints(statsRepository.getStats().getDex());
            blockCreatePlayerPrc.setPrcPoints(statsRepository.getStats().getPrc());
        }*/

        final int MED_BAY_PARAGRAPH = 54;
        if (nextParagraph.paragraphNumber == MED_BAY_PARAGRAPH) {
            for (BlockAction blockAction : nextParagraph.getActions()) {
                final int MAX_HP = 22;
                blockAction.setEnable(statsRepository.getStats().getHp() < MAX_HP);
            }
        }

        final int ARMORY_PARAGRAPH = 100;
        if (nextParagraph.paragraphNumber == ARMORY_PARAGRAPH) {
            boolean isArmoryJumpsEnabled = false;
            final boolean isParagraphVisited = paragraphRepository.isParagraphVisited(nextParagraph.paragraphNumber);

            for (BlockAction blockAction : nextParagraph.getActions()) {
                if (isParagraphVisited) {
                    Log.d("TestPish", "setting true");
                    blockAction.setEnable(true);
                    isArmoryJumpsEnabled = false;
                } else {
                    Log.d("TestPish", "setting false");
                    blockAction.setEnable(false);
                    isArmoryJumpsEnabled = true;
                }
            }

            for (final BlockButton jump: nextParagraph.getJumps()) {
                jump.setEnable(isArmoryJumpsEnabled);
            }
        }

        paragraphRepository.checkSpecialParagraph(nextParagraph.paragraphNumber);

        return nextParagraph;
    }

    public void checkJumpStatus(final Paragraph paragraph) {


        if (paragraph.hasActions() && !paragraph.wasActionPressed
                //A bit of kost'il here
                && !isMedBay(paragraph.paragraphNumber)) {
            Log.d("TestPish", "checkJumpStatus if");
            for (final BlockButton jump: paragraphRepository.getParagraph().getJumps()) {
                jump.setEnable(false);
            }

        } else {
            Log.d("TestPish", "checkJumpStatus else");
            checkParagraphExists();
            checkJumpsConditions();
        }
    }

    private void checkParagraphExists() {
        final Paragraph paragraph = paragraphRepository.getParagraph();

        for (final BlockButton jump: paragraph.getJumps()) {
            Log.d("TestPish", "jump number = " +jump.getParagraphNumber() +" s: " +jump.isEnable());
        }

        for (final BlockButton jump: paragraph.getJumps() ) {
            if (paragraphRepository.getParagraphStringOrEmpty(jump.getParagraphNumber()).equals("")
                    && jump.getParagraphNumber() != 1000) {
                jump.setEnable(false);
            }
        }

        for (final BlockButton jump: paragraph.getJumps()) {
            Log.d("TestPish", "jump number = " +jump.getParagraphNumber() +" s: " +jump.isEnable());
        }
    }

    public boolean isParagraphExists(final int paragraphNumber) {
        return !StringUtills.isEmpty(paragraphRepository.getParagraphStringOrEmpty(paragraphNumber));
    }

    public boolean onDeath() {
        final boolean isDead = statsRepository.getStats().getHp() <= 0;

        if (isDead) {
            statsRepository.resetStats();
            equipmentRepository.resetEquipment();
            keyWordsRepository.resetKeyWords();
            paragraphRepository.resetSpecialVisitedParagraphs();
            paragraphRepository.resetParagraphNumber();
        }

        return isDead;
    }

    public boolean isMedBay(final int paragraphNumber) {
        return paragraphNumber == 54;
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

    public void disableJumps() {
        final List<BlockArea> blockAreas = paragraphRepository.getParagraph().getBlockAreas();

        for (final BlockArea blockArea : blockAreas) {
            if (blockArea.type == BlockArea.BlockType.BUTTON) {
                ((BlockButton) blockArea).setEnable(false);
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

    private Callable paragraph26JumpConditions() {
        if (statsRepository.getStats().getPrc() >= 7) {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }

    private Callable paragraph32JumpConditions() {
        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.OPEN_SPACE_EQUIPMENT, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }

    private Callable paragraph40JumpConditions() {
        if (!paragraphRepository.isParagraphVisited(40)) {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }

    private Callable paragraph59JumpConditions() {
        if (statsRepository.getStats().getDex() >= 7) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        }

        return null;
    }

    private Callable paragraph67JumpConditions() {

        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.BLASTER, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.BEAM, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(2, false);
        }

        //и повершилд активен

        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.POWER_SHIELD, Equipment.Owner.PLAYER) &&
            equipmentRepository.getEquipmentByType(Equipment.TYPE.POWER_SHIELD).getEnabled()) {
            paragraphRepository.changeJumpsButtonStatus(3, false);
        }

        Log.d("TestPishGG", "electroshocker" +equipmentRepository.isOwnedBy(Equipment.TYPE.ELECTROSHOCK, Equipment.Owner.PLAYER));
        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.ELECTROSHOCK, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(4, false);
        }
        return null;
    }

    private Callable paragraph75JumpConditions() {
        if (paragraphRepository.isParagraphVisited(60) || paragraphRepository.isParagraphVisited(34)) {
            paragraphRepository.changeJumpsButtonStatus(1, false);
        } else {
            paragraphRepository.changeJumpsButtonStatus(0, false);
            paragraphRepository.changeJumpsButtonStatus(2, false);
        }

        return null;
    }

    private Callable paragraph327JumpConditions() {
        if (!equipmentRepository.isOwnedBy(Equipment.TYPE.GRENADE, Equipment.Owner.PLAYER)) {
            paragraphRepository.changeJumpsButtonStatus(0, false);
        }

        return null;
    }

    public void clearGameSettings() {
        statsRepository.resetStats();
        equipmentRepository.resetEquipment();
        keyWordsRepository.resetKeyWords();
        paragraphRepository.resetParagraphNumber();
        paragraphRepository.resetSpecialVisitedParagraphs();
        paragraphRepository.resetDistributedDexPoints();
        paragraphRepository.resetDistributedPrcPoints();
        paragraphRepository.resetPointsToDistribute();
        paragraphRepository.resetWasActionPressed();

        gameRepository.saveContinueGameAvailable(false);
    }

    public void removeGrenade() {
        equipmentRepository.getEquipmentByType(Equipment.TYPE.GRENADE).setOwnedBy(Equipment.Owner.TRASH);
    }
}
