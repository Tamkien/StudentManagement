package com.example.hoangkimduc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "People";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String DOB = "dob";

    public DBManager(Context context)  {
        super(context, "Contact", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Script to create table.
        String script = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT," + ADDRESS + " TEXT,"
                + DOB + " TEXT" + ")";
        // Execute script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + "People");

        // Recreate
        onCreate(db);
    }

    public void add(Person person){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, person.getName());
        values.put(ADDRESS, person.getAddress());
        values.put(DOB, person.getBirthday());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);

        // Closing database connection
        db.close();
    }

    public ArrayList<Person> getAll(){
        ArrayList<Person> people = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setName(cursor.getString(0));
                person.setAddress(cursor.getString(1));
                person.setBirthday(cursor.getString(2));
                // Adding note to list
                people.add(person);
            } while (cursor.moveToNext());
        }

        return people;
    }


    public int update(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, person.getName());
        values.put(ADDRESS, person.getAddress());
        values.put(DOB, person.getBirthday());

        // updating row
        return db.update(TABLE_NAME, values, NAME + " = ?",
                new String[]{String.valueOf(person.getName())});
    }

    public void delete(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME + " = ?",
                new String[] { String.valueOf(person.getName()) });
        db.close();
    }
}