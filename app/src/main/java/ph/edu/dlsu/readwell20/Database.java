package ph.edu.dlsu.readwell20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "readwelldb";
    private static final int DATABASE_VERSION = 1;
    static final String login = "login";

    // Add columns as needed
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + login +
            "(login_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, " +
            "password TEXT, saveCart TEXT, transactions TEXT)";

    private static final String DELETE_TABLE_LOGIN = "DROP TABLE IF EXISTS " + login;

    public Database(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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

    public void updateUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", MainActivity.username);
        contentValues.put("password", MainActivity.password);
        contentValues.put("saveCart", MainActivity.lastCart);
        contentValues.put("transactions", MainActivity.transactions.toString());
        db.update(login, contentValues, "login_id = ?", new String[]{String.valueOf(MainActivity.ID)});
    }
}