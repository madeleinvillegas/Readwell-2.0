package ph.edu.dlsu.readwell20.ui.home;

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

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.MainActivity;
import ph.edu.dlsu.readwell20.R;

public class HomeFragment extends Fragment {
    public static Book[] books = getSampleBooks();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listView = root.findViewById(R.id.home_list);
        HomeAdapter adapter = new HomeAdapter(requireActivity(), R.layout.fragment_home_item, books);
        listView.setAdapter(adapter);

        return root;
    }

    public void onStart(){
        super.onStart();
        //update your fragment
    }


    private static Book[] getSampleBooks() {
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
        ArrayList<Object[]> sortBooks = new ArrayList<>();
        for(int i=0; i<tempBooks.length; i++){
            if(tempBooks[i][8].equals(MainActivity.lastView)){
                sortBooks.add(0, tempBooks[i]);
            }
            else{
                sortBooks.add(tempBooks[i]);
            }
        }
        //Object[][] sortedBooks = new Object[sortBooks.size()][];
        for(int i=0; i<sortBooks.size(); i++){
            tempBooks[i] = sortBooks.get(i);
        }
        System.out.println(tempBooks[0][7]);



        Book[] books = new Book[tempBooks.length];
        for (int i = 0; i < tempBooks.length; i++) {
            String tempAuthor = String.valueOf(tempBooks[i][2]);
            String author = tempAuthor.endsWith(";") ? tempAuthor.substring(0, tempAuthor.length() - 1) : tempAuthor;
            books[i] = new Book(String.valueOf(tempBooks[i][1]), author,String.valueOf(tempBooks[i][4]),
                    String.valueOf(tempBooks[i][6]), String.valueOf(tempBooks[i][3]),
                    String.valueOf(tempBooks[i][5]), String.valueOf(tempBooks[i][8]),
                    String.valueOf(tempBooks[i][7]), String.valueOf(tempBooks[i][10]),
                    String.valueOf(tempBooks[i][9]), String.valueOf(tempBooks[i][11]),
                    String.valueOf(tempBooks[i][12]), String.valueOf(tempBooks[i][13]), String.valueOf(tempBooks[i][14]));
        }

        //pass book array
        return books;
    }
}