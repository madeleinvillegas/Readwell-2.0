package ph.edu.dlsu.readwell20;

public class Book {
    public String title, author, publisher, language, datePublished, pages, genre, synopsis, price, rating, thumbnail;
    public int count = 0;
    public String recoTitle1, recoTitle2, recoTitle3;
    public String recoImg1, recoAuthor1, recoImg2, recoAuthor2, recoImg3, recoAuthor3;
    public Book(String title, String author, String thumbnail) {
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
    }

    public Book(String title, String author, String publisher, String language, String datePublished,
                String pages, String genre, String synopsis, String price, String rating,
                String thumbnail, String recommended1, String recommended2, String recommended3) {
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
        this.thumbnail = thumbnail;
        this.recoTitle1 = recommended1;
        this.recoTitle2 = recommended2;
        this.recoTitle3 = recommended3;
    }

    public Book(String title, String author, String publisher, String language, String datePublished,
                String pages, String genre, String synopsis, String price, String rating, String thumbnail) {
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
        this.thumbnail = thumbnail;
    }

    public static Book replicate(Book book) {
        return new Book(book.title, book.author, book.publisher, book.language,
                book.datePublished, book.pages, book.genre, book.synopsis,
                book.price, book.rating, book.thumbnail, book.recoTitle1,
                book.recoTitle2, book.recoTitle3);
    }
}