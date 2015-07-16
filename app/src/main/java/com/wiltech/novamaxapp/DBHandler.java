package com.wiltech.novamaxapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import java.util.concurrent.DelayQueue;

/**
 * Created by Wiliam on 22/02/2015.
 */
public class DBHandler extends SQLiteOpenHelper{

    //declare the variables for the database
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "usersDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_LASTLOGONDATE = "lastLogOnDate";
    public static final String COLUMN_LASTPURCHASEDATE = "lastPurchaseDate";
    public static final String COLUMN_LASTTIMEIN = "lastTimeIn";
    public static final String COLUMN_PURCHASETOTAL = "purchaseTotal";

    //Constructor
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }//ends constructor

    //Override method to be used by SQLHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the database
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_LASTLOGONDATE + " TEXT," +
                COLUMN_LASTPURCHASEDATE + " TEXT," +
                COLUMN_LASTTIMEIN + " TEXT," +
                COLUMN_PURCHASETOTAL + " INTEGER" +
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
        //drop the current table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        //Call the on create method to recreate the table again
        onCreate(db);
    }

    //**************************** Add to Database ****************************
    public void addUser(UsersDB users){
        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_NAME, users.get_name());
        userValues.put(COLUMN_USERNAME, users.get_userName());
        userValues.put(COLUMN_EMAIL, users.get_email());
        userValues.put(COLUMN_PASSWORD, users.get_password());

        //declare the database
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_USERS, null, userValues);
            db.close();
        }catch(SQLiteException e){
            e.printStackTrace();
        }

    }//ends add user


    //************************ Select one record ***********************
    public String checkUser(String userName, String password){
        //read the whole database
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + userName + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";

        //create a cursor to return the value
        Cursor cursor = db.rawQuery(query, null);

        UsersDB user = new UsersDB();

        //loop through the resukts and assign the username if password and username mat=ches database
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            user.set_userName(cursor.getString(2));
            cursor.close();
        }
        else{
            user = null;
            userName = "Not match found";
        }

        db.close();

        //return username if success of Not match found if unsuccesful
        return userName;
    }//ends check user


    //********************* check User in DB **************************
    public String getUser(String userName, String password){

        /*
        //read the whole database
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";

        boolean loggedIn = false;

        //cursor point to a location in your results Eg, first or last result
        try {
            Cursor cursor = db.rawQuery(query, null);
            //Move to first row in results
            cursor.moveToFirst();

            //loop through the results starting from first row
            while (!cursor.isAfterLast()) {
                if (cursor.getString(cursor.getColumnIndex("userName")) == userName) {
                   //if the username matches then check the password
                    if(cursor.getString(cursor.getColumnIndex("password")) == password){
                        loggedIn = true;
                        //Toast.makeText(getApplicationContext, "Logged in", Toast.LENGTH_LONG).show();
                    }
                }//ends if

                cursor.moveToNext();
            }//ends while loop

            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if(loggedIn == false){
            userName = "User Name or Password wrong";
        }
        */
        return userName;

    }//ends get user

    //****************Print all the data from the database *************************
    public String getUsersFromDB(){
        //create an empty string to hold the values
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE 1";

        //cursor point to a location in your results Eg, first or last result
        try {
            Cursor cursor = db.rawQuery(query, null);
            //Move to first row in results
            cursor.moveToFirst();

            //loop through the results starting from first row
            while (!cursor.isAfterLast()) {
                if (cursor.getString(cursor.getColumnIndex("userName")) != null) {
                    //Add the product name to the String and create a new line
                    dbString += cursor.getString(cursor.getColumnIndex("userName"));
                    dbString += "\n";
                }//ends if

                cursor.moveToNext();
            }//ends while loop

            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if(dbString.isEmpty()){
            dbString = "There is no data on the DB";
        }
        return dbString;
    }//ends print users

}//ends class





























