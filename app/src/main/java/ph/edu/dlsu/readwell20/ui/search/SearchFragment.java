package ph.edu.dlsu.readwell20.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ph.edu.dlsu.readwell20.BookDetails;
import ph.edu.dlsu.readwell20.R;

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
        Intent intent = new Intent(getContext(), BookDetails.class);
        startActivity(intent);

//        String whatToSearch = search.getText().toString();
//        if (TextUtils.isEmpty(whatToSearch)) {
//            Toast.makeText(getActivity(), "Please enter a title on the search bar", Toast.LENGTH_SHORT).show();
//        } else {
            // Chaquopy stuff that doesn't work yet
//            Python py = Python.getInstance();
//            final PyObject pyobj = py.getModule("main");
//            List<PyObject> obj = pyobj.callAttr("search", whatToSearch).asList();
//            Intent intent = new Intent(getActivity(), Book.class);
//            String[] data = {obj.get(0).toString(), obj.get(1).toString(), obj.get(2).toString(),
//                            obj.get(3).toString(), obj.get(4).toString(), obj.get(5).toString()};
//            intent.putExtra("data", data);
//            startActivity(intent);
//        }
    }
}