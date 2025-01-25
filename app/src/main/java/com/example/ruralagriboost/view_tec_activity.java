package com.example.ruralagriboost;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class view_tec_activity extends AppCompatActivity {
    private ListView listViewtec;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tec);

        listViewtec = findViewById(R.id.list_view_tec);
        Button buttonUpdate = findViewById(R.id.button_update);
        Button buttonDelete = findViewById(R.id.button_delete);
        databaseHelper = new DatabaseHelper(this);

        displaytechnologies();

        buttonUpdate.setOnClickListener(v -> handleUpdate());
        buttonDelete.setOnClickListener(v -> handleDelete());
    }

    @Override
    protected void onResume() {
        super.onResume();
        displaytechnologies();
    }

    private void displaytechnologies() {
        try {
            Cursor cursor = databaseHelper.getAllTechnologies();
            TecAdapter adapter = new TecAdapter(this, cursor, 0);
            listViewtec.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handleUpdate() {
        Intent intent = new Intent(view_tec_activity.this, update_tec_activity.class);
        startActivity(intent);
    }

    private void handleDelete() {
        Intent intent = new Intent(view_tec_activity.this, delete_tec_activity.class);
        startActivity(intent);
        Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show();
    }
}
