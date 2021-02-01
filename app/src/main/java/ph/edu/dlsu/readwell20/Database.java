package ph.edu.dlsu.readwell20;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readwelldb";
    private static final int DATABASE_VERSION = 1;
    static final String books = "books";
    static final String login = "login";

    // Add columns as needed
    private static final String CREATE_TABLE_BOOKS = "CREATE TABLE IF NOT EXISTS " + books +
            "(stat_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price TEXT)";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + login +
            "(login_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";

    private static final String DELETE_TABLE_BOOKS = "DROP TABLE IF EXISTS " + books;
    private static final String DELETE_TABLE_LOGIN = "DROP TABLE IF EXISTS " + login;

    public Database(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKS);
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_BOOKS);
        db.execSQL(DELETE_TABLE_LOGIN);
        // Create tables again
        onCreate(db);
    }

    public void insertDataIntoLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        try {
            ContentValues values = new ContentValues();
            values.put("username", username);
            values.put("password", password);
            db.insert(login, null, values);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public boolean doesUserExists(String username) {
        SQLiteDatabase temp = this.getReadableDatabase();
        Cursor cursor = temp.rawQuery("SELECT username FROM login", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex("username")).equals(username)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public int getBooksCount() {
        SQLiteDatabase temp = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = temp.rawQuery("SELECT name FROM books", null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public static Book[] getSampleBooks() {

        // make a python instance
        Python py = Python.getInstance();

        //get recommendations file
        PyObject pyobj = py.getModule("recommendations");

        //create object to store data
        PyObject obj = (null);

        //call function getBooks in recommendations.py
        obj = pyobj.callAttr("getBooks");

        //convert return value to java
        String[][] books = obj.toJava(String[][].class);

        // create ArrayList of books to easily append to and convert back to string
        List<Book> Books = new ArrayList<>();

        //create new book and add to array list
        for(int i=0; i<books.length; i++){
            Books.add(new Book(books[i][0], books[i][1], books[i][2], books[i][3], books[i][4],
                    books[i][5], books[i][6], books[i][7], books[i][8], 300, books[i][9]
                    , books[i][10],  books[i][11], books[i][12]));
        }
        //pass book array
        return (Book[]) Books.toArray();
//        return new Book[] {
//                new Book("1984", "George Orwell", "https://covers.openlibrary.org/b/id/8579180-L.jpg"),
//                new Book("To Kill a Mockingbird", "Harper Lee", "https://covers.openlibrary.org/b/id/8410894-L.jpg"),
//                new Book("The Great Gatsby", "F. Scott Fitzgerald", "https://covers.openlibrary.org/b/id/8458093-L.jpg"),
//                new Book("Memoirs of a Geisha", "Arthur Golden", "https://covers.openlibrary.org/b/id/10541425-L.jpg"),
//                new Book("LIFE OF PI", "Yann Martel", "https://covers.openlibrary.org/b/id/529809-L.jpg"),
//                new Book("The Fault in Our Stars", "John Green", "https://covers.openlibrary.org/b/id/7285167-L.jpg")
//        };
    }
}