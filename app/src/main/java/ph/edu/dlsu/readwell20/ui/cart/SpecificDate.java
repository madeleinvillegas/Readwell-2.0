package ph.edu.dlsu.readwell20.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;
import ph.edu.dlsu.readwell20.ui.home.HomeFragment;

public class SpecificDate extends AppCompatActivity {
    @Override
    @SuppressLint({"ResourceType", "DefaultLocale"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_date);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(TransactionHistory.selected);

        ArrayList<String> specEntries = null;
        for (DateEntry temp : TransactionHistory.specEntries) {
            if (temp.date.equals(TransactionHistory.selected)) {
                specEntries = temp.entries;
                break;
            }
        }

        ArrayList<Book> books = new ArrayList<>();
        assert specEntries != null;
        for (String temp : specEntries) {
            String[] item = temp.split(",,,");
            for (Book tempBook : HomeFragment.books) {
                if (tempBook.title.equals(item[0])) {
                    tempBook.count = Integer.parseInt(item[1]);
                    books.add(tempBook);
                    break;
                }
            }
        }

        ListView listView = findViewById(R.id.spec_date_list);
        SpecificDateAdapter adapter = new SpecificDateAdapter(this, R.layout.fragment_specific_item, books);
        listView.setAdapter(adapter);

        double totalPrice = 0;
        for (Book book : books) {
            totalPrice += Double.parseDouble(book.price) * book.count * 53;
        }

//        TextView total = findViewById(R.id.spec_price);
//        total.setText(String.format("PHP %.2f", totalPrice));
    }
}