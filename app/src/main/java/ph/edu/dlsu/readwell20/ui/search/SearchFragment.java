package ph.edu.dlsu.readwell20.ui.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class SearchFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final EditText search = root.findViewById(R.id.searchInput);
        Button searchBtn = root.findViewById(R.id.btnSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            // On click of the search button
            @Override
            public void onClick(View view) {
                // Input is stored on whatToSearch
                String whatToSearch = search.getText().toString();
                if (TextUtils.isEmpty(whatToSearch)) {
                    Toast.makeText(getActivity(), "Please enter a title on the search bar", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Chaquopy stuff that doesn't work yet
//                    Python py = Python.getInstance();
//                    final PyObject pyobj = py.getModule("main");
//                    List<PyObject> obj = pyobj.callAttr("search", whatToSearch).asList();
//                    Intent intent = new Intent(getActivity(), Book.class);
//                    String[] data = {obj.get(0).toString(), obj.get(1).toString(), obj.get(2).toString(),
//                            obj.get(3).toString(), obj.get(4).toString(), obj.get(5).toString()};
//                    intent.putExtra("data", data);
//                    startActivity(intent);
                }

            }
        });

        return root;
    }
}