package ph.edu.dlsu.readwell20;

public class Book {
    public String title, author, publisher, language, datePublished, pages, genre, synopsis, price, rating;
    public Book recommended1, recommended2, recommended3;

    public Book(String title, String author, String publisher, String language, String datePublished,
                String pages, String genre, String synopsis, String price, String rating,
                Book recommended1, Book recommended2, Book recommended3) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.language = language;
        this.datePublished = datePublished;
        this.pages = pages;
        this.genre = genre;
        this.synopsis = synopsis;
        this.price = price;
        this.rating = rating;
        this.recommended1 = recommended1;
        this.recommended2 = recommended2;
        this.recommended3 = recommended3;
    }
    public Book(String title, String author, String publisher, String language, String datePublished,
                String pages, String genre, String synopsis, String price, String rating) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.language = language;
        this.datePublished = datePublished;
        this.pages = pages;
        this.genre = genre;
        this.synopsis = synopsis;
        this.price = price;
        this.rating = rating;
    }
}
