package ph.edu.dlsu.readwell20.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.R;

public class HomeAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final int resource;

    public HomeAdapter(@NonNull Context context, int resource, @NonNull Book[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.home_item_cover);
        String url = "https://static.wikia.nocookie.net/meme/images/d/db/Rick-astley.png/revision/latest/top-crop/width/360/height/450?cb=20200713010539";
        Picasso.get().load(url).resize(150, 150).centerCrop().into(imageView);

        return convertView;
    }
}