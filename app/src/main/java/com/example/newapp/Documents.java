package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Documents extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        WebView mywebview = (WebView) findViewById(R.id.web2);
        mywebview.loadUrl("file:///android_asset/fileupload.html");

        b1 = (Button) findViewById(R.id.bt);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Loanapplysuccess.class);
                startActivity(intent);
            }
        });
    }
}