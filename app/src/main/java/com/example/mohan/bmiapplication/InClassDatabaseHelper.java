package com.example.mohan.bmiapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InClassDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "inclass";  // name of the DB
    private static final int DB_VERSION =1;  // version of the DB
    public static final String TABLE_NAME = "PERSON";  // name of the Table
    public static final String TABLE_BMI = "BMI";

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

        db.execSQL("CREATE TABLE "+TABLE_BMI+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "EMAIL TEXT, "
                + "HEIGHT TEXT, "
                + "WEIGHT TEXT, "
                + "DATE TEXT, "
                + "BMI Text);");



        Date today = new Date();
        ContentValues personValues= new ContentValues();
        personValues.put("NAME", "Mohannad Hameed");
        personValues.put("PASSWORD", "password");
        personValues.put("HEALTH_CARD_NUMB", "1234 5678 9101");
        DateFormat dataFormatter = new SimpleDateFormat("MM-dd-yyyy");
        personValues.put("DATE", dataFormatter.format(today));

        db.insert(TABLE_NAME,null, personValues);


    }
    public  Boolean addUser(String email, String password, String name, String healthCardNo, String date) {


        SQLiteDatabase db = this.getWritableDatabase();
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
    public  void insert_BMI(String email, Double height, Double weight, Double result) {

        SQLiteDatabase db = this.getWritableDatabase();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        String formattedDate = df.format(c);

        ContentValues values = new ContentValues();
        values.put("EMAIL", email);
        values.put("HEIGHT", height);
        values.put("WEIGHT", weight);
        values.put("DATE", formattedDate);
        values.put("BMI", result);

        // Inserting Row
        db.insert(TABLE_BMI, null, values);
        db.close();
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
    public List<BMI> getAllBmiValues(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_BMI, null);


        List<BMI> bmis = new ArrayList<>();
        if (cursor.moveToFirst()){
            BMI bmi = new BMI();

            bmi.email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            bmi.height = cursor.getDouble(cursor.getColumnIndex("HEIGHT"));
            bmi.weight = cursor.getDouble(cursor.getColumnIndex("WEIGHT"));
            bmi.date = cursor.getString(cursor.getColumnIndex("DATE"));
            bmi.bmi = cursor.getDouble(cursor.getColumnIndex("BMI"));
            while(cursor.moveToNext()){

                bmi.email = cursor.getString(cursor.getColumnIndex("EMAIL"));
                bmi.height = cursor.getDouble(cursor.getColumnIndex("HEIGHT"));
                bmi.weight = cursor.getDouble(cursor.getColumnIndex("WEIGHT"));
                bmi.date = cursor.getString(cursor.getColumnIndex("DATE"));
                bmi.bmi = cursor.getDouble(cursor.getColumnIndex("BMI"));
                bmis.add(bmi);
            }
        }
        cursor.close();
        db.close();
        return  bmis;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


