package com.vyke.worldcountries.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.vyke.worldcountries.R;

public class MapActivity extends AppCompatActivity {
    WebView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        String intentName = getIntent().getExtras().getString("Mname");
        String MapAdd = "https://maps.google.com/?q=" + intentName;
        mapView = (WebView) findViewById(R.id.mapWeb_view);
        Toast.makeText(this, "Google Map is Loading", Toast.LENGTH_SHORT).show();
        try {
            WebSettings mapSetting = mapView.getSettings();
            mapSetting.setJavaScriptEnabled(true);
            mapSetting.getBuiltInZoomControls();
            mapView.setWebViewClient(new MapBrowser());
            mapSetting.getLoadsImagesAutomatically();
            mapView.loadUrl(MapAdd);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "opps There issome problem", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.destroy();
        finish();
    }


    private class MapBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request));
            return true;
        }
    }
}
