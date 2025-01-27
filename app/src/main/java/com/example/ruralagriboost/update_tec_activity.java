package com.example.ruralagriboost;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class update_tec_activity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private ImageView imageViewtec;
    private Button buttonUpdate;
    private Button buttonSelectImage;
    private Button buttonSearch;
    private TextView textViewtecId;

    private DatabaseHelper databaseHelper;
    private byte[] tecImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tec);

        editTextName = findViewById(R.id.edit_text_tec_name);
        imageViewtec = findViewById(R.id.image_view_tec);
        buttonUpdate = findViewById(R.id.button_update);
        buttonSelectImage = findViewById(R.id.button_select_image);
        buttonSearch = findViewById(R.id.button_search);
        textViewtecId = findViewById(R.id.text_view_tec_id);

        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchProduct());
        buttonSelectImage.setOnClickListener(view -> selectImage());
        buttonUpdate.setOnClickListener(view -> updateProduct());
    }

    private void searchProduct() {
        String tecName = editTextName.getText().toString().trim();
        if (tecName.isEmpty()) {
            Toast.makeText(this, "Please enter a technology name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.get_tec_By_Name(tecName);

        if (cursor != null && cursor.moveToFirst())
        {
            int tecId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_IMAGE));

            textViewtecId.setText("Technology ID: " + tecId);

            if (image != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewtec.setImageBitmap(bitmap);
                tecImageByteArray = image;
            }
            cursor.close();
        }
        else
        {
            Toast.makeText(this, "Technology not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageViewtec.setImageBitmap(bitmap);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                tecImageByteArray = byteArrayOutputStream.toByteArray();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProduct() {

        String tecName = editTextName.getText().toString().trim();


        if (tecName.isEmpty())
        {
            Toast.makeText(this, "Please give technology name", Toast.LENGTH_SHORT).show();
            return;
        }



        String productIdText = textViewtecId.getText().toString();
        int productId = Integer.parseInt(productIdText.replaceAll("\\D+", ""));

        databaseHelper.update_tech(productId, tecName, tecImageByteArray);
    }
}