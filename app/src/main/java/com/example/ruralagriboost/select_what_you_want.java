package com.example.ruralagriboost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class select_what_you_want extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_what_you_want);

        ImageView img_rural_agri_boost = findViewById(R.id.img_rural_agri_boost);
        TextView tv_select = findViewById(R.id.tv_select);

        TextView tv_feedback = findViewById(R.id.tv_feedback);
        TextView tv_logout = findViewById(R.id.tv_logout);


        Button bt_crop = findViewById(R.id.bt_crop);
        Button bt_agri_tec = findViewById(R.id.bt_agri_tec);
        Button bt_agri_helpline = findViewById(R.id.bt_agri_helpline);


        tv_feedback.setOnClickListener(v -> {

            Intent intent = new Intent(select_what_you_want.this, Feedback_activity.class);
            startActivity(intent);

        });

        tv_logout.setOnClickListener(v -> {

            Intent intent = new Intent(select_what_you_want.this, MainActivity.class);
            finish();
            startActivity(intent);

        });


        bt_crop.setOnClickListener(v -> {

            Intent intent = new Intent(select_what_you_want.this,crop_details_activity.class);
            startActivity(intent);

        });


        bt_agri_tec.setOnClickListener(v -> {

            Intent intent = new Intent(select_what_you_want.this,agri_tec_activity.class);
            startActivity(intent);

        });


        bt_agri_helpline.setOnClickListener(v -> {

            Intent intent = new Intent(select_what_you_want.this,agriculture_helpline.class);
            startActivity(intent);

        });

    }
}