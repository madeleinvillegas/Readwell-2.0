package ph.edu.dlsu.readwell20.ui.cart;

import ph.edu.dlsu.readwell20.Book;

public class CartStack {
    private final CartStackItem root = new CartStackItem(null);
    private CartStackItem topMost = root;
    public int size = 0;

    public void push(Book book) {
        CartStackItem temp = new CartStackItem(book);
        temp.bottom = topMost;
        topMost.top = temp;
        topMost = temp;
        size++;
    }

    public Book[] toArray() {
        Book[] toReturn = new Book[size];
        CartStackItem temp = topMost;

        int counter = 0;
        while (temp.bottom != null) {
            toReturn[counter] = temp.book;
            temp = temp.bottom;
            counter++;
        }

        return toReturn;
    }
}