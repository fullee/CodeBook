package com.icngor.codebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class ReadBookBrowser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String pagesUrl = intent.getStringExtra("pagesUrl");
        WebView webView = new WebView(ReadBookBrowser.this);
        webView.loadUrl(pagesUrl);
        setContentView(webView);
    }
}
