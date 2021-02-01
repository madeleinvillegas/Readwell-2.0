package ph.edu.dlsu.readwell20.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.Database;
import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;
import ph.edu.dlsu.readwell20.ui.home.HomeFragment;

public class CartFragment extends Fragment {
    public static CartStack cartStack = new CartStack();
    private static boolean init = true;
    private ArrayList<Book> books;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        if (init) {
            initCart();
            init = false;
        }

        ListView listView = root.findViewById(R.id.home_list);
        books = cartStack.toArray();
        if (books.size() > 0) {
            CartAdapter adapter = new CartAdapter(requireActivity(), R.layout.fragment_cart_item, books);
            listView.setAdapter(adapter);
        }

        FloatingActionButton btnSave = root.findViewById(R.id.save);
        btnSave.setOnClickListener(v -> ButtonSave());
        FloatingActionButton btnCheckout = root.findViewById(R.id.checkout);

        return root;
    }

    private void ButtonSave() {
        StringBuilder toSave = new StringBuilder();
        for (Book temp : books) toSave.append(temp.title).append(",,,").append(temp.count).append(",,,");

        Database db = new Database(getContext());
        System.out.println(toSave.toString());
        db.updateData(toSave.toString());
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