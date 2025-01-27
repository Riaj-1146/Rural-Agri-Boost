package com.example.ruralagriboost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "rural_agri_boost.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_TECHNOLOGIES = "technologies";


    public static final String COLUMN_ID = "id";


    public static final String COLUMN_TECH_NAME = "tech_name";
    public static final String COLUMN_TECH_IMAGE = "tech_image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNOLOGIES);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TECHNOLOGIES_TABLE = "CREATE TABLE " + TABLE_TECHNOLOGIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TECH_NAME + " TEXT,"
                + COLUMN_TECH_IMAGE + " BLOB)";
        db.execSQL(CREATE_TECHNOLOGIES_TABLE);
    }


    public void insertTechnology(String techName, byte[] techImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TECH_NAME, techName);
        values.put(COLUMN_TECH_IMAGE, techImage);
        db.insert(TABLE_TECHNOLOGIES, null, values);
        db.close();
    }


    public Cursor getAllTechnologies() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID + " AS _id", COLUMN_TECH_NAME, COLUMN_TECH_IMAGE};
        return db.query(TABLE_TECHNOLOGIES, projection, null, null, null, null, null);
    }

    public Cursor get_tec_By_Name(String tecName) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TECHNOLOGIES + " WHERE " + COLUMN_TECH_NAME + " = ?", new String[]{tecName});
    }

    public void update_tech(int productId, String productName, byte[] productImageByteArray) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TECH_NAME, productName);
        values.put(COLUMN_TECH_IMAGE, productImageByteArray);

        db.update(TABLE_TECHNOLOGIES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    public void delete_tec(String tecName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TECHNOLOGIES, COLUMN_TECH_NAME + " = ?", new String[]{tecName});
        db.close();
    }
}
