package com.npgames.insight.data.model;

import com.npgames.insight.data.model.equipment.Equipment;

import java.util.List;
import java.util.Set;

public class Player {
    public static final int INIT_HP = 16;
    public static final int INIT_AUR = 2;
    public static final int INIT_DEX = 10;
    public static final int INIT_PRC = 3;
    public static final int INIT_TIME = 30;
    public static final int INIT_AMN = 8;

    private int[] visittedParagraphs;
    private int paragraph;

    private final Stats stats;
    private final List<Equipment> equipments;
    private final Set<String> keywords;

    public Player(final Stats playerStats, final Set<String> playerKeywords, final List<Equipment> playerEquipments) {
        stats = playerStats;
        keywords = playerKeywords;
        equipments = playerEquipments;
    }
}
