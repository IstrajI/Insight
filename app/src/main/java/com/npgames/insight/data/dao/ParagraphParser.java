package com.npgames.insight.data.dao;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.npgames.insight.data.model.BlockAction;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockButton;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.Jump;
import com.npgames.insight.data.model.Paragraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser {
    private static final int ZERO_PARAGRAPH = 0;

    private static final String RES_ARRAY_TYPE = "array";
    private static final String RES_STRING_TYPE = "string";

    private static final String JUMP_TEXTS_PATTERN = "j%d_t";
    private static final String JUMP_NUMBERS_PATTERN = "j%d_n";
    private static final String PARAGRAPH_TEXT_PATTERN = "p%d_t";
    private static final String ACTIONS_KEYS_PATTERN = "p%d_aKEYS";
    private static final String ACTIONS_VALUES_PATTERN = "p%d_aVALUES";

    public static List<BlockArea> parse(final String paragraphText) {
        final Pattern jumpsPattern = Pattern.compile("\\#(\\d+)\\#");
        final Matcher jumpsMatcher = jumpsPattern.matcher(paragraphText);

        final Pattern actionPattern = Pattern.compile("\\^(\\d+)\\|?(.+)\\^");
        final Matcher actionMatcher = actionPattern.matcher(paragraphText);

        int lastMatchEndPosition = 0;

        final List<BlockArea> paragraphBlocks = new ArrayList<>();
        boolean actionFound = actionMatcher.find();
        boolean jumpsFound = jumpsMatcher.find();


        final boolean test = true || false;

        while (jumpsFound || actionFound) {
            final int matchStartPosition;
            final int matchEndPosition;

            final BlockArea clickBlock;

            if ((jumpsFound && !actionFound)
                    || (jumpsFound && (jumpsMatcher.start() < actionMatcher.start()))) {
                matchStartPosition = jumpsMatcher.start();
                matchEndPosition = jumpsMatcher.end();

                final String clickBlockText = cutExtremalSharps(paragraphText.substring(matchStartPosition, matchEndPosition));
                clickBlock = new BlockButton(clickBlockText);

                jumpsFound = jumpsMatcher.find();
            } else {
                matchStartPosition = actionMatcher.start();
                matchEndPosition = actionMatcher.end();

                final int actionBlockCode = Integer.parseInt(actionMatcher.group(1));

                final String actionBlockText = actionMatcher.group(2);
                Log.d("TestPish", "myCheck = " +actionBlockText);
                clickBlock = new BlockAction(actionBlockText, actionBlockCode);

                actionFound = actionMatcher.find();
            }

            final String substringText = paragraphText.substring(lastMatchEndPosition, matchStartPosition);

            final String textBlockText = cutExtremalSpaces(substringText);
            final BlockText textBlock = new BlockText(textBlockText);
            paragraphBlocks.add(textBlock);
            paragraphBlocks.add(clickBlock);

            lastMatchEndPosition = matchEndPosition;
        }

        final String tail = paragraphText.substring(lastMatchEndPosition, paragraphText.length());

        if (tail.length() != 0) {
            final BlockText textBlock = new BlockText(tail);
            paragraphBlocks.add(textBlock);
        }

        return paragraphBlocks;
    }

    public static String formatParagraphResName(final int paragraphNumber) {
        final Formatter paragraphFormatter = new Formatter();
        return paragraphFormatter.format(PARAGRAPH_TEXT_PATTERN, paragraphNumber).toString();
        //final int paragraphTextId = getResId(context, formatStr, RES_STRING_TYPE, formatter, PARAGRAPH_TEXT_PATTERN, paragraph);

    }

    private static int getResId(final Context context, final StringBuilder formatterString, final String type,  final Formatter formatter, final String pattern, int paragraphNumber) {
        formatter.format(pattern, paragraphNumber);
        final int resId = context.getResources().getIdentifier(formatterString.toString(), type, context.getPackageName());

        formatterString.setLength(0);
        return resId;
    }

    private static String cutExtremalSpaces(final String text) {
        String resultText = text;
        if (text.charAt(0) == ' ') resultText = text.substring(1, text.length());
        if (text.charAt(text.length() - 1) == ' ') resultText = text.substring(0, text.length() - 1);
        return resultText;
    }

    private static String cutExtremalSharps(final String text) {
        String resultText = text;
        resultText = text.substring(1, text.length() - 1);
        return resultText;
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
