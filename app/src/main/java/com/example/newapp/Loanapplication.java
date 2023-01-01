package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Loanapplication extends AppCompatActivity {

    WebView w1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loanapplication);

        w1 = (WebView) findViewById(R.id.web1);
        w1.loadUrl("https://forms.gle/AFcH1XTSarkLbwHHA");

    }
}