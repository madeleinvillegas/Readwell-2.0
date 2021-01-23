package ph.edu.dlsu.readwell20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserSignup extends AppCompatActivity {
    private EditText signUpUsername;
    private EditText signUpPassword;
    private CheckBox terms;
    private ProgressDialog loading;
    private Context context;
    private Database db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        context = UserSignup.this;
        db = new Database(context);

        // User input
        signUpUsername = findViewById(R.id.signUpUsername);
        signUpPassword = findViewById(R.id.signUpPass);
        terms = findViewById(R.id.confirm);
        Button signUpClick = findViewById(R.id.btnSignUp);
        TextView backToLogin = findViewById(R.id.logInHere);
        loading = new ProgressDialog(this);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserSignup.this, UserLogin.class);
                startActivity(intent);
            }
        });

        signUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }
    private void createAccount() {
        // Checks input
        String email = signUpUsername.getText().toString();
        String pass = signUpPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "You forgot to add your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "You forgot to add your password", Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 8) {
            Toast.makeText(this, "Your password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
        } else if (!terms.isChecked()) {
            Toast.makeText(this, "You need to consent to our Terms and Conditions and Privacy Policy.", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("Create Account");
            loading.setMessage("Please wait while we are creating you account.");
            loading.setCanceledOnTouchOutside(false);
            loading.show();
            checkEmail(email, pass);
        }
    }
    private void checkEmail(final String username, final String password) {
        // Do checks if email already exists
        SQLiteDatabase data = db.getReadableDatabase();
        data.beginTransaction();
        String query = "SELECT username FROM " + Database.login;
        Cursor cursor = data.rawQuery(query, null);
        Toast toast;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Username and password inputted is found on the database
                if (username.equals(cursor.getString(cursor.getColumnIndex("username")))) {
                    toast = Toast.makeText(getApplicationContext(),
                            "User already exists",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                } else {
                    toast = Toast.makeText(getApplicationContext(),
                            "Successfully created account",
                            Toast.LENGTH_SHORT);
                    toast.show();

                    Intent main = new Intent(UserSignup.this, UserLogin.class);
                    startActivity(main);
                    break;
                }
            }
            data.setTransactionSuccessful();
            data.endTransaction();
            data.close();
        }
    }
}