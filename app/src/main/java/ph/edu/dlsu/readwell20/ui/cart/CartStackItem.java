package ph.edu.dlsu.readwell20.ui.cart;

import ph.edu.dlsu.readwell20.Book;

public class CartStackItem {
    public CartStackItem top = null;
    public Book book;
    public CartStackItem bottom = null;
    public int counter = 1;

    public CartStackItem(Book book) {
        this.book = book;
    }

    public CartStackItem(Book book, int counter) {
        this.book = book;
        this.counter = counter;
    }
}