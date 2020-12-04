package com.example.prac7.helpers;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "prac7.db";
    private static final String DATABASE_CREATESQL =
        "CREATE TABLE Tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, colour TEXT, status TEXT, datedue INTEGER );";

    private static DatabaseHelper databaseHelper;
    private static Context context;

    // Initializes the DatabaseHelper.
    // This must be called whenever the app starts before you call
    // any other functions in this helper class.
    //
    public static void initialize(Context context)
    {
        DatabaseHelper.context = context;
    }

    // Gets a singleton instance of the SQLiteDatabase object
    //
    public static SQLiteDatabase getInstance()
    {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(DatabaseHelper.context);
        return databaseHelper.getWritableDatabase();
    }

    // Constructor for the database helper class.
    //
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Executes the SQL to create the database tables.
    //
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATESQL);
        Log.d("DatabaseHelper", "Database created");
    }

    // Executes the SQL to update the database structures to the new version.
    //
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "Database upgraded");
    }
}