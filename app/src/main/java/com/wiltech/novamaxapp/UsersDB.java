package com.wiltech.novamaxapp;

/**
 * Created by Wiliam on 22/02/2015.
 */
public class UsersDB {

    //declare the collumns
    private int _id;
    private String _name;
    private String _userName;
    private String _email;
    private String _password;
    private String _lastLogOnDate;
    private String _lastPurchaseDate;
    private String _lastTimeIn;
    private String _purchaseTotal;

    //constructors
    public UsersDB() {
    }
    public UsersDB(String _name, String _userName, String _email, String _password) {
        this._name = _name;
        this._userName = _userName;
        this._email = _email;
        this._password = _password;
    }

    //Getters and Setter
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_lastLogOnDate() {
        return _lastLogOnDate;
    }

    public void set_lastLogOnDate(String _lastLogOnDate) {
        this._lastLogOnDate = _lastLogOnDate;
    }

    public String get_lastPurchaseDate() {
        return _lastPurchaseDate;
    }

    public void set_lastPurchaseDate(String _lastPurchaseDate) {
        this._lastPurchaseDate = _lastPurchaseDate;
    }

    public String get_lastTimeIn() {
        return _lastTimeIn;
    }

    public void set_lastTimeIn(String _lastTimeIn) {
        this._lastTimeIn = _lastTimeIn;
    }

    public String get_purchaseTotal() {
        return _purchaseTotal;
    }

    public void set_purchaseTotal(String _purchaseTotal) {
        this._purchaseTotal = _purchaseTotal;
    }
}
