package com.example.project_bookstore;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.preference.PreferenceFragment;

import java.util.ArrayList;
import java.util.List;

public class DBs extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DB";
    public static final int DATABASE_VERSION = 1;
    // BOOK TABLE
    public static final String BOOK_TABLE_NAME = "books";
    public static final String BOOK_COLUMN_ID = "_id";
    public static final String BOOK_COLUMN_NAME = "name";
    public static final String BOOK_COLUMN_DESC = "description";

    public static final String BOOK_COLUMN_AUTHOR = "author";
    public static final String BOOK_COLUMN_QTY = "qty";
    public static final String BOOK_COLUMN_PRICE = "price";

    // USER TABLE
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_PHONE = "phone";
    public static final String USER_COLUMN_GENDER = "gender";
    public static final String USER_COLUMN_ROLE = "role";

    public static final String USER_COLUMN_BOOKS = "books";

    // Order TABLE
    public static final String ORDER_TABLE_NAME = "orders";
    public static final String ORDER_COLUMN_ID = "_id";
    public static final String ORDER_COLUMN_USER_ID = "user_id";
    public static final String ORDER_COLUMN_BOOKS = "user_id";
    public static final String ORDER_COLUMN_TOTAL = "total";
    public static final String ORDER_COLUMN_PAYMENT_METHOD = "payment";
    public static final String ORDER_COLUMN_STATUS = "status";


    public DBs(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // this is called teh first time a database is accessed(referenced).
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + BOOK_TABLE_NAME + "(" +
                BOOK_COLUMN_ID + " INTEGER PRIMARY KEY , " +
                BOOK_COLUMN_NAME + " TEXT NOT NULL, " +
                BOOK_COLUMN_DESC + " TEXT, " +
                BOOK_COLUMN_AUTHOR + " TEXT NOT NULL, " +
                BOOK_COLUMN_QTY + " INTEGER NOT NULL CHECK (qty >= 0), " +
                BOOK_COLUMN_PRICE + " REAL NOT NULL CHECK (price >= 0) " +
                    ");";

        db.execSQL(sql);
         sql = "CREATE TABLE " + USER_TABLE_NAME + "(" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY , " +
                USER_COLUMN_NAME + " TEXT NOT NULL, " +
                USER_COLUMN_EMAIL + " TEXT NOT NULL, " +
                USER_COLUMN_PASSWORD + " TEXT NOT NULL, " +
                USER_COLUMN_PHONE + " TEXT NOT NULL, " +
                USER_COLUMN_GENDER + " TEXT NOT NULL, " +
                 USER_COLUMN_ROLE + " TEXT NOT NULL, " +
                 USER_COLUMN_BOOKS + " TEXT  DEFAULT '' " +
                ");";

        db.execSQL(sql);

//        sql = "CREATE TABLE " + ORDER_TABLE_NAME + "(" +
//                ORDER_COLUMN_ID + " INTEGER PRIMARY KEY , " +
//                ORDER_COLUMN_USER_ID + " TEXT NOT NULL, " +
//                ORDER_COLUMN_BOOKS + " TEXT NOT NULL, " +
//                ORDER_COLUMN_TOTAL + " INTEGER, " +
//                ORDER_COLUMN_PAYMENT_METHOD + " TEXT NOT NULL, " +
//                ORDER_COLUMN_STATUS + " TEXT  DEFAULT 'Pending' " +
//                ");";
//
//        db.execSQL(sql);
    }

    // if the version every has changed. no need to code.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    boolean addBook(BookModel book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_COLUMN_ID, book.id);
        cv.put(BOOK_COLUMN_NAME, book.name);
        cv.put(BOOK_COLUMN_DESC, book.desc);
        cv.put(BOOK_COLUMN_AUTHOR, book.author);
        cv.put(BOOK_COLUMN_QTY, book.qty);
        cv.put(BOOK_COLUMN_PRICE, book.price);

        return sqLiteDatabase.insertOrThrow(BOOK_TABLE_NAME, null, cv) != -1;

    }

     List<BookModel> getAllBooks(){
         List<BookModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + BOOK_TABLE_NAME, null);

         if(cursor.moveToFirst()){
             do {
                int bookID = cursor.getInt(0);
                String bookName = cursor.getString(1);
                 String bookDesc = cursor.getString(2);
                 String bookAuthor = cursor.getString(3);
                int bookQty = cursor.getInt(4);
                double bookPrice = cursor.getDouble(5);

                list.add(new BookModel(bookID, bookName, bookDesc, bookAuthor, bookQty, bookPrice));
             }while (cursor.moveToNext());
         }
         cursor.close();
         sqLiteDatabase.close();
         return list;
    }
    boolean updateBook(BookModel book){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_COLUMN_NAME, book.name);
        cv.put(BOOK_COLUMN_DESC, book.desc);
        cv.put(BOOK_COLUMN_AUTHOR, book.author);
        cv.put(BOOK_COLUMN_QTY, book.qty);
        cv.put(BOOK_COLUMN_PRICE, book.price);
        return sqLiteDatabase.update(BOOK_TABLE_NAME, cv, BOOK_COLUMN_ID +" = ?",
                 new String[]{String.valueOf(book.id)}) > 0;
    }
    boolean deleteBook(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(BOOK_TABLE_NAME, BOOK_COLUMN_ID +"=?",
                new String[]{String.valueOf(id)}) > 0;
    }

    boolean addUser(UserModel user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_ID, user.id);
        cv.put(USER_COLUMN_NAME, user.name);
        cv.put(USER_COLUMN_EMAIL, user.email);
        cv.put(USER_COLUMN_PASSWORD, user.password);
        cv.put(USER_COLUMN_PHONE, user.phone);
        cv.put(USER_COLUMN_GENDER, user.gender);
        cv.put(USER_COLUMN_ROLE, user.role);
        return sqLiteDatabase.insert(USER_TABLE_NAME, null, cv) != -1;
    }
    boolean updateUser(UserModel user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_NAME, user.name);
        cv.put(USER_COLUMN_EMAIL, user.email);
        cv.put(USER_COLUMN_PASSWORD, user.password);
        cv.put(USER_COLUMN_PHONE, user.phone);
        cv.put(USER_COLUMN_GENDER, user.gender);
        cv.put(USER_COLUMN_ROLE, user.role);

        return sqLiteDatabase.update(USER_TABLE_NAME, cv, USER_COLUMN_ID +" = ?",
                new String[]{String.valueOf(user.id)}) > 0;
    }
    boolean deleteUser(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(USER_TABLE_NAME, USER_COLUMN_ID +"=?",
                new String[]{String.valueOf(id)}) > 0;
    }
    List<UserModel> getAllUsers(){
        List<UserModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userEmail = cursor.getString(2);
                String userPassword = cursor.getString(3);
                String userPhone = cursor.getString(4);
                String userGender = cursor.getString(5);
                String userRole = cursor.getString(6);
                String userBooks = cursor.getString(7);

                list.add(new UserModel(userID, userName, userEmail, userPassword, userPhone, userGender, userRole, userBooks));
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    // TODO method to add a book to books String
    void addBookToUser(int userId, int bookId){
        // fetch the existing books_ids
         Cursor cursor = getReadableDatabase().query(USER_TABLE_NAME, new String[]{USER_COLUMN_BOOKS},
                USER_COLUMN_ID + " = ?", new String[]{String.valueOf(userId)},null,
                null,null);
         String ids = cursor.moveToFirst() ? cursor.getString(0):"";


        // add the new book
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (ids.equals("")) {
            cv.put(USER_COLUMN_BOOKS, bookId);
        } else {
            cv.put(USER_COLUMN_BOOKS, ids + "," + bookId);
        }
        sqLiteDatabase.update(USER_TABLE_NAME, cv, USER_COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)});

    }

    //  method to fetch all books (not only ids)
    List<BookModel> getUserBooks( int userId){
        List<BookModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(USER_TABLE_NAME,new String[]{USER_COLUMN_BOOKS},
                USER_COLUMN_ID + " = ?", new String[]{String.valueOf(userId)},null,
                null,null);
        if (cursor != null && cursor.moveToFirst()) {
            String ids = cursor.getString(0);
            for (String id : ids.split(",")) {
                list.add(getBookById(id));
            }
        }
        return list;
    }
    BookModel getBookById(String id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(BOOK_TABLE_NAME,new String[]{BOOK_COLUMN_ID,BOOK_COLUMN_NAME
                        ,BOOK_COLUMN_DESC,BOOK_COLUMN_AUTHOR,BOOK_COLUMN_QTY,BOOK_COLUMN_PRICE},
                BOOK_COLUMN_ID + " = ?", new String[]{id},null,
                null,null);

        if (cursor.moveToFirst()) {
            int bookID = cursor.getInt(0);
            String bookName = cursor.getString(1);
            String bookDesc = cursor.getString(2);
            String bookAuthor = cursor.getString(3);
            int bookQty = cursor.getInt(4);
            double bookPrice = cursor.getDouble(5);

            return new BookModel(bookID, bookName, bookDesc, bookAuthor, bookQty, bookPrice);
        }
        return null;
    }
    void deleteBookFromUser(int userId, int bookId){
        String newBooks = "";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(USER_TABLE_NAME,new String[]{USER_COLUMN_BOOKS},
                USER_COLUMN_ID + " = ?", new String[]{String.valueOf(userId)},null,
                null,null);
        if (cursor.moveToFirst()) {
            String ids = cursor.getString(0);
            for (String id : ids.split(",")) {
                if (!id.equals(String.valueOf(bookId))){
                    newBooks += id +",";

                }
            }
            cursor.close();
            sqLiteDatabase.close();
            deleteAllBooksFromUser(userId);
            for (String id : newBooks.split(",")){
                addBookToUser(userId, Integer.parseInt(id));
            }
        }
    }
    private void deleteAllBooksFromUser(int userId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_BOOKS, "" );
        sqLiteDatabase.update(USER_TABLE_NAME, cv, USER_COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)});
        sqLiteDatabase.close();
    }





    //  method to fetch all existing emails
    List<String> getAllEmails() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(USER_TABLE_NAME, new String[]{USER_COLUMN_EMAIL},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(0);
                list.add(email);
            }while (cursor.moveToNext());
        }
        return list;
    }
    //  fetch user data when log in
    UserModel getUserInfoByEmail(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(USER_TABLE_NAME,new String[]{USER_COLUMN_ID,USER_COLUMN_NAME,
                USER_COLUMN_EMAIL,USER_COLUMN_PASSWORD,USER_COLUMN_PHONE,USER_COLUMN_GENDER, USER_COLUMN_ROLE,
                        USER_COLUMN_BOOKS},
                USER_COLUMN_EMAIL + " = ?", new String[]{email},null,
                null,null);
        if (cursor.moveToFirst()) {
            int userID = cursor.getInt(0);
            String userName = cursor.getString(1);
            String userEmail = cursor.getString(2);
            String userPassword = cursor.getString(3);
            String userPhone = cursor.getString(4);
            String userGender = cursor.getString(5);
            String userRole = cursor.getString(6);
            String userBooks = cursor.getString(7);

            return new UserModel(userID, userName, userEmail, userPassword, userPhone, userGender, userRole, userBooks);
        }else {
            return null;
        }
    }

    // TODO handle img in book database

    // TODO make_order(user)
        // handel the total price

    // TODO fetch all orders for the admin view

}
