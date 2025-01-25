package com.example.ruralagriboost;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_startButton = findViewById(R.id.bt_startButton);

        bt_startButton.setOnClickListener(v -> {


            LayoutInflater inflater = getLayoutInflater();

            View customToastView = inflater.inflate(R.layout.custom_toast, findViewById(android.R.id.content), false);


            ImageView toastImage = customToastView.findViewById(R.id.toast_image);
            TextView toastText = customToastView.findViewById(R.id.toast_text);


            toastText.setText("Welcome to Rural_Agri_Boost");


            Toast customToast = new Toast(MainActivity.this);
            customToast.setDuration(Toast.LENGTH_SHORT);
            customToast.setView(customToastView);
            customToast.show();


            Intent intent = new Intent(MainActivity.this, Regi_Login_Activity.class);
            startActivity(intent);
            finish();
        });
    }
}
