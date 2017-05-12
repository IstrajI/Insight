package com.npgames.insight.data.model;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private int id;
    private String text;
    private List<Jump> jumps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Jump> getJumps() {
        return jumps;
    }

    public void setJumps(List<Jump> jumps) {
        this.jumps = jumps;
    }

    public static Paragraph createParagraphFromResources(final int paragraphNumber, final String [] jumpTextsArray, final String [] jumpNumbersArray, final String paragraphText) {
        final int numberOfJumps = (jumpTextsArray.length < jumpNumbersArray.length) ? jumpTextsArray.length : jumpNumbersArray.length;
        final List<Jump> jumps = new ArrayList<>(numberOfJumps);

        for (int i = 0; i < numberOfJumps; i++) {
            final Jump jump = new Jump();
            jump.setId(Integer.parseInt(jumpNumbersArray[i]));
            jump.setText(jumpTextsArray[i]);
            jumps.add(jump);
        }

        final Paragraph paragraph = new Paragraph();
        paragraph.setId(paragraphNumber);
        paragraph.setText(paragraphText);
        paragraph.setJumps(jumps);
        return paragraph;
    }
}
