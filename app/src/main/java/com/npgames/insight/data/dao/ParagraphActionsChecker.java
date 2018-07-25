package com.npgames.insight.data.dao;

import android.util.SparseArray;

import com.npgames.insight.data.model.KeyWord;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.ui.book.ActionsCallBack;

import java.util.concurrent.Callable;

public class ParagraphActionsChecker {
    final SparseArray<Callable<Void>> actions = new SparseArray<>();
    private final ActionsCallBack actionsCallBack;
    Player player;

    public ParagraphActionsChecker(final ActionsCallBack actionsCallBack) {
        this.actionsCallBack = actionsCallBack;
        actions.put(500, () -> paragraph37Action());
        actions.put(5, () -> paragraph5Action());
        actions.put(22, () -> paragraph22Action());
        actions.put(32, () -> paragraph32Action());
        actions.put(37, () -> paragraph37Action());
        actions.put(49, () -> paragraph49Action());
        actions.put(59, () -> paragraph59Action());
        actions.put(60, () -> paragraph60Action());
        actions.put(67, () -> paragraph67Action());
        actions.put(75, () -> paragraph75Action());
        actions.put(81, () -> paragraph81Action());
        actions.put(87, () -> paragraph87Action());
        actions.put(97, () -> paragraph97Action());
        actions.put(100, () -> paragraph100Action());
        actions.put(104, () -> paragraph104Action());
        actions.put(116, () -> paragraph116Action());
        actions.put(327, () -> paragraph327Action());
        actions.put(193, () -> paragraph193Action());
    }

    public void applyAction(final int paragraphNumber, final Player player) {
        this.player = player;
        try {
            actions.get(paragraphNumber).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Void paragraph5Action() {
        player.changeStats(Stats.builder()
                .setHp(-6)
                .build());
        return null;
    }

    private Void paragraph22Action() {
        Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }

    private Void paragraph32Action() {
        Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }

    private Void paragraph37Action() {
        actionsCallBack.onDeathAction();
        return null;
    }

    private Void paragraph49Action() {
        actionsCallBack.onAchievementUnlocked(GamePreferences.Achievements.NATURALIST);
        return null;
    }

    private Void paragraph59Action() {
        //TODO: complex logic
        Stats.builder()
                .setHp(-10)
                .build();
        return null;
    }

    private Void paragraph60Action() {
        Stats.builder()
                .setHp(5)
                .build();
        return null;
    }

    private Void paragraph67Action() {
        Stats.builder()
                .setHp(-4)
                .build();
        return null;
    }

    private Void paragraph75Action() {
        Stats.builder()
                .setAmn(-1)
                .build();
        return null;
    }

    private Void paragraph81Action() {
        player.addKeyword(KeyWord.KeyWords.SHINE);
        Stats.builder()
                .build();
        return null;
    }

    private Void paragraph87Action() {
         Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }

    private Void paragraph97Action() {
        Stats.builder()
                .setHp(-2)
                .build();
        return null;
        //TODO:DISABLE POWER SHIELD
    }

    private Void paragraph100Action() {
        Stats.builder()
                .setHp(-2)
                .build();
        //TODO: - TIME if NOT FIRST TIME
        return null;
    }

    private Void paragraph104Action() {
        Stats.builder()
                .setTime(-1)
                .setHp(-4)
                .build();
        return null;
    }

    private Void paragraph116Action() {
        Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }

    private Void paragraph327Action() {
        Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }

    private Void paragraph193Action() {
        Stats.builder()
                .setTime(-1)
                .build();
        return null;
    }
}
