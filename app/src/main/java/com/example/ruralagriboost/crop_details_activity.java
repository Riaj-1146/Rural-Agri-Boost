package com.example.ruralagriboost;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class crop_details_activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CropAdapter cropAdapter;
    private List<Crop> cropList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);

        recyclerView = findViewById(R.id.rv_crop_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cropList = new ArrayList<>();
        cropList.add(new Crop("Paddy", "Paddy is a staple food crop widely cultivated in Asia.", R.drawable.paddy_image));
        cropList.add(new Crop("Barley", "Wheat is a versatile grain used for bread, pasta, and more.", R.drawable.wheat_image));
        cropList.add(new Crop("Corn", "Corn is a cereal grain that is a key ingredient in many products.", R.drawable.corn_image));
        cropList.add(new Crop("Potato", "Potatoes are a root vegetable rich in carbohydrates.", R.drawable.potato_image));
        cropList.add(new Crop("Sugarcane", "Sugarcane is used to produce sugar and ethanol.", R.drawable.sugarcane_image));

        cropAdapter = new CropAdapter(cropList, crop -> {

            Toast.makeText(this, "Clicked on: " + crop.getName(), Toast.LENGTH_SHORT).show();


            Intent intent;
            switch (crop.getName()) {
                case "Paddy":
                    intent = new Intent(crop_details_activity.this, paddy_activity.class);
                    break;
                case "Barley":
                    intent = new Intent(crop_details_activity.this, barley_activity.class);
                    break;
                case "Corn":
                    intent = new Intent(crop_details_activity.this, corn_activity.class);
                    break;
                case "Potato":
                    intent = new Intent(crop_details_activity.this, potato_activity.class);
                    break;
                case "Sugarcane":
                    intent = new Intent(crop_details_activity.this, sugarcane_activity.class);
                    break;
                default:
                    intent = new Intent(crop_details_activity.this, crop_details_activity.class); // A default activity
            }
            startActivity(intent);
        });

        recyclerView.setAdapter(cropAdapter);
    }
}
