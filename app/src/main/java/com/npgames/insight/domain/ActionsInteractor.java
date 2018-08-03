package com.npgames.insight.domain;

import android.content.Context;
import android.util.SparseArray;

import com.npgames.insight.data.game.GamePreferences;
import com.npgames.insight.data.keywords.KeyWordsPreferences;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.Equipment;
import com.npgames.insight.data.equipment.EquipmentRepository;
import com.npgames.insight.data.game.GameRepository;
import com.npgames.insight.data.keywords.KeyWordsRepository;
import com.npgames.insight.data.paragraph.ParagraphRepository;
import com.npgames.insight.data.stats.StatsRepository;

import java.util.concurrent.Callable;

public class ActionsInteractor {
    private final GameRepository gameRepository;
    private final StatsRepository statsRepository;
    private final EquipmentRepository equipmentRepository;
    private final KeyWordsRepository keyWordsRepository;
    private final ParagraphRepository paragraphRepository;

    private final SparseArray<Callable<Void>> actions = new SparseArray<>();

    public ActionsInteractor(final Context context) {
        gameRepository = GameRepository.getInstance(context);
        statsRepository = StatsRepository.getInstance(context);
        equipmentRepository = EquipmentRepository.getInstance(context);
        keyWordsRepository = KeyWordsRepository.getInstance(context);
        paragraphRepository = ParagraphRepository.getInstance(context);

        actions.put(500, this::paragraph37Action);
        actions.put(5, this::paragraph5Action);
        actions.put(22, this::paragraph22Action);
        actions.put(32, this::paragraph32Action);
        actions.put(37, this::paragraph37Action);
        actions.put(49, this::paragraph49Action);
        actions.put(59, this::paragraph59Action);
        actions.put(60, this::paragraph60Action);
        actions.put(67, this::paragraph67Action);
        actions.put(75, this::paragraph75Action);
        actions.put(81, this::paragraph81Action);
        actions.put(87, this::paragraph87Action);
        actions.put(97, this::paragraph97Action);
        actions.put(100, this::paragraph100Action);
        actions.put(104, this::paragraph104Action);
        actions.put(116, this::paragraph116Action);
        actions.put(327, this::paragraph327Action);
        actions.put(193, this::paragraph193Action);
    }

    public void applyAction(final int paragraphNumber) {
        try {
            actions.get(paragraphNumber).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Void paragraph5Action() {
        final Stats changedStats = Stats.builder()
                .setHp(-6)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph22Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph32Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph37Action() {
        final Stats changedStats = Stats.builder()
                .setHp(-50)
                .build();

        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph49Action() {
        gameRepository.addAchievement(GamePreferences.Achievements.NATURALIST);

        return null;
    }

    private Void paragraph54Action() {
        final Stats changedStats = Stats.builder()
                .setHp(5)
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph59Action() {
        //TODO: complex logic
        final Stats changedStats = Stats.builder()
                .setHp(-10)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph60Action() {
        final Stats changedStats = Stats.builder()
                .setHp(5)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph67Action() {
        final Stats changedStats = Stats.builder()
                .setHp(-4)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph75Action() {
        final Stats changedStats = Stats.builder()
                .setAmn(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph81Action() {
        keyWordsRepository.addKeyWord(KeyWordsPreferences.KeyWords.SHINE);

        return null;
    }

    private Void paragraph87Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph97Action() {
        final Stats changedStats = Stats.builder()
                .setHp(-2)
                .build();
        statsRepository.updateStats(changedStats);

        final Equipment powerShield = equipmentRepository.getEquipmentByType(Equipment.TYPE.POWER_SHIELD);

        if (Equipment.Owner.PLAYER.equals(powerShield.getOwnedBy())) {
            powerShield.setEnabled(false);
        }

        return null;
    }

    private Void paragraph100Action() {
        final Stats.Builder changedStats = Stats.builder()
                .setHp(-2);

        if (paragraphRepository.isParagraphVisited(100)) {
            changedStats.setTime(-1);
        }

        statsRepository.updateStats(changedStats.build());
        return null;
    }

    private Void paragraph104Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .setHp(-4)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph116Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph327Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }

    private Void paragraph193Action() {
        final Stats changedStats = Stats.builder()
                .setTime(-1)
                .build();
        statsRepository.updateStats(changedStats);

        return null;
    }
}
