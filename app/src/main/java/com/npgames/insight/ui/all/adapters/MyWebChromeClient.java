package com.npgames.insight.ui.all.adapters;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MyWebChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);

        if (newProgress == 100) {
            Log.d("number of pages", ""+calculateNoOfPages());

            }, 200);
        }
    }

    private int calculateNoOfPages(WebView view) {
            if (view.getMeasuredWidth() != 0) {
                int newPageCount = computeHorizontalScrollRange() / view.getMeasuredWidth();
                return newPageCount;
            }
            return 0;

    }

    @Override
    public int computeHorizontalScrollRange() {
        // TODO Auto-generated method stub
        return super.computeHorizontalScrollRange();
    }
}