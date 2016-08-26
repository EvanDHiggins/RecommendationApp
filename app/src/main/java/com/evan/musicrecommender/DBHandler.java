package com.evan.musicrecommender;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Evan on 8/8/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "recommendationEntries";

    public static final String TABLE_ENTRIES = "entries";

    public static final String COLUMN_ID = "_id";

    public static final String COLUMN_ARTIST = "artist";

    public static final String COLUMN_ALBUM = "album";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ENTRY_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ARTIST + " TEXT,"
                + COLUMN_ALBUM + " TEXT)";
        System.out.println("------------------------------------------------------------");
        System.out.println(CREATE_ENTRY_TABLE);
        db.execSQL(CREATE_ENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ENTRIES);
        onCreate(db);
    }
}
