package ph.edu.dlsu.readwell20.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.Database;
import ph.edu.dlsu.readwell20.R;

public class CartFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        ListView listView = root.findViewById(R.id.home_list);
        CartAdapter adapter = new CartAdapter(requireActivity(), R.layout.fragment_cart_item, Database.getSampleBooks());
        listView.setAdapter(adapter);

        return root;
    }
}