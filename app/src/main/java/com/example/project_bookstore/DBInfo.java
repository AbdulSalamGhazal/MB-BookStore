package com.example.project_bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBInfo extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Info.db";

    public DBInfo(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(id INTEGER PRIMARY KEY,email TEXT NOT NULL,name TEXT NOT NULL," +
                "password TEXT NOT NULL,phone INTEGER NOT NULL,gender TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public Boolean addInfo(Integer id,String email,String name,String password,Integer phone,String gender){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("name",name);
        cv.put("password",password);
        cv.put("phone",phone);
        cv.put("gender",gender);
        long result = sqLiteDatabase.insert(DATABASE_NAME,null,cv);
        if(result==-1) return false;
        else
            return true;
    }




}
