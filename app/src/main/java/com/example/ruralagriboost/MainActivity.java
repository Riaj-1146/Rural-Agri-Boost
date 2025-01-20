package com.example.ruralagriboost;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img_greetingImageView=findViewById(R.id.img_greetingImageView);
        TextView txt_greetingTextView = findViewById(R.id.txt_greetingTextView);
        TextView txt_greetingTextView1 = findViewById(R.id.txt_greetingTextView1);
        Button bt_startButton = findViewById(R.id.bt_startButton);

        bt_startButton.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,Regi_Login_Activity.class);
            startActivity(intent);
            finish();

        });

    }
}