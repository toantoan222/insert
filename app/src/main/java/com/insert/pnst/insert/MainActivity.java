package com.insert.pnst.insert;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.insert.pnst.insert.database.DataContract.EmployEntry;
import com.insert.pnst.insert.database.DataDBHelper;
public class MainActivity extends AppCompatActivity {
    public DataDBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper=new DataDBHelper(this);    }
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }
    public void displayDatabaseInfo(){
        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        String[] projection={EmployEntry._ID,EmployEntry.TABLE_NAME,EmployEntry.COLUMN_EMPLOY_GENDER,EmployEntry.COLUMN_EMPLOY_ROLL};
        Cursor cursor=db.rawQuery("SELECT * FROM "+EmployEntry.TABLE_NAME,null);
        TextView display=(TextView)findViewById(R.id.display);
        try{
            display.setText("Có "+cursor.getCount()+" nhân viên trong danh sách.\n\n");
            int idColumnIndex = cursor.getColumnIndex(EmployEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(EmployEntry.COLUMN_EMPLOY_NAME);
            int genderColumnIndex = cursor.getColumnIndex(EmployEntry.COLUMN_EMPLOY_GENDER);
            int rollColumnIndex = cursor.getColumnIndex(EmployEntry.COLUMN_EMPLOY_ROLL);
            while(cursor.moveToNext()){
                int currentID=cursor.getInt(idColumnIndex);
                String currentName=cursor.getString(nameColumnIndex);
                String currentGender=cursor.getString(genderColumnIndex);
                String currentRoll=cursor.getString(rollColumnIndex);
                display.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentGender + " - " +
                        currentRoll));
            }
        }finally {
            cursor.close();
        }
    }
}
