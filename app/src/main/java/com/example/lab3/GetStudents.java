package com.example.lab3;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class GetStudents extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_students);
        DBHelper dbHelper;
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Element> content = new ArrayList<Element>();
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int timeIndex = cursor.getColumnIndex(DBHelper.KEY_TIME);
            do {
                content.add(new Element("ID\n "+cursor.getString(idIndex),"Имя: "+ cursor.getString(nameIndex), "Время:\n"+cursor.getString(timeIndex)));
                /*Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", time = " + cursor.getString(timeIndex));*/
            } while (cursor.moveToNext());
        } else Log.d("mLog","0 rows");
        cursor.close();
        database.close();
        dbHelper.close();
        RecyclerAdapter recAdapter = new RecyclerAdapter(content);
        recyclerView.setAdapter(recAdapter);
    }
}