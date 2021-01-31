package ph.edu.dlsu.readwell20;

public class Book {
    public String title, author, publisher, language, datePublished, pages, genre, synopsis, price, rating;
    public Book recommended1, recommended2, recommended3;

    public Book(String s10, String s9, String s8, String s7, String s6, String s5, String s4, String s3, String s2, int i, String s1, String s, String title, String author) {
        this.title = title;
        this.author = author;
    }

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