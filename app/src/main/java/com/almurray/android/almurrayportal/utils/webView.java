package com.almurray.android.almurrayportal.utils;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.almurray.android.almurrayportal.R;

public class webView extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    String webURL = extras.getString("url","https://garyportal.xyz");
                    webView = findViewById(R.id.webViewer);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                    webView.getSettings().setSupportMultipleWindows(true);
                    webView.loadUrl(webURL);
                }
                else {
                    finish();
                }
            }
        };
        handler.post(runnable);
    }
}
