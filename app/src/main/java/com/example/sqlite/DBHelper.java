package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Spannable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static String name="android";
    private static SQLiteDatabase.CursorFactory factory = null;
    private static int version = 1;

    private ContentValues contentValues = new ContentValues();

    public DBHelper(Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS Persons(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "firstName TEXT, "+
                "lastName TEXT, "+
                "email TEXT)";
                db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS Persons";
        db.execSQL(query);
    }

    public boolean insertUserData(String firstName, String lastName, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);

        long result = db.insert("Persons",null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateUserData(int id, String firstName, String lastName, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email", email);

        long result = db.update("Persons", contentValues, "id=?", new String[]{String.valueOf(id)});

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteUserData(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Persons WHERE id = ?", new String[]{String.valueOf(id)});

        if(cursor.getCount() > 0) {
            long result = db.delete("Persons", "id=?", new String[]{String.valueOf(id)});
            if(result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Persons", null);
        return cursor;
    }
}
