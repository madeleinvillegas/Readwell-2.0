package ph.edu.dlsu.readwell20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import ph.edu.dlsu.readwell20.ui.cart.CartFragment;
import ph.edu.dlsu.readwell20.ui.home.HomeFragment;

public class BookDetails extends AppCompatActivity {
    public static Book forViewing;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView title = findViewById(R.id.book_title);
        TextView author = findViewById(R.id.book_author);
        TextView rating = findViewById(R.id.book_rating);
        TextView genre = findViewById(R.id.book_genre);
        TextView price = findViewById(R.id.book_price);
        TextView synopsis = findViewById(R.id.book_synopsis);
        ImageView bookImage = findViewById(R.id.book_image);

        // Book Details
        TextView pub = findViewById(R.id.book_pub);
        pub.setText(forViewing.publisher);
        TextView lang = findViewById(R.id.book_lang);
        lang.setText(forViewing.language);
        TextView date = findViewById(R.id.book_date);
        date.setText(forViewing.datePublished);
        TextView pages = findViewById(R.id.book_pages);
        pages.setText(forViewing.pages);

        // Suggestion
        ImageView suggestion1 = findViewById(R.id.book_image_sug1);
        ImageView suggestion2 = findViewById(R.id.book_image_sug2);
        ImageView suggestion3 = findViewById(R.id.book_image_sug3);
        TextView sugg1Title = findViewById(R.id.book_title_sug1);
        TextView sugg1Author = findViewById(R.id.book_author_sug1);
        TextView sugg2Title = findViewById(R.id.book_title_sug2);
        TextView sugg2Author = findViewById(R.id.book_author_sug2);
        TextView sugg3Title = findViewById(R.id.book_title_sug3);
        TextView sugg3Author = findViewById(R.id.book_author_sug3);
        Button addToCart = findViewById(R.id.cart);

        if (CartFragment.cartStack.contains(forViewing)) {
            addToCart.setText("Added");
            addToCart.setClickable(false);
        }
        else addToCart.setOnClickListener(v -> {
            addToCart.setText("Added");
            addToCart.setClickable(false);
            CartFragment.cartStack.push(forViewing);
        });

        title.setText(forViewing.title);
        author.setText(forViewing.author);
        rating.setText(forViewing.rating + "⭐");
        genre.setText("Genre: " + forViewing.genre);
        MainActivity.lastView = forViewing.genre;
        String peso = String.format("%.2f", Float.parseFloat(forViewing.price) * 53);
        price.setText("Php " + peso);
        synopsis.setText(forViewing.synopsis);

        Picasso.get().load(forViewing.thumbnail).resize(100, 120).centerCrop().into(bookImage);

        for (Book temp : HomeFragment.books) {
            if (temp.title.equals(forViewing.recoTitle1)) {
                Picasso.get().load(temp.thumbnail).resize(100, 120).centerCrop().into(suggestion1);
                suggestion1.setOnClickListener(v -> imageRedir(temp));
                sugg1Title.setText(temp.title);
                sugg1Author.setText(temp.author);
            } else if (temp.title.equals(forViewing.recoTitle2)) {
                Picasso.get().load(temp.thumbnail).resize(100, 120).centerCrop().into(suggestion2);
                suggestion2.setOnClickListener(v -> imageRedir(temp));
                sugg2Title.setText(temp.title);
                sugg2Author.setText(temp.author);
            } else if (temp.title.equals(forViewing.recoTitle3)) {
                Picasso.get().load(temp.thumbnail).resize(100, 120).centerCrop().into(suggestion3);
                suggestion3.setOnClickListener(v -> imageRedir(temp));
                sugg3Title.setText(temp.title);
                sugg3Author.setText(temp.author);
            }
        }
    }

    private void imageRedir(Book book) {
        forViewing = book;
        Intent intent = new Intent(this, BookDetails.class);
        this.startActivity(intent);
    }
}