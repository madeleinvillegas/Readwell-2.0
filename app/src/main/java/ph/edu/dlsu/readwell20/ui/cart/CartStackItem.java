package ph.edu.dlsu.readwell20.ui.cart;

import ph.edu.dlsu.readwell20.Book;

public class CartStackItem {
    public CartStackItem top = null;
    public Book book;
    public CartStackItem bottom = null;
    public int counter = 0;

    public CartStackItem(Book book) {
        this.book = book;
    }
}