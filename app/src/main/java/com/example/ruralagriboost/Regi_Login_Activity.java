package com.example.ruralagriboost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Regi_Login_Activity extends AppCompatActivity {

    private TextView tv_sign_in, tv_Instruction_part1, tv_Instruction_part2;
    private EditText et_email, et_Give_password;
    private Button  bt_login_Button;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    private static final String ADMIN_EMAIL = "riaj@gmail.com";
    private static final String ADMIN_PASSWORD = "Riaj1146";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_login);


        tv_sign_in = findViewById(R.id.tv_sign_in);
        et_email = findViewById(R.id.et_user_email);
        et_Give_password = findViewById(R.id.et_user_password);
        bt_login_Button = findViewById(R.id.bt_Login_Button);
        progressBar = findViewById(R.id.progressBar_login);
        tv_Instruction_part1 = findViewById(R.id.tv_Instruction_part1);
        tv_Instruction_part2 = findViewById(R.id.tv_Instruction_part2);


        mAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.GONE);


        bt_login_Button.setOnClickListener(v -> validateAndDoLogin());


        tv_Instruction_part2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Register_activity.class);
            startActivity(intent);
            finish();
        });
    }

    private void validateAndDoLogin() {
        String email = et_email.getText().toString().trim();
        String password = et_Give_password.getText().toString().trim();


        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]{2,6}$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");


        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Both fields must be filled out!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!emailPattern.matcher(email).matches()) {
            Toast.makeText(this, "Incorrect email address!", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
            return;
        }


        if (!passwordPattern.matcher(password).matches()) {
            Toast.makeText(this, "Password must be at least 6 characters with a number and a letter.", Toast.LENGTH_SHORT).show();
            et_Give_password.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
            progressBar.setVisibility(View.GONE); // Hide ProgressBar
            Intent intent = new Intent(getApplicationContext(), Admin_home_activity.class);
            startActivity(intent);
            finish();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null && user.isEmailVerified()) {
                                Intent intent = new Intent(getApplicationContext(), select_what_you_want.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Regi_Login_Activity.this, "Verify email first", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Regi_Login_Activity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
