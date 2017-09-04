package com.android.vyke.worldcountries;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WikiWeb extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_web);

        String Item = getIntent().getExtras().getString("Cname");
        String webadd = "https://en.m.wikipedia.org/wiki/" + Item;

        webView = (WebView) findViewById(R.id.wikiweb);
        Toast.makeText(this, "Wikipedia Loading", Toast.LENGTH_SHORT).show();
        try {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadsImagesAutomatically(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.loadUrl(webadd);
            webView.setWebViewClient(new WikiBrowser());
        } catch (Exception e) {
            Toast.makeText(WikiWeb.this, "Cannot connect to Wiki", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        webView.destroy();
        finish();
    }

    private class WikiBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request));
            return true;
        }
    }

}
