package ph.edu.dlsu.readwell20.ui.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class CartAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final int resource;
    private final ArrayList<Book> books;

    public CartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.books = objects;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n", "ResourceType"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView title = convertView.findViewById(R.id.cart_item_title);
        title.setText(getItem(position).title);
        TextView author = convertView.findViewById(R.id.cart_item_author);
        author.setText(getItem(position).author);
        TextView count = convertView.findViewById(R.id.cart_item_count);
        count.setText(Integer.toString(getItem(position).count));
        ImageView imageView = convertView.findViewById(R.id.cart_item_cover);
        Picasso.get().load(getItem(position).thumbnail).resize(100, 120).centerCrop().into(imageView);

        Button btnAdd = convertView.findViewById(R.id.add_button);


        btnAdd.setOnClickListener(v -> {
            getItem(position).count++;
            count.setText(Integer.toString(getItem(position).count));
        });
        Button btnMinus = convertView.findViewById(R.id.minus_button);
        btnMinus.setOnClickListener(v -> {
            getItem(position).count--;
            count.setText(Integer.toString(getItem(position).count));
            if (getItem(position).count == 0) {
                CartFragment.cartStack.remove(getItem(position));
                books.remove(getItem(position));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}