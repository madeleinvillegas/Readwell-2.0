package ph.edu.dlsu.readwell20.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;

public class TransactionHistory extends AppCompatActivity {
    @Override
    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ArrayList<String> dates = new ArrayList<>();
        String[] details = MainActivity.transactions.toString().split(";;;");
        for (String detail : details) {
            String[] entries = detail.split(",,,");
            dates.add(entries[0]);
            DateEntry temp = new DateEntry(entries[0]);
            for (int i = 1; i < entries.length; i += 2) {
                temp.addEntry(entries[i], entries[i + 1]);
            }
        }

        ListView listView = findViewById(R.id.history_list);
        TransactionAdapter adapter = new TransactionAdapter(this, R.layout.activity_transaction_history_item, dates);
        listView.setAdapter(adapter);
    }
}