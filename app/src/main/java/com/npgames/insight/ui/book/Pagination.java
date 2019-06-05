package com.npgames.insight.ui.book;

import android.text.StaticLayout;
import android.util.Log;

import com.npgames.insight.application.StringUtills;
import com.npgames.insight.data.model.BlockArea;
import com.npgames.insight.data.model.BlockText;
import com.npgames.insight.data.model.new_model.Page;
import com.npgames.insight.data.model.new_model.Paragraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pagination {

    private int pageMaxHeight;

    public Paragraph createParagraphModel(final List<BlockArea> paragraphBlocks, int pageHeight) {
        if (paragraphBlocks.isEmpty()) {
            return new Paragraph(Collections.emptyList());
        }

        pageHeight -=50;
        pageMaxHeight = pageHeight;

        final List<Page> pages = new ArrayList<>();
        pages.add(new Page());

        int currentPageHeight = 0;

        for (final BlockArea block: paragraphBlocks) {
            final int measuredPageHeight = currentPageHeight + block.getViewHeight();
            final boolean isPageHeightExceed = measuredPageHeight >= pageHeight;

            if (isPageHeightExceed) {
                paginateTooLargeBlock(pages, block, pageHeight - currentPageHeight);
                final List<BlockArea> currentPageBlocks = pages.get(pages.size() - 1).blocks;
                currentPageHeight = currentPageBlocks.get(currentPageBlocks.size() - 1).getViewHeight();
            } else {
                final Page currentPage = pages.get(pages.size() - 1);
                currentPage.blocks.add(block);
                currentPageHeight = measuredPageHeight;
            }
        }

        return (new Paragraph(pages));
    }

    private void paginateTooLargeBlock(final List<Page> pages, final BlockArea block, final int remainingHeight) {
        if (block.type == BlockArea.BlockType.TEXT) {
            paginateText(block, pages, remainingHeight);
        } else {
            transferToNewPage(pages, block);
        }
    }

    private void transferToNewPage(final List<Page> pages, final BlockArea block) {
        final Page newPage = new Page();
        newPage.blocks.add(block);
        pages.add(newPage);
    }

    private List<Page> paginateText(final BlockArea textBlock, final List<Page> pages, final int pageHeight) {
        if (textBlock.content.length() == 0) return pages;
        final int lastPageIndex = pages.size() - 1;

        final int he = textBlock.getViewHeight();

        if (he > pageHeight) {
            final String suitedText = calculateRemainedText((BlockText) textBlock, pageHeight);
            pages.get(lastPageIndex).blocks.add(new BlockText(suitedText));

            final int beginIndex = suitedText.length();
            final String nextPageText = textBlock.content.substring(beginIndex);

            if (nextPageText.equals(StringUtills.emptyString)) {
                return pages;
            }

            final BlockText nextPageBlock = new BlockText(nextPageText);
            pages.add(new Page());
            return paginateText(nextPageBlock, pages, pageMaxHeight);
        } else {
            pages.get(lastPageIndex).blocks.add(textBlock);
            return pages;
        }
    }

    public String calculateRemainedText(final BlockText textBlock, final int remainingHeight) {
        final StaticLayout textBlockLayout = textBlock.layout;
        final int lineAmount = textBlockLayout.getLineCount();

        for (int i = 0; i < lineAmount; i++) {
            final int generalLinesHeight = textBlockLayout.getLineBottom(i);

            if (remainingHeight < generalLinesHeight) {
                final String textBlockContent = textBlock.content;
                final String remainedText = textBlockContent.substring(0, textBlockLayout.getLineStart(i));
                return remainedText;
            }
        }

        return StringUtills.emptyString;
    }
}
