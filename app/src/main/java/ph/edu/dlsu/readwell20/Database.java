package ph.edu.dlsu.readwell20;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

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
        return new Book[] {
                new Book("Diary 1", "Casio"),
                new Book("Diary 2", "Casio"),
                new Book("Diary 3", "Casio"),
                new Book("Diary 4", "Casio"),
                new Book("Diary 5", "Casio"),
                new Book("Diary 6", "Casio"),
                new Book("Diary 7", "Casio"),
                new Book("Diary 8", "Casio"),
                new Book("Diary 9", "Casio"),
                new Book("Diary 10", "Casio")
        };
    }
}