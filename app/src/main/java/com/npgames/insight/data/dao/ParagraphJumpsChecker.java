package com.npgames.insight.data.dao;

import android.util.SparseArray;

import com.npgames.insight.data.model.Player;
import com.npgames.insight.data.model.equipment.Beam;
import com.npgames.insight.data.model.equipment.Blaster;
import com.npgames.insight.data.model.equipment.Grenade_1;
import com.npgames.insight.data.model.equipment.Grenade_2;
import com.npgames.insight.data.model.equipment.Grenade_3;
import com.npgames.insight.data.model.equipment.OpenSpaceEqpt;
import com.npgames.insight.data.model.equipment.PowerShield;
import com.npgames.insight.data.model.new_model.Paragraph;

import java.util.function.Function;

public class ParagraphJumpsChecker {
    private final SparseArray<Function<Paragraph, Void>> jumpStateChecker = new SparseArray<>();

    public void checkJumpsConditions(final Paragraph paragraph, final Player player) {

        switch(paragraph.getId()) {
            case 26:
                paragraph26JumpConditions(paragraph);
                break;
            case 32:
                paragraph32JumpConditions(paragraph);
                break;
            case 59:
                paragraph59JumpConditions(paragraph);
                break;
            case 67:
                paragraph67JumpConditions(paragraph);
                break;
            case 327:
                paragraph327JumpConditions(paragraph);
                break;
        }
    }

    private Void paragraph26JumpConditions(final Paragraph paragraph) {
        if (player.getStats().getPrc() >= 7) {
            paragraph.getJumps().get(0).setStatus(false);
            return;
        }
        paragraph.getJumps().get(1).setStatus(false);
    }

    private void paragraph59JumpConditions(final Paragraph paragraph) {
        if (player.getStats().getDex() >= 7) {
            paragraph.getJumps().get(0).setStatus(false);
            return;
        }
        paragraph.getJumps().get(1).setStatus(false);
    }

    private void paragraph32JumpConditions(final Paragraph paragraph) {
        if (!player.isOwnerOf(OpenSpaceEqpt.SHARED_PROPERTY_NAME)) {
            paragraph.getJumps().get(0).setStatus(false);
        }
    }

    private void paragraph67JumpConditions(final Paragraph paragraph) {
        if (!player.isOwnerOf(Blaster.SHARED_PROPERTY_NAME)) {
            paragraph.getJumps().get(0).setStatus(false);
        }
        if (!player.isOwnerOf(Beam.SHARED_PROPERTY_NAME)) {
            paragraph.getJumps().get(1).setStatus(false);
        }
        if (!player.isOwnerOf(PowerShield.SHARED_PROPERTY_NAME)) {
            paragraph.getJumps().get(2).setStatus(false);
        }
    }

    private void paragraph327JumpConditions(final Paragraph paragraph) {
        if (!player.isOwnerOf(Grenade_1.SHARED_PROPERTY_NAME) &&
                !player.isOwnerOf(Grenade_2.SHARED_PROPERTY_NAME) &&
                !player.isOwnerOf(Grenade_3.SHARED_PROPERTY_NAME)) {
            paragraph.getJumps().get(0).setStatus(false);
        }
    }
}
