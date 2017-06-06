package com.npgames.insight.data.dao;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Pair;

import com.npgames.insight.data.model.Jump;
import com.npgames.insight.data.model.Paragraph;
import com.npgames.insight.data.model.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParagraphParser {
    private static final String RES_ARRAY_TYPE = "array";
    private static final String RES_STRING_TYPE = "string";

    private static final String JUMP_TEXTS_PATTERN = "j%d_t";
    private static final String JUMP_NUMBERS_PATTERN = "j%d_n";
    private static final String PARAGRAPH_TEXT_PATTERN = "p%d_t";
    private static final String ACTIONS_KEYS_PATTERN = "p%d_aKEYS";
    private static final String ACTIONS_VALUES_PATTERN = "p%d_aVALUES";

    public static Paragraph parse(final Context context, final int paragraph) {
        final StringBuilder formatStr = new StringBuilder();
        final Formatter formatter = new Formatter(formatStr);

        final int paragraphTextId = getResId(context, formatStr, RES_STRING_TYPE, formatter, PARAGRAPH_TEXT_PATTERN, paragraph);
        final List<Jump> jumps = parseJumps(context, formatStr, formatter, paragraph);
        final Map<Paragraph.ActionTypes, Integer> actions = parseActions(context, formatStr, formatter, paragraph);

        return new Paragraph(paragraph, jumps, paragraphTextId, actions);
    }

    private static int getResId(final Context context, final StringBuilder formatterString, final String type,  final Formatter formatter, final String pattern, int paragraphNumber) {
        formatter.format(pattern, paragraphNumber);
        final int resId = context.getResources().getIdentifier(formatterString.toString(), type, context.getPackageName());
        formatterString.setLength(0);
        return resId;
    }

    private static List<Jump> parseJumps(final Context context,
                                  final StringBuilder formatStr,
                                  final Formatter formatter,
                                  final int paragraph) {
        final int jumpTextsArrayId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter,
                JUMP_TEXTS_PATTERN, paragraph);
        final int jumpNumbersArrayId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter,
                JUMP_NUMBERS_PATTERN, paragraph);

        final String [] jumpTextsArray = context.getResources().getStringArray(jumpTextsArrayId);
        final String [] jumpNumbersArray = context.getResources().getStringArray(jumpNumbersArrayId);

        final List jumps = new ArrayList(jumpNumbersArray.length);
        for (int i = 0; i < jumpNumbersArray.length; i++) {
            final Jump jump = new Jump(jumpNumbersArray[i], jumpTextsArray[i]);
            jumps.add(jump);
        }
        return jumps;
    }

    private static Map<Paragraph.ActionTypes, Integer> parseActions(final Context context,
                                                           final StringBuilder formatStr,
                                                           final Formatter formatter,
                                                           final int paragraph) {
        final Map<Paragraph.ActionTypes, Integer> actions;
        try {
            final int actionsKeysId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter,
                    ACTIONS_KEYS_PATTERN, paragraph);
            final int actionsValuesId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter,
                    ACTIONS_VALUES_PATTERN, paragraph);

            final String [] actionsKeys = context.getResources().getStringArray(actionsKeysId);
            final int [] actionValues = context.getResources().getIntArray(actionsValuesId);

            actions = new HashMap<>(actionsKeys.length);

            for (int i = 0; i < actionsKeys.length; i++) {
                actions.put(Paragraph.ActionTypes.valueOf(actionsKeys[i]), actionValues[i]);
            }
            return actions;
        } catch (Resources.NotFoundException ex) {}
        return new HashMap<>(0);
    }
}
