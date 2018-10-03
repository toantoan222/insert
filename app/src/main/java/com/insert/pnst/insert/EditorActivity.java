package com.insert.pnst.insert;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.insert.pnst.insert.database.DataContract.EmployEntry;
import com.insert.pnst.insert.database.DataDBHelper;
;public class EditorActivity extends AppCompatActivity {
    EditText edit_name,edit_ID,edit_roll;
    Spinner spinner_gender;
    public String mGender=EmployEntry.GENDER_male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_ID=(EditText)findViewById(R.id.edit_ID);
        edit_roll=(EditText)findViewById(R.id.edit_roll);
        spinner_gender=(Spinner)findViewById(R.id.spinner_gender);
        setupspinner();
    }
    public void setupspinner(){
        ArrayAdapter genderspinner=ArrayAdapter.createFromResource(this,R.array.array_gender_options,android.R.layout.simple_dropdown_item_1line);
        spinner_gender.setAdapter(genderspinner);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection=(String)parent.getItemAtPosition(position);
                if(selection.equals("Male")){
                    mGender=EmployEntry.GENDER_male;
                }else mGender=EmployEntry.GENDER_female;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender=EmployEntry.GENDER_male;
            }
        });
    }
    public void insertEmploy(){
        String nameString = edit_name.getText().toString().trim();
        String idString = edit_ID.getText().toString().trim();
        String rollString = edit_roll.getText().toString().trim();
        int id=Integer.parseInt(idString);
        DataDBHelper mDbHelper=new DataDBHelper(this);
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(EmployEntry._ID,id);
        values.put(EmployEntry.COLUMN_EMPLOY_NAME,nameString);
        values.put(EmployEntry.COLUMN_EMPLOY_GENDER,mGender);
        values.put(EmployEntry.COLUMN_EMPLOY_ROLL,rollString);
        long newRowId = db.insert(EmployEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with adding employee", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Employee saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            case R.id.action_save:
                insertEmploy();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
