package com.example.nezar_lulu.second_assignment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by nezar_lulu on 2017-10-24.
 */

public class MySQLiteDatabase extends SQLiteOpenHelper {
    public static final String INFO_TABLE = "INFO";
    public static final String NAME_COLUMN = "NAME";
    public static final String NUMBER_COLUMN = "NUMBER";
    public static final String ID_COLUMN = "_id";

    public MySQLiteDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table " + INFO_TABLE + "(_id Integer PRIMARY KEY AUTOINCREMENT, " + NAME_COLUMN + " TEXT, " + NUMBER_COLUMN + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INFO_TABLE);
        this.onCreate(sqLiteDatabase);
    }

}
