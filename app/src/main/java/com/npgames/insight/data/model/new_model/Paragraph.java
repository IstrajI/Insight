package com.npgames.insight.data.model.new_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 05.11.2017.
 */

public class Paragraph {
    public final List<Page> pages;

    public Paragraph() {
        pages = new ArrayList<>();
    }

    public Paragraph(final List<Page> pages) {
        this.pages = pages;
    }
}
