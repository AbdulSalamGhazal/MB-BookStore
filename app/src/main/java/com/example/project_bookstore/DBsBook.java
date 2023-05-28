package com.example.project_bookstore;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DBsBook extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BookStore";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "books";
    public static final String COLUMN_ID = "book_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_PRICE = "price";


    public DBsBook(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // this is called teh first time a database is accessed(referenced).
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_AUTHOR + " TEXT NOT NULL, " +
                    COLUMN_QTY + " INTEGER NOT NULL CHECK (qty >= 0), " +
                    COLUMN_PRICE+ " REAL NOT NULL CHECK (price >= 0) " +
                    ");";

        db.execSQL(sql);
    }

    // if the version every has changed. no need to code.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean addBook(bookModel bookModel){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, bookModel.name);
        cv.put(COLUMN_AUTHOR, bookModel.author);
        cv.put(COLUMN_QTY, bookModel.qty);
        cv.put(COLUMN_PRICE, bookModel.price);

        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;
    }
     List<bookModel> getAllBooks(){
         List<bookModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
         if(cursor.moveToFirst()){
             do {
                int bookID = cursor.getInt(0);
                String bookName = cursor.getString(1);
                String bookAuthor = cursor.getString(2);
                int bookQty = cursor.getInt(3);
                double bookPrice = cursor.getDouble(4);

                list.add(new bookModel(bookID, bookName, bookAuthor, bookQty, bookPrice));
             }while (cursor.moveToNext());
         }
         cursor.close();
         sqLiteDatabase.close();
         return list;
    }
    boolean updateBook(bookModel book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(COLUMN_NAME, book.name);
        data.put(COLUMN_AUTHOR, book.author);
        data.put(COLUMN_QTY, book.qty);
        data.put(COLUMN_PRICE, book.price);
        return sqLiteDatabase.update(TABLE_NAME, data, COLUMN_ID+" = ?",
                 new String[]{String.valueOf(book.id)}) > 0;
    }
    boolean deleteBook(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID+"=?",
                new String[]{String.valueOf(id)}) > 0;
    }


}
