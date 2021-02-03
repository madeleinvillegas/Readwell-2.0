package ph.edu.dlsu.readwell20;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        backToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(context, UserLogin.class);
            startActivity(intent);
        });

        signUpClick.setOnClickListener(view -> createAccount());
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
            loading.cancel();
        }
    }

    @SuppressLint("ShowToast")
    private void checkEmail(final String username, final String password) {
        Toast toast = Toast.makeText(getApplicationContext(), "Successfully Created Account", Toast.LENGTH_SHORT);
        if (db.doesUserExists(username)) {
            toast = Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_SHORT);
        } else {
            db.insertDataIntoLogin(username, password);
            Intent main = new Intent(UserSignup.this, UserLogin.class);
            startActivity(main);
        }
        toast.show();
    }
}