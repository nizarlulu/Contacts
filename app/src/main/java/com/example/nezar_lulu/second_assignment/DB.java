package com.example.nezar_lulu.second_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*import static com.example.nezar_lulu.second_assignment.MySQLiteDatabase.ID_COLUMN;
import static com.example.nezar_lulu.second_assignment.MySQLiteDatabase.INFO_TABLE;
import static com.example.nezar_lulu.second_assignment.MySQLiteDatabase.NAME_COLUMN;*/

/**
 * Created by nezar_lulu on 2017-10-24.
 */

public class DB {

    private final Context context;
    MySQLiteDatabase mySQLiteDatabase;
    SQLiteDatabase db1;

    public DB(Context context) {
        this.context = context;
        mySQLiteDatabase = new MySQLiteDatabase(context, "infb", null, 2);
    }

    public void open() {
        db1 = mySQLiteDatabase.getWritableDatabase();
    }

    public void close() {
        db1.close();
    }

    public long addNew(String name, String number) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabase.NAME_COLUMN, name);
        values.put(MySQLiteDatabase.NUMBER_COLUMN, number);
        long id = db1.insert(MySQLiteDatabase.INFO_TABLE, null, values);
        return id;
    }

    public Cursor getnew() {
        Cursor c = db1.rawQuery("select * from INFO", null);
        return c;
    }

    public String getname(int pos) {
        Cursor c = db1.rawQuery("select NAME from INFO WHERE _id = '" + pos + "'", null);
        if (c.moveToFirst()) ;


        return c.getString(c.getColumnIndex("NAME"));

    }

    public String getnumber(int pos) {
        Cursor c = db1.rawQuery("select NUMBER from INFO WHERE _id = '" + pos + "'", null);
        if (c.moveToFirst()) ;

        return c.getString(c.getColumnIndex("NUMBER"));
    }

    public boolean updateData(int id, String name, String number) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteDatabase.ID_COLUMN, id);
        contentValues.put(MySQLiteDatabase.NAME_COLUMN, name);
        contentValues.put(MySQLiteDatabase.NUMBER_COLUMN, number);
        db1.update(MySQLiteDatabase.INFO_TABLE, contentValues, "_id = ?", new String[]{String.valueOf(id)});
        return true;
    }

    public int remove(int id) {
        int deleted = db1.delete(MySQLiteDatabase.INFO_TABLE, "_id=?", new String[]{id + ""});
        return deleted;
    }

}


