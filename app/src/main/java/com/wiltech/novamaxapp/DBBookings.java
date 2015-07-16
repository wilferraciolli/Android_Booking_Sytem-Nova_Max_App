package com.wiltech.novamaxapp;

/**
 * Created by Wiliam on 24/02/2015.
 */
public class DBBookings {

    private int _id;
    private String _userName;
    private String _staffName;
    private String _service;
    private String _date;
    private String _timeIn;
    private String _timeOut;
    private int _price;

    //Constructors
    public DBBookings() {
    }
    public DBBookings(String _userName, String _staffName, String _service, String _date, String _timeIn) {
        this._userName = _userName;
        this._staffName = _staffName;
        this._service = _service;
        this._date = _date;
        this._timeIn = _timeIn;
    }

    //getters and Setters
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_staffName() {
        return _staffName;
    }

    public void set_staffName(String _staffName) {
        this._staffName = _staffName;
    }

    public String get_service() {
        return _service;
    }

    public void set_service(String _service) {
        this._service = _service;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_timeIn() {
        return _timeIn;
    }

    public void set_timeIn(String _timeIn) {
        this._timeIn = _timeIn;
    }

    public String get_timeOut() {
        return _timeOut;
    }

    public void set_timeOut(String _timeOut) {
        this._timeOut = _timeOut;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }
}
