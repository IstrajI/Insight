package com.npgames.insight.domain;

import android.content.Context;

import com.npgames.insight.data.dao.ParagraphJumpsChecker;
import com.npgames.insight.data.dao.ParagraphParser;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.Stats;
import com.npgames.insight.data.model.equipment.Equipment;
import com.npgames.insight.data.model.new_model.Paragraph;
import com.npgames.insight.data.repositories.EquipmentRepository;
import com.npgames.insight.data.repositories.KeyWordsRepository;
import com.npgames.insight.data.repositories.StatsRepository;
import com.npgames.insight.ui.book.Pagination;

import java.util.List;

/**
 * Created by i_straj_i on 28.07.2018.
 */

public class GameInteractor {
    private final StatsRepository statsRepository;
    private final EquipmentRepository equipmentRepository;
    private final KeyWordsRepository keyWordsRepository;

    public GameInteractor(final Context context) {
        statsRepository = StatsRepository.getInstance(context);
        equipmentRepository = EquipmentRepository.getInstance(context);
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

    public Paragraph nextParagraph(final int paragraphNumber, final int availableHeight, final String paragraphString) {
        final List<BlockArea> blockAreas = ParagraphParser.parse(paragraphString);
        final Pagination pagination = new Pagination();
        final Paragraph nextParagraph = pagination.createParagraphModel(blockAreas, availableHeight);

        final Player player = new Player(statsRepository.getStats(),
                keyWordsRepository.getKeyWords(),
                equipmentRepository.getEquipmentsOwnedBy(Equipment.Owner.PLAYER));

        final ParagraphJumpsChecker paragraphJumpsChecker = new ParagraphJumpsChecker();
        paragraphJumpsChecker.checkJumpsConditions(nextParagraph, player);


        return nextParagraph;
    }
}
