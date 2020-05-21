package com.example.lab3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class MakeRecord extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    EditText etName;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_rec);
        etName = (EditText) findViewById(R.id.etName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //текущее московское время
        Date date = new Date();
        String hours = String.format("%tH", date);
        int i = Integer.parseInt(hours)+3;
        hours=Integer.toString(i);
        String minutes = String.format("%tM", date);
        String time = hours+':'+minutes;


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_TIME, time);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;
        }
        dbHelper.close();
    }

}