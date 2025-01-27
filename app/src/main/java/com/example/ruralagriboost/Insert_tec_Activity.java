package com.example.ruralagriboost;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Insert_tec_Activity extends AppCompatActivity {

    private ImageView img_image;
    private EditText et_tech_name;
    private Button bt_choose_img;
    private Button bt_inset_img;
    private DatabaseHelper databaseHelper;
    private byte[] imageByteArray;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tec);

        img_image = findViewById(R.id.img_image);
        et_tech_name = findViewById(R.id.et_tech_name);
        bt_choose_img = findViewById(R.id.bt_choose_img);
        bt_inset_img = findViewById(R.id.bt_inset_img);
        databaseHelper = new DatabaseHelper(this);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            img_image.setImageBitmap(imageBitmap);
                            imageByteArray = bitmapToByteArray(imageBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        bt_choose_img.setOnClickListener(view -> chooseImage());
        bt_inset_img.setOnClickListener(view -> insertTechnology());
    }

    private void chooseImage() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        imagePickerLauncher.launch(pickIntent);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void insertTechnology() {
        String name = et_tech_name.getText().toString();

        if (name.isEmpty() || imageByteArray == null) {
            Toast.makeText(this, "Please fill all fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseHelper.insertTechnology(name, imageByteArray);
        Toast.makeText(Insert_tec_Activity.this, "Insertion successful!", Toast.LENGTH_SHORT).show();
    }
}
