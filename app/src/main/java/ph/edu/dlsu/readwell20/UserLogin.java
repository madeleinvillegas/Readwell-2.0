package ph.edu.dlsu.readwell20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {
    private EditText loginUsername;
    private EditText loginPassword;
    private ProgressDialog loading;
    private Context context;
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        context = UserLogin.this;
        db = new Database(context);
        db.insertDataIntoLogin("madel", "123");     // Remove this later

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPass);
        Button loginButton = findViewById(R.id.btnLogin);
        TextView signUpHere = findViewById(R.id.signUpHere);
        loading = new ProgressDialog(this);

        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this, UserSignup.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login() {
        String user = loginUsername.getText().toString();
        String pass = loginPassword.getText().toString();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "You forgot to add your username", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "You forgot to add your password", Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setTitle("Login");
            loading.setMessage("Please wait while we are checking your account.");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            checkCredentials(user, pass);
        }
    }
    public void checkCredentials(final String username, final String password) {
        SQLiteDatabase data = db.getReadableDatabase();
        data.beginTransaction();
        String query = "SELECT username, password FROM " + Database.login;
        Cursor cursor = data.rawQuery(query, null);
        Toast toast;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (username.equals(cursor.getString(cursor.getColumnIndex("username")))
                        && password.equals(cursor.getString(cursor.getColumnIndex("password")))) {
                    toast = Toast.makeText(getApplicationContext(),
                            "Successfully logged in",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    Intent main = new Intent(UserLogin.this, MainActivity.class);
                    startActivity(main);
                    break;
                } else {
                    toast = Toast.makeText(getApplicationContext(),
                            "Failed Retry",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
            }
            data.setTransactionSuccessful();
            data.endTransaction();
            data.close();
        }
    }
}