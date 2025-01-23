package com.example.ruralagriboost;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class agriculture_helpline extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_helpline);
        TextView txt_krisi_helpline = findViewById(R.id.txt_krisi_helpline);
        TextView txt_krisok_bondhu = findViewById(R.id.txt_krisok_bondhu);

    }
}