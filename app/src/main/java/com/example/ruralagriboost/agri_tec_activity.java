package com.example.ruralagriboost;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agri_tec_activity extends AppCompatActivity {

    private ListView listViewtec;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agri_tec);

        listViewtec = findViewById(R.id.list_view_tec);
        databaseHelper = new DatabaseHelper(this);

        displaytechnologies();

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



}