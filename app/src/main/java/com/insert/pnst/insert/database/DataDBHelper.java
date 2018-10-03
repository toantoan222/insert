package com.insert.pnst.insert.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.insert.pnst.insert.database.DataContract.EmployEntry;

public class DataDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DataDBHelper.class.getSimpleName();
    public static final String DATABASE_NAME="database.db";
    public static final int DATABASE_VERSION=1;
    public DataDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EMPLOY_TABLE =  "CREATE TABLE " + EmployEntry.TABLE_NAME + " ("
                + EmployEntry._ID + " INTEGER PRIMARY KEY, "
                + EmployEntry.COLUMN_EMPLOY_NAME + " TEXT NOT NULL, "
                + EmployEntry.COLUMN_EMPLOY_GENDER + " INTERGER NOT NULL, "
                + EmployEntry.COLUMN_EMPLOY_ROLL + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_EMPLOY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_EMPLOY_TABLE="DROP TABLE IF EXISTS "+EmployEntry.TABLE_NAME+";";
        db.execSQL(SQL_DELETE_EMPLOY_TABLE);
    }
}
