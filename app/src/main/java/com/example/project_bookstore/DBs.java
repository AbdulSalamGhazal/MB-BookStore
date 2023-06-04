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
                list.add(getBookById(id));
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
        addBook(new BookModel(
                "Burned Out",
                "Eric Philson came to the Children's Hospital of Biloxi with a goal ― to build the cardiac intensive care unit from the ground up. The physician faces insurmountable the devastating aftereffects of a hurricane, deprivation of essential staff and resources, and a cardiac surgeon resistant to change. Dr. Philson will do whatever it takes to help improve the care for children, until the job begins to take a toll on his free time, marriage, and health. How much is he willing to sacrifice? Working hundred-hour weeks, overcoming the impossible, and facing personal ruin are only the start of what he will face. Does he have the grit and determination to do what's right, even if it costs him everything?",
                "Dean Mafako",
                R.drawable.burnedout,
                15.5,
                20
        ));
        addBook(new BookModel(
                "Magical Elements",
                "In this unique alphabet book, members of the Metal Horn Unicorn Tribe present 26 Magical Elements of the Periodic Table in alphabetical order.  Each member of the tribe has a metal horn and hooves. They also have magical powers based on the properties of their metals.",
                "Sybrina Durant",
                R.drawable.magicalelements ,
                5,
                50
        ));
        addBook(new BookModel(
                "Beethoven",
                "Jan Swafford’s biographies of Charles Ives and Johannes Brahms have established him as a revered music historian, capable of bringing his subjects vibrantly to life. His magnificent new biography of Ludwig van Beethoven peels away layers of legend to get to the living, breathing human being who composed some of the world’s most iconic music. Swafford mines sources never before used in English-language biographies to reanimate the revolutionary ferment of Enlightenment-era Bonn, where Beethoven grew up and imbibed the ideas that would shape all of his future work. Swafford then tracks his subject to Vienna, capital of European music, where Beethoven built his career in the face of critical incomprehension, crippling ill health, romantic rejection, and “fate’s hammer,” his ever-encroaching deafness. Throughout, Swafford offers insightful readings of Beethoven’s key works. More than a decade in the making, this will be the standard Beethoven biography for years to come. ",
                "Jan Swafford",
                R.drawable.beethoven ,
                25,
                25
        ));
        addBook(new BookModel(
                "The Psychology of Money",
                "Timeless lessons on wealth, greed, and happiness doing well with money isn’t necessarily about what you know. It’s about how you behave. And behavior is hard to teach, even to really smart people. How to manage money, invest it, and make business decisions are typically considered to involve a lot of mathematical calculations, where data and formulae tell us exactly what to do. But in the real world, people don’t make financial decisions on a spreadsheet. They make them at the dinner table, or in a meeting room, where personal history, your unique view of the world, ego, pride, marketing, and odd incentives are scrambled together. In the psychology of money, the author shares 19 short stories exploring the strange ways people think about money and teaches you how to make better sense of one of life’s most important matters." ,
                "Morgan Housel",
                R.drawable.psychologyofmoney ,
                30,
                10
        ));
        addBook(new BookModel(
                "The Silent Patient",
                "Alicia Berenson’s life is seemingly perfect. One evening her husband Gabriel returns home late from a fashion shoot, and Alicia shoots him five times in the face, and then never speaks another word. Alicia’s refusal to talk, or give any kind of explanation, turns a domestic tragedy into something far grander, a mystery that captures the public imagination and casts Alicia into notoriety. The price of her art skyrockets, and she, the silent patient, is hidden away from the tabloids and spotlight at the Grove, a secure forensic unit in North London. Theo Faber is a criminal psychotherapist who has waited a long time for the opportunity to work with Alicia. His determination to get her to talk and unravel the mystery of why she shot her husband takes him down a twisting path into his own motivations–a search for the truth that threatens to consume him.",
                "Alex Michaelides",
                R.drawable.thesilentpatient ,
                17,
                10
        ));
        addBook(new BookModel(
                "The Design of Everyday Things",
                "The Design of Everyday Things is a best-selling book by cognitive scientist and usability engineer Donald Norman about how design serves as the communication between object and user, and how to optimize that conduit of communication in order to make the experience of using the object pleasurable. One of the main premises of the book is that although people are often keen to blame themselves when objects appear to malfunction, it is not the fault of the user but rather the lack of intuitive guidance that should be present in the design.",
                "Donald A. Norman",
                R.drawable.designofeveryday ,
                29.99,
                16
        ));
        addBook(new BookModel(
                "How Not to Die\n",
                "From the physician behind the wildly popular website NutritionFacts.org, How Not to Die reveals the groundbreaking scientific evidence behind the only diet that can prevent and reverse many of the causes of disease-related death. The simple truth is that most doctors are good at treating acute illnesses but bad at preventing chronic disease. The 15 leading causes of premature death -- illnesses such as heart disease, cancer, diabetes, Parkinson's, high blood pressure, and others -- claim the lives of 1.6 million Americans annually. This doesn't have to be the case. By following Dr. Greger's advice, all of it backed up by strong scientific evidence, you will learn which foods to eat and which lifestyle changes to make to help prevent or fight these diseases and to live longer. In addition to showing what to eat to help treat the top 15 causes of death, How Not to Die includes Dr. Greger's Daily Dozen, a checklist of the foods and activities we should try to incorporate into our daily routines. Full of practical, actionable advice and surprising, cutting-edge nutritional science, these doctor's orders are just what we need to live longer, healthier lives. - Jacket flap.",
                "Gene Stone",
                R.drawable.hownottodie ,
                9,
                10
        ));
        addBook(new BookModel(
                "Salt, Fat, Acid, Heat",
                "In the tradition of The Joy of Cooking and How to Cook Everything comes Salt, Fat, Acid, Heat, an ambitious new approach to cooking by a major new culinary voice. Chef and writer Samin Nosrat has taught everyone from professional chefs to middle school kids to author Michael Pollan to cook using her revolutionary, yet simple, philosophy. Master the use of just four elements—Salt, which enhances flavor; Fat, which delivers flavor and generates texture; Acid, which balances flavor; and Heat, which ultimately determines the texture of food—and anything you cook will be delicious. By explaining the hows and whys of good cooking, Salt, Fat, Acid, Heat will teach and inspire a new generation of cooks how to confidently make better decisions in the kitchen and cook delicious meals with any ingredients, anywhere, at any time.",
                "Samin Nosrat",
                R.drawable.saltfatacidheat ,
                14,
                20
        ));
        addBook(new BookModel(
                "Milk and Honey",
                "The book is divided into four chapters, each chapter serves a different purpose. They deal with different pains; heal different heartaches. Milk and honey takes readers through a journey of the most bitter moments in life and finds sweetness in them, because there is sweetness everywhere If you are just willing to look.",
                "Rupi Kaur",
                R.drawable.milkandhoney ,
                18.5,
                15
        ));

    }
}
