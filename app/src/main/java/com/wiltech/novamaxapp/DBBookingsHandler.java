package com.wiltech.novamaxapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiliam on 24/02/2015.
 */
public class DBBookingsHandler extends SQLiteOpenHelper{

    //String date = "";

    //dclare the database elements
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "bookingsDB.db";
    public static final String TABLE_BOOKINGS = "bookings";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_STAFFNAME = "staffName";
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMEIN = "timeIn";
    public static final String COLUMN_TIMEOUT = "timeOut";
    public static final String COLUMN_PRICE = "price";

    //Constructor
    public DBBookingsHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Override methods

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_BOOKINGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_STAFFNAME + " TEXT," +
                COLUMN_SERVICE + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_TIMEIN + " TEXT," +
                COLUMN_TIMEOUT + " TEXT," +
                COLUMN_PRICE + " INTEGER" +
                ");";
        //execute the query
        try{
            db.execSQL(query);
        }catch(SQLiteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop the current table and upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);

        //call the create database
        onCreate(db);
    }

    //*************************** Add to DB method ********************
    public void addAppointment(DBBookings bookings){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, bookings.get_userName());
        values.put(COLUMN_STAFFNAME, bookings.get_staffName());
        values.put(COLUMN_SERVICE, bookings.get_service());
        values.put(COLUMN_DATE, bookings.get_date());
        values.put(COLUMN_TIMEIN, bookings.get_timeIn());

        //add the rest here

        //declare the database
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_BOOKINGS, null, values);
            db.close();
        }catch(SQLiteException e){
            e.printStackTrace();
        }

    }//ends add appointment


    //****************Print all the data from the database *************************
    public String[] getMyBookingsFromDB(String userName){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_USERNAME + " =\"" + userName + "\"" + " ORDER BY " + COLUMN_DATE + " DESC";

        //cursor point to a location in your results Eg, first or last result
        //try {
        Cursor cursor = db.rawQuery(query, null);
        //Move to first row in results
        cursor.moveToFirst();
        //create integer to loop through
        int i = 0;

        //create an empty string to hold the values
        String dbString[] = new String[cursor.getCount()];

        //loop through the results starting from first row
        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("userName")) != null) {

                //Add the product name to the String and create a new line
                dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex("userName"));
                dbString[i] += " Staff: " + cursor.getString(cursor.getColumnIndex("staffName"));
                dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex("service"));
                dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex("date"));
                dbString[i] += " At: " + cursor.getString(cursor.getColumnIndex("timeIn"));
                //bString += "\n";
                i++;
            }//ends if

            cursor.moveToNext();
        }//ends while loop

        db.close();

        return dbString;
    }


    //****************Print all the data from the database *************************
    public String[] getBookingsFromDB(String date){

        //List<String> dbString = new ArrayList<String>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKINGS + " WHERE " + COLUMN_DATE + " =\"" + date + "\"";

        //cursor point to a location in your results Eg, first or last result
        //try {
            Cursor cursor = db.rawQuery(query, null);
            //Move to first row in results
            cursor.moveToFirst();
            //create integer to loop through
            int i = 0;

            //create an empty string to hold the values
            String dbString[] = new String[cursor.getCount()];

        //loop through the results starting from first row
            while (!cursor.isAfterLast()) {
                if (cursor.getString(cursor.getColumnIndex("userName")) != null) {

                    //Add the product name to the String and create a new line
                    dbString[i] = "Client: " + cursor.getString(cursor.getColumnIndex("userName"));
                    dbString[i] += " Staff: " + cursor.getString(cursor.getColumnIndex("staffName"));
                    dbString[i] += " Service: " + cursor.getString(cursor.getColumnIndex("service"));
                    dbString[i] += " Date: " + cursor.getString(cursor.getColumnIndex("date"));
                    dbString[i] += " At: " + cursor.getString(cursor.getColumnIndex("timeIn"));
                    //bString += "\n";
                    i++;
                }//ends if

                cursor.moveToNext();
            }//ends while loop

            db.close();
       // } catch (SQLiteException e) {
           // Toast.makeText(this, "Could not add appointment", Toast.LENGTH_LONG).show();
        //    e.printStackTrace();
        //    e.getMessage();
        //}
       // if(dbString[0].isEmpty()){
           // dbString[0] = "There is no appointments on the DB";
        //}
        return dbString;
    }//ends print bookings


}//ends class
