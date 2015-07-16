package com.wiltech.novamaxapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Wiliam on 23/02/2015.
 */
public class SaveUserLoggedInSharedPreference {
    //declare a string to hold the username who is loggec on
    static final String PREF_USER_NAME = "";

    static SharedPreferences getUserSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //set the user name logged in
    public static void setUserNameLoggedIn(Context ctx, String userName){
        SharedPreferences.Editor editor = getUserSharedPreferences(ctx).edit();
        //add the username to the shared pref file
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    //get the username logged in
    public static String getPrefUserName(Context ctx){
        return getUserSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    //Log the user out
    public static void logOut(Context ctx){
        SharedPreferences.Editor editor = getUserSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
