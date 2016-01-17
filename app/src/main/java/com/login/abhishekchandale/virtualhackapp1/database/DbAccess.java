package com.login.abhishekchandale.virtualhackapp1.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by admi on 9/9/15.
 */
public class DbAccess extends SQLiteOpenHelper {

    Context context_;
    private  static String databaseName="hackApp1Db";
    private  static String   table_userRegistration="userRegistration";
    private  static String table_complaintDetails="complaintDetails";
    public DbAccess(Context context){
        super(context, databaseName, null, 1);
        context_ = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userRegistartion_table = "CREATE TABLE IF NOT EXISTS   " + table_userRegistration+ " (name TEXT, email TEXT, gender TEXT, birthday TEXT,location TEXT, password TEXT)";
        db.execSQL(userRegistartion_table);
        String complaintDetails_table = "CREATE TABLE IF NOT EXISTS   " + table_complaintDetails+ " (cid INTEGER PRIMARY KEY autoincrement, complaintMessage TEXT, image BLOB, name TEXT, email TEXT, datetime TEXT,city ,lattitude INTEGER,logitude, complaintAddress TEXT )";
        db.execSQL(complaintDetails_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_userRegistration);
        db.execSQL("DROP TABLE IF EXISTS " + table_complaintDetails);
        onCreate(db);
        db.close();

    }

    public void addUser(String name,String email,String gender,String birthday,String location,String password){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + table_userRegistration + "(name,email,gender,birthday,location,password) VALUES('" + name + "','" + email + "','" + gender + "', '" + birthday + "', ' " + location + "', '" + password + "')");
        db.close();

    }


    public void addComplaint(String complaintMessage, byte[] image,String name,String email,String date,Double lat,Double lon,String complaintAddress){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + table_complaintDetails + "(complaintMessage,image,name,email,datetime,lattitude,logitude,complaintAddress) VALUES('" + complaintMessage + "','" + image + "','" + name + "', '" + email + "','" + date + "', ' " + lat + "', '" + lon + "', '" + complaintAddress + "')");
        db.close();
    }

    public Cursor getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ table_userRegistration, null);
        return cursor;
    }
    public Cursor getComplaint(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ table_complaintDetails, null);
        return cursor;
    }


}
