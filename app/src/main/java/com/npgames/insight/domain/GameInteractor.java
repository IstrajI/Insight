package com.npgames.insight.domain;

import android.content.Context;
import android.util.SparseArray;

import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.repositories.EquipmentRepository;
import com.npgames.insight.data.repositories.KeyWordsRepository;
import com.npgames.insight.data.repositories.ParagraphRepository;
import com.npgames.insight.data.repositories.StatsRepository;

import java.util.List;
import java.util.concurrent.Callable;

public class GameInteractor {
    private final StatsRepository statsRepository;
    private final EquipmentRepository equipmentRepository;
    private final KeyWordsRepository keyWordsRepository;
    private final ParagraphRepository paragraphRepository;
    private final SparseArray<Callable<Void>> jumpStateChecker = new SparseArray<>();

    public GameInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
        equipmentRepository = EquipmentRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);
        keyWordsRepository = KeyWordsRepository.getInstance(context);

        jumpStateChecker.put(26, paragraph26JumpConditions());
        jumpStateChecker.put(59, paragraph59JumpConditions());
        jumpStateChecker.put(32, paragraph32JumpConditions());
        jumpStateChecker.put(67, paragraph67JumpConditions());
        jumpStateChecker.put(327, paragraph327JumpConditions());
    }

    public void startNewGame() {
        //reset stats
        final Stats stats = Stats.builder()
                .setAmn(Player.INIT_AMN)
                .setTime(Player.INIT_TIME)
                .setDex(Player.INIT_DEX)
                .setAur(Player.INIT_AUR)
                .setPrc(Player.INIT_PRC)
                .setHp(Player.INIT_HP)
                .build();

        statsRepository.setStats(stats);

        //reset equipment

        //reset keywords

        //reset paragraph
    }

    public Paragraph nextParagraph(final int paragraphNumber, final int availableHeight) {
        final Paragraph nextParagraph = paragraphRepository.getNextParagraph(paragraphNumber, availableHeight);

        if (nextParagraph.hasActions()) {
            disableJumps();
        } else {
            checkJumpsConditions();
        }

        return nextParagraph;
    }

    private void disableJumps() {
        for (final BlockButton jump: paragraphRepository.getParagraph().getJumps()) {
            jump.setEnable(false);
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
