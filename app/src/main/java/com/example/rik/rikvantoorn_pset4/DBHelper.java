package com.example.rik.rikvantoorn_pset4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rik on 21-11-2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myDB.db";
    static final int DATABASE_VERSION = 1;

    public static String TABLE = "toDo";
    public static String _ID = "_id";
    public static String TASK = "task";


    //constructor
    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //onCreate
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    //onUpgrade
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }



}
