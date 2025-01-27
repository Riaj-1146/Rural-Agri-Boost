package com.example.ruralagriboost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        Button btn_insert_tec =findViewById(R.id.btn_insert_tec);
        Button btn_view_tec =findViewById(R.id.btn_view_tec);

        btn_insert_tec.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_home_activity.this, Insert_tec_Activity.class);
            startActivity(intent);
        });

        btn_view_tec.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_home_activity.this, view_tec_activity.class);
            startActivity(intent);
        });


    }
}