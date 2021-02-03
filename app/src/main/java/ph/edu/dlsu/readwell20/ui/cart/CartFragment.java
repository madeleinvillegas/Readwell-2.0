package ph.edu.dlsu.readwell20.ui.cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.Database;
import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;
import ph.edu.dlsu.readwell20.ui.home.HomeFragment;

public class CartFragment extends Fragment {
    public static CartStack cartStack = new CartStack();
    public static ArrayList<Book> books;
    private static boolean init = true;
    public static CartAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        if (MainActivity.lastCart != null && init) {
            initCart();
            init = false;
        }

        ListView listView = root.findViewById(R.id.home_list);
        books = cartStack.toArray();
        if (books.size() > 0) {
            adapter = new CartAdapter(requireActivity(), R.layout.fragment_cart_item, books);
            listView.setAdapter(adapter);
        }

        FloatingActionButton btnSave = root.findViewById(R.id.save);
        btnSave.setOnClickListener(v -> {
            if (books.size() > 0) ButtonSave();
            else Toast.makeText(getContext(), "Cart is Empty", Toast.LENGTH_SHORT).show();
        });
        FloatingActionButton btnCheckout = root.findViewById(R.id.checkout);
        btnCheckout.setOnClickListener(v -> {
            if (books.size() > 0) ButtonCheckout();
            else Toast.makeText(getContext(), "Cart is Empty", Toast.LENGTH_SHORT).show();
        });
        FloatingActionButton btnHistory = root.findViewById(R.id.history);
//        btnHistory.setOnClickListener(v -> ButtonCheckout());

        return root;
    }

    private void ButtonCheckout() {
        MainActivity.lastTab = 2;
        Intent intent = new Intent(getContext(), CheckoutConfirm.class);
        startActivity(intent);
    }

    private void ButtonSave() {
        CartStackItem bottom = cartStack.getRoot();
        StringBuilder toSave = new StringBuilder();
        CartStackItem temp = bottom;

        while (temp.top != null) {
            toSave.append(temp.top.book.title).append(",,,").append(temp.top.book.count).append(",,,");
            temp = temp.top;
        }

        MainActivity.lastCart = toSave.toString();
        Database db = new Database(getContext());
        db.updateUser();
    }

    private void initCart() {
        String[] lastCartStr = MainActivity.lastCart.split(",,,");
        for (int i = 0; i < lastCartStr.length; i += 2) {
            for (Book book : HomeFragment.books) {
                if (book.title.equals(lastCartStr[i])) {
                    book.count = Integer.parseInt(lastCartStr[i + 1]);
                    cartStack.push(book);
                }
            }
        }
    }
}