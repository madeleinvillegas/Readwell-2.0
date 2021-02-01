package ph.edu.dlsu.readwell20.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class CartFragment extends Fragment {
    public static CartStack cartStack = new CartStack();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        ListView listView = root.findViewById(R.id.home_list);
        Book[] books = cartStack.toArray();
        if (books.length > 0) {
            CartAdapter adapter = new CartAdapter(requireActivity(), R.layout.fragment_cart_item, books);
            listView.setAdapter(adapter);
        }

        return root;
    }

    private Book[] getSampleBooks() {
        return new Book[] {
                new Book("1984", "George Orwell", "https://covers.openlibrary.org/b/id/8579180-L.jpg"),
                new Book("To Kill a Mockingbird", "Harper Lee", "https://covers.openlibrary.org/b/id/8410894-L.jpg"),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", "https://covers.openlibrary.org/b/id/8458093-L.jpg"),
                new Book("Memoirs of a Geisha", "Arthur Golden", "https://covers.openlibrary.org/b/id/10541425-L.jpg"),
                new Book("LIFE OF PI", "Yann Martel", "https://covers.openlibrary.org/b/id/529809-L.jpg"),
                new Book("The Fault in Our Stars", "John Green", "https://covers.openlibrary.org/b/id/7285167-L.jpg")
        };
    }
}