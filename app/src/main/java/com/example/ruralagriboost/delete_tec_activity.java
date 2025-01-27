package com.example.ruralagriboost;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete_tec_activity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextName;
    private ImageView imageViewtec;
    private Button buttonDelete;
    private Button buttonSelectImage;
    private Button buttonSearch;
    private TextView textViewtecId;

    private DatabaseHelper databaseHelper;
    private byte[] productImageByteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tec);

        editTextName = findViewById(R.id.text_view_tec_name);
        textViewtecId = findViewById(R.id.text_view_tec_id);
        imageViewtec = findViewById(R.id.image_view_tec);

        buttonDelete = findViewById(R.id.button_delete);
        buttonSearch = findViewById(R.id.button_search);


        databaseHelper = new DatabaseHelper(this);

        buttonSearch.setOnClickListener(view -> searchProduct());
        buttonDelete.setOnClickListener(view -> deleteProduct());
    }

    private void searchProduct()
    {
        String tecName = editTextName.getText().toString().trim();
        if (tecName.isEmpty())
        {
            Toast.makeText(this, "Please enter a technology name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = databaseHelper.get_tec_By_Name(tecName);
        if (cursor != null && cursor.moveToFirst())
        {
            int tecId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_IMAGE));

            textViewtecId.setText("Technology ID: " + tecId);

            if (image != null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imageViewtec.setImageBitmap(bitmap);
                productImageByteArray = image;
            }
            cursor.close();
        }
        else
        {
            Toast.makeText(this, "Technology not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct()
    {
        String tecName = editTextName.getText().toString().trim();

        databaseHelper.delete_tec(tecName);
    }

}