package com.npgames.insight.ui.all.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebClient extends WebViewClient {
    final Context context;
    public MyWebClient(final Context context) {
        this.context = context;
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {

        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        final WebView myWebView = (WebView) view;


        String varMySheet = "var mySheet = document.styleSheets[0];";

        String addCSSRule = "function addCSSRule(selector, newRule) {"
                + "ruleIndex = mySheet.cssRules.length;"
                + "mySheet.insertRule(selector + '{' + newRule + ';}', ruleIndex);"

                + "}";

        String insertRule1 = "addCSSRule('html', 'padding: 0px; height: "
                + (myWebView.getMeasuredHeight() / context.getResources().getDisplayMetrics().density)
                + "px; -webkit-column-gap: 0px; -webkit-column-width: "
                + myWebView.getMeasuredWidth() + "px;')";


        myWebView.loadUrl("javascript:" + varMySheet);
        myWebView.loadUrl("javascript:" + addCSSRule);
        myWebView.loadUrl("javascript:" + insertRule1);


    }

}


