package ph.edu.dlsu.readwell20;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;

import java.util.Objects;

public class BookDetails extends AppCompatActivity {
    private TextView title, author, others, genre, synopsis, price, rating, sugg1Title, sugg1Author;
    private TextView sugg2Title, sugg2Author, sugg3Title, sugg3Author;
    private ImageView bookImage, suggestion1, suggestion2, suggestion3;
    private Button addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        bookImage = findViewById(R.id.book_image);
        title = findViewById(R.id.book_title);
        author = findViewById(R.id.book_author);
        rating = findViewById(R.id.book_rating);
        genre = findViewById(R.id.book_genre);
        price = findViewById(R.id.book_price);
        synopsis = findViewById(R.id.book_synopsis);
        others = findViewById(R.id.book_details);
        bookImage = findViewById(R.id.book_image);
        suggestion1 = findViewById(R.id.book_image_sug1);
        suggestion2 = findViewById(R.id.book_image_sug2);
        suggestion3 = findViewById(R.id.book_image_sug3);
        sugg1Title = findViewById(R.id.book_title_sug1);
        sugg1Author = findViewById(R.id.book_author_sug1);
        sugg2Title = findViewById(R.id.book_title_sug2);
        sugg2Author = findViewById(R.id.book_author_sug2);
        sugg3Title = findViewById(R.id.book_title_sug3);
        sugg3Author = findViewById(R.id.book_author_sug3);
        addToCart = findViewById(R.id.cart);
        MainActivity.lastTab = 1;
    }
}