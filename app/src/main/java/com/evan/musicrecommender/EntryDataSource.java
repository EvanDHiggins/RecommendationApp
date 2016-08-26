package com.evan.musicrecommender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evanh on 8/16/2016.
 */
public class EntryDataSource {

    private SQLiteDatabase database;
    private DBHandler dbHandler;
    private String[] allColumns = {DBHandler.COLUMN_ID,
                                   DBHandler.COLUMN_ARTIST,
                                   DBHandler.COLUMN_ALBUM};

    public EntryDataSource(Context context) {
        dbHandler = new DBHandler(context);
    }

    public void open() throws SQLException {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public void createEntry(RecommendationEntry entry) {
        ContentValues values = new ContentValues();

        values.put(DBHandler.COLUMN_ARTIST, entry.getArtist().get());
        if(entry.hasAlbum())
            values.put(DBHandler.COLUMN_ALBUM, entry.getAlbum().get());

        long insertId = database.insert(DBHandler.TABLE_ENTRIES, null, values);
        Cursor dbCursor = database.query(DBHandler.TABLE_ENTRIES, null, null, null, null, null, null);

//        Cursor cursor = database.query(DBHandler.TABLE_ENTRIES,
//                                       allColumns,
//                                       DBHandler.COLUMN_ID + " = " + insertId,
//                                       null, null, null, null);
//        cursor.moveToFirst();
//        RecommendationEntry newEntry = entryFromCursor(cursor);
//        cursor.close();
//        return newEntry;
    }

    public Optional<RecommendationEntry> getRandomEntry() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHandler.TABLE_ENTRIES, null);
        if(cursor.getCount() == 0) {
            System.out.println("No items in cursor");
            return Optional.empty();
        }

        cursor.moveToPosition(MainActivity.random.nextInt(cursor.getCount()));
        Optional<RecommendationEntry> entry = Optional.of(entryFromCursor(cursor));

        cursor.close();

        return entry;
    }

    public int getEntriesCount()
    {
        String countQuery = "SELECT * FROM " + DBHandler.TABLE_ENTRIES;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    private RecommendationEntry entryFromCursor(Cursor cursor) {
        String artistName = cursor.getString(1);
        String albumName = cursor.getString(2);

        return new RecommendationEntry(artistName, albumName);
    }
}
