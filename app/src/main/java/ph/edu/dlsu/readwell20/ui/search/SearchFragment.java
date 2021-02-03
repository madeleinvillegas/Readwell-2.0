package ph.edu.dlsu.readwell20.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;
import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.BookDetails;

public class SearchFragment extends Fragment {
    private EditText search;
    public static String previousSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        search = root.findViewById(R.id.searchInput);
        Button searchBtn = root.findViewById(R.id.btnSearch);
        searchBtn.setOnClickListener(view -> searchTheBook());
        search.setText(previousSearch);
        return root;
    }
    public void searchTheBook() {
        previousSearch = search.getText().toString();

        if (TextUtils.isEmpty(previousSearch)) {
            Toast.makeText(getActivity(), "Please enter a keyword on the search bar", Toast.LENGTH_SHORT).show();
        }

        // make a python instance
        Python py = Python.getInstance();

        //get recommendations file
        PyObject pyobj = py.getModule("search");

        //create object to store data
        PyObject obj;

        //call function getBooks in recommendations.py
        obj = pyobj.callAttr("load_data", previousSearch);

        //convert return value to java
        Object[][] tempBooks = obj.toJava(Object[][].class);
        if (tempBooks.length==0) {
            Toast.makeText(getActivity(), "Please enter a keyword on the search bar 1", Toast.LENGTH_SHORT).show();
        } else {
            BookDetails.forViewing = new Book(String.valueOf(tempBooks[0][0]), String.valueOf(tempBooks[0][1]),String.valueOf(tempBooks[0][3]),
                    String.valueOf(tempBooks[0][5]), String.valueOf(tempBooks[0][2]),
                    String.valueOf(tempBooks[0][4]), String.valueOf(tempBooks[0][7]),
                    String.valueOf(tempBooks[0][6]), String.valueOf(tempBooks[0][9]),
                    String.valueOf(tempBooks[0][8]), String.valueOf(tempBooks[0][10]),
                    String.valueOf(tempBooks[0][11]), String.valueOf(tempBooks[0][12]), String.valueOf(tempBooks[0][13]));
            MainActivity.lastTab = 1;
            Intent intent = new Intent(getContext(), BookDetails.class);
            startActivity(intent);
        }

        if (TextUtils.isEmpty(previousSearch)) {
            Toast.makeText(getActivity(), "Please enter a keyword on the search bar 2", Toast.LENGTH_SHORT).show();
        }
    }
}