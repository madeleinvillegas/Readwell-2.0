package ph.edu.dlsu.readwell20.ui.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class CartAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final int resource;

    public CartAdapter(@NonNull Context context, int resource, @NonNull Book[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView text = convertView.findViewById(R.id.cart_item_title);
        text.setText(getItem(position).title);
        ImageView imageView = convertView.findViewById(R.id.cart_item_cover);
        Picasso.get().load("https://covers.openlibrary.org/b/id/8739161-L.jpg").resize(150, 150).centerCrop().into(imageView);
        return convertView;
    }
}