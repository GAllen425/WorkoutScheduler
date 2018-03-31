package com.packtpub.workoutscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SQLiteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "routine_database";
    public static final String ROUTINE_TABLE_NAME = "routine";
    public static final String ROUTINE_COLUMN_ID = "_id";
    public static final String ROUTINE_COLUMN_NAME = "name";
    public static final String ROUTINE_COLUMN_DATE = "date";
    public static final String ROUTINE_COLUMN_DAY = "day";
    public static final String EXERCISE_COLUMN_NAME = "exercise";
    public static final String EXERCISE_COLUMN_WEIGHT = "weight";
    public static final String EXERCISE_COLUMN_SETS = "sets";
    public static final String EXERCISE_COLUMN_REPS = "reps";
    public static final String EXERCISE_COLUMN_RATIO = "ratio";

    public static final String PERSON_COLUMN_GENDER = "gender";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ROUTINE_TABLE_NAME + " (" +
                ROUTINE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ROUTINE_COLUMN_NAME + " TEXT ," +
                ROUTINE_COLUMN_DATE + " DATE, " +
                ROUTINE_COLUMN_DAY + " INT, " +
                EXERCISE_COLUMN_NAME + "TEXT, " +
                EXERCISE_COLUMN_WEIGHT + " DECIMAL(5,2) , " +
                EXERCISE_COLUMN_SETS + " INT , " +
                EXERCISE_COLUMN_REPS + " INT, " +
                EXERCISE_COLUMN_RATIO + " TEXT " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ROUTINE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
