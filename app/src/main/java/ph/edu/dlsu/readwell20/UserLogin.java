package ph.edu.dlsu.readwell20;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        // Setup the database
        context = UserLogin.this;
        db = new Database(this);

        // Get user input
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPass);
        Button loginButton = findViewById(R.id.btnLogin);
        TextView signUpHere = findViewById(R.id.signUpHere);
        loading = new ProgressDialog(this);

        signUpHere.setOnClickListener(view -> {
            Intent intent = new Intent(UserLogin.this, UserSignup.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> login());
    }

    private void login() {
        String user = loginUsername.getText().toString();
        String pass = loginPassword.getText().toString();

        // Checking user input
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "You forgot to add your username", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "You forgot to add your password", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("Login");
            loading.setMessage("Please wait while we are checking your account.");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            checkCredentials(user, pass);
            loading.cancel();
        }
    }

    @SuppressLint("ShowToast")
    public void checkCredentials(final String username, final String password) {
        SQLiteDatabase data = db.getReadableDatabase();

        // SQL query
        String query = "SELECT login_id, username, password, saveCart, transactions FROM " + Database.login;
        Cursor cursor = data.rawQuery(query, null);

        Toast toast = Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT);

        while (cursor.moveToNext()) {
            // Username and password inputted is found on the database
            String tmpUser = cursor.getString(cursor.getColumnIndex("username"));
            String tmpPass = cursor.getString(cursor.getColumnIndex("password"));
            if (username.equals(tmpUser) && password.equals(tmpPass)) {
                MainActivity.username = username;
                MainActivity.password = password;
                MainActivity.ID = cursor.getInt(cursor.getColumnIndex("login_id"));
                MainActivity.lastCart = cursor.getString(cursor.getColumnIndex("saveCart"));
                String temp = cursor.getString(cursor.getColumnIndex("transactions"));
                MainActivity.transactions = new StringBuilder(temp == null ? "" : temp);

                toast = Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT);
                Intent main = new Intent(context, MainActivity.class);
                startActivity(main);
                break;
            }
        }
        toast.show();
        cursor.close();
        data.close();
    }
}