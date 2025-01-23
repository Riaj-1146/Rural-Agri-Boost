package com.example.ruralagriboost;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback_activity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RatingBar ratingBar;
    private TextView ratingText;
    private EditText feedbackInput;
    private Button submitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        radioGroup = findViewById(R.id.radioGroup);
        ratingBar = findViewById(R.id.ratingBar);
        ratingText = findViewById(R.id.ratingText);
        feedbackInput = findViewById(R.id.feedbackInput);
        submitFeedback = findViewById(R.id.submitFeedback);


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            String selectedOption = selectedRadioButton.getText().toString();
            Toast.makeText(Feedback_activity.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
        });


        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingText.setText("Rating: " + rating);
        });


        submitFeedback.setOnClickListener(v -> {
            String feedback = feedbackInput.getText().toString().trim();
            float rating = ratingBar.getRating();

            if (feedback.isEmpty()) {
                Toast.makeText(this, "Please provide your feedback.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(
                        this,
                        "Thank you for your valuable response!\nRating: " + rating,
                        Toast.LENGTH_SHORT
                ).show();
                feedbackInput.setText("");
                ratingBar.setRating(0);
            }
        });
    }
}
