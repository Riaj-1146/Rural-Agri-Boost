package com.example.ruralagriboost;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TecAdapter extends CursorAdapter
{


    public TecAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_tec_view, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        ImageView tec_ImageView = view.findViewById(R.id.image_view_tec);
        TextView nameTextView = view.findViewById(R.id.text_view_tec_name);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_NAME));
        byte[] imageBytes = cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TECH_IMAGE));


        nameTextView.setText(name);

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        tec_ImageView.setImageBitmap(bitmap);
    }


}























