package com.example.rik.rikvantoorn_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rik on 22-11-2016.
 */

public class DBManager {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBManager(Context c) {
        context = c;
    }

    public void insert(String task) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TASK, task);
        db.insert(DBHelper.TABLE, null, contentValue);
    }

    public Cursor read() {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        String[] columns = new String[] { DBHelper._ID, DBHelper.TASK};
        Cursor cursor = db.query(DBHelper.TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TASK, task);
        int i = db.update(DBHelper.TABLE, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        String string =String.valueOf(_id);
        db.delete(DBHelper.TABLE, DBHelper._ID + "=" + string, null);
    }
}
