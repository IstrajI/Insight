package com.npgames.insight.data.dao;
import android.content.Context;
import android.util.Log;

import com.npgames.insight.data.model.Paragraph;

import java.util.Formatter;

public class ParagraphParser {
    private static final String RES_ARRAY_TYPE = "array";
    private static final String RES_STRING_TYPE = "string";

    private static final String JUMP_TEXTS_PATTERN = "jump%d_texts";
    private static final String JUMP_NUMBERS_PATTERN = "jump%d_numbers";
    private static final String PARAGRAPH_TEXT_PATTERN = "paragraph%d_texts";

    public static Paragraph parse(final Context context, final int paragraph) {
        StringBuilder formatStr = new StringBuilder();
        Formatter formatter = new Formatter(formatStr);

        final int jumpTextsArrayId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter, JUMP_TEXTS_PATTERN, paragraph);
        final int jumpNumbersArrayId = getResId(context, formatStr, RES_ARRAY_TYPE, formatter, JUMP_NUMBERS_PATTERN, paragraph);
        final int paragraphTextId = getResId(context, formatStr, RES_STRING_TYPE, formatter, PARAGRAPH_TEXT_PATTERN, paragraph);
        Log.d("text id", ""+paragraphTextId);

        final String [] jumpTextsArray = context.getResources().getStringArray(jumpTextsArrayId);
        final String [] jumpNumbersArray = context.getResources().getStringArray(jumpNumbersArrayId);
        final String paragraphText = context.getResources().getString(paragraphTextId);

        Log.d("text", ""+paragraphText);

        return Paragraph.createParagraphFromResources(paragraph, jumpTextsArray, jumpNumbersArray, paragraphText);
    }

    private static int getResId(final Context context, final StringBuilder formatterString, final String type,  final Formatter formatter, final String pattern, int paragraphNumber) {
        formatter.format(pattern, paragraphNumber);
        Log.d("string ", ""+formatterString);
        final int resId = context.getResources().getIdentifier(formatterString.toString(), type, context.getPackageName());
        formatterString.setLength(0);
        return resId;
    }


}
