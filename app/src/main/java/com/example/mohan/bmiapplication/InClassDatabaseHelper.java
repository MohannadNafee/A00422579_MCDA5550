package com.example.mohan.bmiapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InClassDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "inclass";  // name of the DB
    private static final int DB_VERSION =1;  // version of the DB
    public static final String TABLE_NAME = "PERSON";  // name of the Table

    public InClassDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);  //   null is for cursors
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EMAIL TEXT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT, "
                + "HEALTH_CARD_NUMB TEXT, "
                + "DATE Text);");




        Date today = new Date();
        ContentValues personValues= new ContentValues();
        personValues.put("NAME", "Mohannad Hameed");
        personValues.put("PASSWORD", "password");
        personValues.put("HEALTH_CARD_NUMB", "1234 5678 9101");
        DateFormat dataFormatter = new SimpleDateFormat("MM-dd-yyyy");
        personValues.put("DATE", dataFormatter.format(today));

        db.insert(TABLE_NAME,null, personValues);

        db.close();
    }
    public  Boolean addUser(String email, String password, String name, String healthCardNo, String date) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                "EMAIL"
        };
        String selection =   "EMAIL = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();

        if (cursorCount > 0) {
            return false;
        }

        ContentValues values = new ContentValues();
        values.put("EMAIL", email);
        values.put("PASSWORD", password);
        values.put("NAME", name);
        values.put("HEALTH_CARD_NUMB", healthCardNo);
        values.put("DATE", date);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close();
        return  true;
    }
    public  boolean checkUser(String email, String password) {
        String[] columns = {
                "EMAIL", "PASSWORD"
        };

        SQLiteDatabase db = this.getWritableDatabase();

        String selection =  "EMAIL = ? AND PASSWORD = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


