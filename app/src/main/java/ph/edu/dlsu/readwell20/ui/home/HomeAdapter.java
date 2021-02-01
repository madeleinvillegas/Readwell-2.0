package ph.edu.dlsu.readwell20.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import ph.edu.dlsu.readwell20.Book;
import ph.edu.dlsu.readwell20.BookDetails;
import ph.edu.dlsu.readwell20.MainActivity;
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

        RelativeLayout item = convertView.findViewById(R.id.home_item);
        item.setOnClickListener(v -> {
            MainActivity.lastTab = 0;
            BookDetails.forViewing = getItem(position);
            Intent intent = new Intent(context, BookDetails.class);
            context.startActivity(intent);
        });
        TextView title = convertView.findViewById(R.id.home_item_title);
        title.setText(getItem(position).title);
        TextView author = convertView.findViewById(R.id.home_item_author);
        author.setText(getItem(position).author);
        ImageView imageView = convertView.findViewById(R.id.home_item_cover);
        Picasso.get().load(getItem(position).thumbnail).resize(100, 120).centerCrop().into(imageView);

//        if(BookDetails.forViewing.recoTitle1.equals(getItem(position).title)) {
//            BookDetails.forViewing.recoAuthor1 = getItem(position).author;
//            BookDetails.forViewing.recoImg1 = getItem(position).thumbnail;
//        }
//        if(BookDetails.forViewing.recoTitle2.equals(getItem(position).title)) {
//            BookDetails.forViewing.recoAuthor2 = getItem(position).author;
//            BookDetails.forViewing.recoImg2 = getItem(position).thumbnail;
//        }
//        if(BookDetails.forViewing.recoTitle3.equals(getItem(position).title)) {
//            BookDetails.forViewing.recoAuthor3 = getItem(position).author;
//            BookDetails.forViewing.recoImg3 = getItem(position).thumbnail;
//        }

        return convertView;
    }
}