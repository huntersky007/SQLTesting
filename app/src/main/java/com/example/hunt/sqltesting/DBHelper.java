package com.example.hunt.sqltesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Hunt on 2015-01-16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLTest.db";
    public static final String PEOPLE_TABLE_NAME = "people";
    public static final String PEOPLE_COLUMN_ID = "id";
    public static final String PEOPLE_COLUMN_NAME = "name";
    public static final String PEOPLE_COLUMN_SURNAME = "surname";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table people " + "(id integer primary key, name text, surname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS people");
        onCreate(db);
    }

    public boolean insertPerson (String name, String surname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("surname",surname);

        db.insert("people",null,contentValues);
        return true;
    }

    public Cursor getData(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from people where id="+id+"",null);
        return res;
    }

    public int numberOfRows()
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, PEOPLE_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String surname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        db.update("people", contentValues, "id = ? ", new String[] {Integer.toString(id)});
        return  true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("people",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList getAllPeople()
    {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from people", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PEOPLE_COLUMN_NAME)));
            array_list.add(res.getString(res.getColumnIndex(PEOPLE_COLUMN_SURNAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
