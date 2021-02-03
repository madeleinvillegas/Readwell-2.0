package ph.edu.dlsu.readwell20.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.Database;
import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;

public class CheckoutConfirm extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_confirm);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        double total = 0;
        for (Book book : CartFragment.cartStack.toArray()) {
            total += book.count * Double.parseDouble(book.price) * 53;
        }
        TextView totalPrice = findViewById(R.id.checkout_price);
        totalPrice.setText(String.format("PHP %.2f", total));

        Button confirm = findViewById(R.id.checkout_button);
        confirm.setOnClickListener(v -> updateTransaction());
    }

    @SuppressLint({"SimpleDateFormat", "ShowToast"})
    private void updateTransaction() {
        Toast.makeText(this, "Transaction Completed.", Toast.LENGTH_SHORT).show();

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        MainActivity.transactions.append(simpleDateFormat.format(new Date())).append(",,,");
        for (Book temp : CartFragment.cartStack.toArray()) {
            MainActivity.transactions.append(temp.title).append(",,,").append(temp.count).append(",,,");
        }
        MainActivity.transactions.append(";;;");

        MainActivity.lastCart = null;
        CartFragment.cartStack.clear();
        CartFragment.books.clear();
        CartFragment.adapter.notifyDataSetChanged();

        Database db = new Database(this);
        db.updateUser();

        finish();
    }
}