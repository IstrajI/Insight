package com.npgames.insight.ui.book;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.npgames.insight.R;
import com.npgames.insight.ui.all.activities.BaseMvpActivity;

import butterknife.BindView;

/**
 * Created by Влад on 03.08.2017.
 */

public class GameBookActivity2 extends BaseMvpActivity {
    @BindView(R.id.webview_product_details)
    WebView pishWebView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamebook2);
    }

    @Override
    protected void bindViews() {
        initVebView(pishWebView, "olologgg <b>sdasd</b> dsdddddsa sad dsa dsadsad dsad dsad dsad dsadas dsadasd");
    }

    void initVebView(WebView wvContent_Detail, String body) {
        String HTML1 = "<html><head><style type=\"text/css\">@font-face      {font-family: MyFont;src: url(\"file:///android_asset/%s\")}body {font-family: MyFont;font-size: medium;text-align: justify; line-height: %spx;} " +
                "   body{\n" +
                "    background-color: #3366CC; /* Цвет фона веб-страницы */\n" +
                "   } " +
                "</style></head><body dir='rtl'>";
        String HTML2 = "</body></html>";
        String HTML3 = "<span style=\"font-weight:bold\">%s<br/><br/>%s</span>";

        String HTML4 = "<style>img{display: inline;height: auto;max-width: 100%;</style>";

        String str = String.format(HTML1, "IRANSansMobile_UltraLight.ttf", 25);


        String myHtmlString = str + body + HTML2;

        wvContent_Detail.loadDataWithBaseURL(null, HTML4 + myHtmlString, "text/html", "UTF-8", null);

        WebSettings webSettings = wvContent_Detail.getSettings();
        webSettings.setDefaultFontSize(20);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Log.d("dif", "dif");
        Log.d("pish", wvContent_Detail.getTitle());
    }
}
