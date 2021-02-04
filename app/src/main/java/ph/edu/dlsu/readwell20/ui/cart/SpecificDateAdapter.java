package ph.edu.dlsu.readwell20.ui.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;
import ph.edu.dlsu.readwell20.ui.home.HomeFragment;

public class SpecificDateAdapter extends ArrayAdapter<SpecificDateEntry> {
    private final Context context;
    private final int resource;

    public SpecificDateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SpecificDateEntry> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n", "ResourceType"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView title = convertView.findViewById(R.id.spec_item_title);
        title.setText(getItem(position).book.title);
        TextView author = convertView.findViewById(R.id.spec_item_author);
        author.setText(getItem(position).book.author);
        TextView counter = convertView.findViewById(R.id.spec_item_count);
        counter.setText(String.valueOf(getItem(position).counter));
        ImageView imageView = convertView.findViewById(R.id.spec_item_cover);
        Picasso.get().load(getItem(position).book.thumbnail).resize(100, 120).centerCrop().into(imageView);

        return convertView;
    }
}