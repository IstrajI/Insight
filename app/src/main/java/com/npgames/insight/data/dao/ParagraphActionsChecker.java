package com.npgames.insight.data.dao;

import android.util.SparseArray;

import com.npgames.insight.data.model.KeyWord;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.StatsChanger;

import java.util.concurrent.Callable;

public class ParagraphActionsChecker {
    final SparseArray<Callable<Stats>> actions = new SparseArray<>();
    final Player player;

    public ParagraphActionsChecker(final Player player) {
        this.player = player;
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

/*    public StatsChanger checkParagraph(final int paragraphNumber) {
        try {
            statsChanger = actions.get(paragraphNumber).call();
            statsChanger = (statsChanger != null) ? statsChanger : new StatsChanger();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statsChanger;
    }*/

    private Stats paragraph5Action() {
        return Stats.builder()
                .setHp(-6)
                .build();
    }

    private Stats paragraph22Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }

    private Stats paragraph32Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }

    private Stats paragraph37Action() {
        //TODO: death
        return Stats.builder().build();
    }

    private Stats paragraph49Action() {
        //TODO: achievement
        return Stats.builder().build();
    }

    private Stats paragraph59Action() {
        //TODO: complex logic
        return Stats.builder()
                .setHp(-10)
                .build();
    }

    private Stats paragraph60Action() {
        return Stats.builder()
                .setHp(5)
                .build();
    }

    private Stats paragraph67Action() {
        return Stats.builder()
                .setHp(-4)
                .build();
    }

    private Stats paragraph75Action() {
        return Stats.builder()
                .setAmn(-1)
                .build();
    }

    private Stats paragraph81Action() {
        player.addKeyword(KeyWord.KeyWords.SHINE);
        return Stats.builder()
                .build();
    }

    private Stats paragraph87Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }

    private Stats paragraph97Action() {
        return Stats.builder()
                .setHp(-2)
                .build();

        //TODO:DISABLE POWER SHIELD
    }

    private Stats paragraph100Action() {
        return Stats.builder()
                .setHp(-2)
                .build();
        //TODO: - TIME if NOT FIRST TIME
    }

    private Stats paragraph104Action() {
        return Stats.builder()
                .setTime(-1)
                .setHp(-4)
                .build();
    }

    private Stats paragraph116Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }

    private Stats paragraph327Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }

    private Stats paragraph193Action() {
        return Stats.builder()
                .setTime(-1)
                .build();
    }
}
