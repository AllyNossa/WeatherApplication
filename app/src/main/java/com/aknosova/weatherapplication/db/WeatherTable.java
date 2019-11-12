package com.aknosova.weatherapplication.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WeatherTable {
    private final static String TABLE_NAME = "Weather";
    private final static String COLUMN_CITY = "city";
    private final static String COLUMN_TEMPERATURE = "temperature";

    static void createTable(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_CITY + " TEXT," + COLUMN_TEMPERATURE + " INTEGER);");
    }

    public static void addValue(String city, Float temperature, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY, city);
        values.put(COLUMN_TEMPERATURE, temperature);

        database.insert(TABLE_NAME, null, values);
    }

    public static void editValue(String cityToEdit, Float newWeather, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEMPERATURE, newWeather);

        database.update(TABLE_NAME, values, COLUMN_CITY + "=" + "\"" + cityToEdit + "\"", null);
    }

    public static boolean checkValue(String city, SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_CITY + " = ?", new String[]{city});
        boolean result = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return result;
    }
}

