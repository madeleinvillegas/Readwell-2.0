package ph.edu.dlsu.readwell20.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.BookDetails;
import ph.edu.dlsu.readwell20.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = root.findViewById(R.id.home_list);
        HomeAdapter adapter = new HomeAdapter(requireActivity(), R.layout.fragment_home_item, getSampleBooks());
        listView.setAdapter(adapter);

        return root;
    }

    private Book[] getSampleBooks() {
        // make a python instance
        Python py = Python.getInstance();

        //get recommendations file
        PyObject pyobj = py.getModule("recommendations");

        //create object to store data
        PyObject obj;

        //call function getBooks in recommendations.py
        obj = pyobj.callAttr("getBooks");

        //convert return value to java
        Object[][] tempBooks = obj.toJava(Object[][].class);

        Book[] books = new Book[tempBooks.length];
        for (int i = 0; i < tempBooks.length; i++) {
            books[i] = new Book(String.valueOf(tempBooks[i][0]), String.valueOf(tempBooks[i][1]),
                    String.valueOf(tempBooks[i][2]), String.valueOf(tempBooks[i][3]),
                    String.valueOf(tempBooks[i][4]), String.valueOf(tempBooks[i][5]),
                    String.valueOf(tempBooks[i][6]), String.valueOf(tempBooks[i][7]),
                    String.valueOf(tempBooks[i][8]), "300",
                    String.valueOf(tempBooks[i][9]), String.valueOf(tempBooks[i][10]),
                    String.valueOf(tempBooks[i][11]));
        }

        //pass book array
        return books;

//        return new Book[] {
//                new Book("1984", "George Orwell", "https://covers.openlibrary.org/b/id/8579180-L.jpg"),
//                new Book("To Kill a Mockingbird", "Harper Lee", "https://covers.openlibrary.org/b/id/8410894-L.jpg"),
//                new Book("The Great Gatsby", "F. Scott Fitzgerald", "https://covers.openlibrary.org/b/id/8458093-L.jpg"),
//                new Book("Memoirs of a Geisha", "Arthur Golden", "https://covers.openlibrary.org/b/id/10541425-L.jpg"),
//                new Book("LIFE OF PI", "Yann Martel", "https://covers.openlibrary.org/b/id/529809-L.jpg"),
//                new Book("The Fault in Our Stars", "John Green", "https://covers.openlibrary.org/b/id/7285167-L.jpg")
//        };
    }

    public void goToViewDetails() {
//        Intent intent = new Intent(getActivity(), BookDetails.class);
        // convert to string array
//                String[] data = {obj1.get(0).toString(), obj1.get(1).toString(), obj1.get(2).toString(),
//                        obj1.get(3).toString(), obj1.get(4).toString(),obj1.get(5).toString()};
//                intent.putExtra("data", data);
//        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button button = view.findViewById(R.id.button);
//        button.setOnClickListener(v -> test());
    }
}