package ph.edu.dlsu.readwell20;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readwelldc";
    private static final int DATABASE_VERSION = 1;
    static final String books = "books";
    static final String login = "login";

    private static final String CREATE_TABLE_BOOKS = "CREATE TABLE IF NOT EXISTS " + books +
            "(stat_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , price TEXT )";
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + login +
            "(login_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , password TEXT)";

    private static final String DELETE_TABLE_BOOKS="DROP TABLE IF EXISTS " + books;
    private static final String DELETE_TABLE_LOGIN="DROP TABLE IF EXISTS " + login;


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKS);
        db.execSQL(CREATE_TABLE_LOGIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_BOOKS);
        db.execSQL(DELETE_TABLE_LOGIN);
        //Create tables again
        onCreate(db);
    }
    public void insertDataIntoLogin(String username, String password ){
        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        db.beginTransaction(); // Start the transaction.
        ContentValues values;
        try {
            values = new ContentValues();
            values.put("username",username);
            values.put("password",password);
            long i = db.insert(login, null, values);
            Log.i("Insert", i + "");
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public void insertDataIntoBooks(String title, String author) {

    }


}
