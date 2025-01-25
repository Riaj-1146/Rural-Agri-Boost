package com.example.ruralagriboost;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ruralagriboost.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(() -> {
            // Navigate to MainActivity
            Intent intent = new Intent(SplashActivity.this, com.example.ruralagriboost.MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
