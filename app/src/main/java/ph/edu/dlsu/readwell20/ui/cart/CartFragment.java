package ph.edu.dlsu.readwell20.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class CartFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        Book[] trial = {
                new Book("Diary 1", "Casio"),
                new Book("Diary 2", "Casio"),
                new Book("Diary 3", "Casio"),
                new Book("Diary 4", "Casio"),
                new Book("Diary 5", "Casio"),
                new Book("Diary 6", "Casio"),
                new Book("Diary 7", "Casio"),
                new Book("Diary 8", "Casio"),
                new Book("Diary 9", "Casio"),
                new Book("Diary 10", "Casio")
        };
        ListView listView = root.findViewById(R.id.cart_list);
        CartAdapter listViewAdapter = new CartAdapter(
                requireActivity(),
                R.layout.fragment_cart_item,
                trial
        );
        listView.setAdapter(listViewAdapter);

        return root;
    }
}