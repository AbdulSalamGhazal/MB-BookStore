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
    public static final String BOOK_COLUMN_IMAGE = "image";
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
    public static final String ORDER_COLUMN_USER_NAME = "user_name";
    public static final String ORDER_COLUMN_TOTAL = "total";
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
                BOOK_COLUMN_IMAGE + " INTEGER NOT NULL , " +
                BOOK_COLUMN_PRICE + " REAL NOT NULL CHECK (price >= 0)," +
                BOOK_COLUMN_QTY + " INTEGER NOT NULL CHECK (qty >= 0) " +

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

        sql = "CREATE TABLE " + ORDER_TABLE_NAME + "(" +
                ORDER_COLUMN_ID + " INTEGER PRIMARY KEY , " +
                ORDER_COLUMN_USER_ID + " INTEGER NOT NULL, " +
                ORDER_COLUMN_USER_NAME + " TEXT NOT NULL, " +
                ORDER_COLUMN_TOTAL + " INTEGER NOT NULL, " +
                ORDER_COLUMN_STATUS + " TEXT  DEFAULT 'Pending' " +
                ")";

        db.execSQL(sql);
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
        cv.put(BOOK_COLUMN_IMAGE, book.image);
        cv.put(BOOK_COLUMN_PRICE, book.price);
        cv.put(BOOK_COLUMN_QTY, book.qty);

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
                int bookImage = cursor.getInt(4);
                double bookPrice = cursor.getDouble(5);
                int bookQty = cursor.getInt(6);

                list.add(new BookModel(bookID, bookName, bookDesc, bookAuthor, bookImage, bookPrice,bookQty));
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
        cv.put(BOOK_COLUMN_IMAGE, book.image);
        cv.put(BOOK_COLUMN_PRICE, book.price);
        cv.put(BOOK_COLUMN_QTY, book.qty);

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
                BookModel book = getBookById(id);
                if (book != null) {
                    list.add(book);
                }
            }
        }
        return list;
    }
    BookModel getBookById(String id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(BOOK_TABLE_NAME,new String[]{BOOK_COLUMN_ID,BOOK_COLUMN_NAME
                        ,BOOK_COLUMN_DESC,BOOK_COLUMN_AUTHOR,BOOK_COLUMN_IMAGE,BOOK_COLUMN_PRICE,BOOK_COLUMN_QTY},
                BOOK_COLUMN_ID + " = ?", new String[]{id},null,
                null,null);

        if (cursor.moveToFirst()) {
            int bookID = cursor.getInt(0);
            String bookName = cursor.getString(1);
            String bookDesc = cursor.getString(2);
            String bookAuthor = cursor.getString(3);
            int bookImage = cursor.getInt(4);
            double bookPrice = cursor.getDouble(5);
            int bookQty = cursor.getInt(6);
            return new BookModel(bookID, bookName, bookDesc, bookAuthor, bookImage, bookPrice, bookQty);
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
    public void deleteAllBooksFromUser(int userId){
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

    String getUserInfo(int userId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(USER_TABLE_NAME,new String[]{USER_COLUMN_NAME},
                USER_COLUMN_ID + " = ?", new String[]{String.valueOf(userId)},null,
                null,null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }else {
            return "Not Found";
        }
    }

    boolean make_order(OrderModel order){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORDER_COLUMN_ID, order.id);
        cv.put(ORDER_COLUMN_USER_ID, order.userId);
        cv.put(ORDER_COLUMN_USER_NAME, getUserInfo(order.userId));
        cv.put(ORDER_COLUMN_TOTAL, order.total);
        cv.put(ORDER_COLUMN_STATUS, "Pending");
        return sqLiteDatabase.insert(ORDER_TABLE_NAME, null, cv) != -1;
    }
    List<OrderModel> getAllOrders(){
        List<OrderModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + ORDER_TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                int orderId = cursor.getInt(0);
                int userId = cursor.getInt(1);
                String userName = cursor.getString(2);
                double total = cursor.getDouble(3);
                String status = cursor.getString(4);
                list.add(new OrderModel(orderId,userId,userName,total,status));
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    OrderModel getOrderById( int order_Id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(ORDER_TABLE_NAME,new String[]{ORDER_COLUMN_ID, ORDER_COLUMN_USER_ID,
                ORDER_COLUMN_USER_NAME, ORDER_COLUMN_TOTAL, ORDER_COLUMN_STATUS},
                ORDER_COLUMN_ID + " = ?", new String[]{String.valueOf(order_Id)},null,
                null,null);
        if (cursor.moveToFirst()) {
            int orderId = cursor.getInt(0);
            int userId = cursor.getInt(1);
            String userName = cursor.getString(2);
            double total = cursor.getDouble(3);
            String status = cursor.getString(4);

            return new OrderModel(orderId, userId, userName, total, status);
        }else {
            return null;
        }

    }

    public void create_books(){
        addBook(new BookModel(
                "Atomic Habits",
                "No matter your goals, Atomic Habits offers a proven framework for improving—every day. James Clear, one of the world's leading experts on habit formation, reveals practical strategies that will teach you exactly how to form good habits, break bad ones, and master the tiny behaviors that lead to remarkable results.",
                "James Clear",
                R.drawable.atomic_habits,
                59,
                40
        ));
        addBook(new BookModel(
                "Factfulness",
                "When asked simple questions about global trends—what percentage of the world’s population live in poverty; why the world’s population is increasing; how many girls finish school—we systematically get the answers wrong. So wrong that a chimpanzee choosing answers at random will consistently outguess teachers, journalists, Nobel laureates, and investment bankers.",
                "Hans Rosling",
                R.drawable.factfulness,
                9.99,
                10
        ));
        addBook(new BookModel(
                "What If?",
                "Randall Munroe left NASA in 2005 to start up his hugely popular site XKCD 'a web comic of romance, sarcasm, math and language' which offers a witty take on the world of science and geeks. It now has 600,000 to a million page hits daily. Every now and then, Munroe would get emails asking him to arbitrate a science debate. 'My friend and I were arguing about what would happen if a bullet got struck by lightning, and we agreed that you should resolve it . . . ' He liked these questions so much that he started up What If.",
                "Randall Munroe",
                R.drawable.whatif,
                1,
                0
        ));
        addBook(new BookModel(
                "Outliers",
                "In this stunning book, Malcolm Gladwell takes us on an intellectual journey through the world of \"outliers\"--the best and the brightest, the most famous and the most successful. He asks the question: what makes high-achievers different?",
                "Malcolm Gladwell",
                R.drawable.outliers,
                15,
                120
        ));
        addBook(new BookModel(
                "Guns, Germs, and Steel",
                "In this \"artful, informative, and delightful\" (William H. McNeill, New York Review of Books) book, Jared Diamond convincingly argues that geographical and environmental factors shaped the modern world. Societies that had a head start in food production advanced beyond the hunter-gatherer stage, and then developed writing, technology, government, and organized religion—as well as nasty germs and potent weapons of war—and adventured on sea and land to conquer and decimate preliterate cultures. A major advance in our understanding of human societies, Guns, Germs, and Steel chronicles the way that the modern world came to be and stunningly dismantles racially based theories of human history.",
                "Jared Diamond",
                R.drawable.gunsgermsandsteel,
                19.99,
                54
        ));

        addBook(new BookModel(
                "Thinking, Fast and Slow",
                "In the highly anticipated Thinking, Fast and Slow, Kahneman takes us on a groundbreaking tour of the mind and explains the two systems that drive the way we think. System 1 is fast, intuitive, and emotional; System 2 is slower, more deliberative, and more logical. Kahneman exposes the extraordinary capabilities—and also the faults and biases—of fast thinking, and reveals the pervasive influence of intuitive impressions on our thoughts and behavior. The impact of loss aversion and overconfidence on corporate strategies, the difficulties of predicting what will make us happy in the future, the challenges of properly framing risks at work and at home, the profound effect of cognitive biases on everything from playing the stock market to planning the next vacation—each of these can be understood only by knowing how the two systems work together to shape our judgments and decisions.",
                "Daniel Kahneman",
                R.drawable.thinkingfastandslow,
                25,
                20
        ));
        addBook(new BookModel(
                "The Tipping Point",
                "The tipping point is that magic moment when an idea, trend, or social behavior crosses a threshold, tips, and spreads like wildfire. Just as a single sick person can start an epidemic of the flu, so too can a small but precisely targeted push cause a fashion trend, the popularity of a new product, or a drop in the crime rate. This widely acclaimed bestseller, in which Malcolm Gladwell explores and brilliantly illuminates the tipping point phenomenon, is already changing the way people throughout the world think about selling products and disseminating ideas.",
                "Malcolm Gladwell",
                R.drawable.thetippingpoint,
                22,
                5
        ));

    }
}
