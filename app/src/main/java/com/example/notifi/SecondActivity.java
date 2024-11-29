package com.example.notifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        t= findViewById(R.id.getmsg);
        Intent intent = getIntent();
        String msg=intent.getStringExtra("message");
        String fmsg="The message is : "+msg;
        t.setText(fmsg);
    }
}