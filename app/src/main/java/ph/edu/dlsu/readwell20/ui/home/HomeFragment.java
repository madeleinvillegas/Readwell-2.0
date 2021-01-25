package ph.edu.dlsu.readwell20.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ph.edu.dlsu.readwell20.BookDetails;
import ph.edu.dlsu.readwell20.R;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Chaquopy
//        Python py = Python.getInstance();
//        final PyObject pyobj = py.getModule("main");
//        final List<PyObject> obj1 = pyobj.callAttr("best").asList();
//
//        title1.setText(obj1.get(0).toString());
//        author1.setText(obj1.get(1).toString());
//        rating1.setText(obj1.get(2).toString());
//        genre1.setText(obj1.get(3).toString());
//        new DownloadImageTask((ImageView) root.findViewById(R.id.book_image1)).execute(obj1.get(4).toString());

//        book1.setOnClickListener(view -> goToViewDetails());
        return root;
    }
    public void goToViewDetails() {
        Intent intent = new Intent(getActivity(), BookDetails.class);
        // convert to string array
//                String[] data = {obj1.get(0).toString(), obj1.get(1).toString(), obj1.get(2).toString(),
//                        obj1.get(3).toString(), obj1.get(4).toString(),obj1.get(5).toString()};
//                intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button button = view.findViewById(R.id.button);
//        button.setOnClickListener(v -> test());
    }
}