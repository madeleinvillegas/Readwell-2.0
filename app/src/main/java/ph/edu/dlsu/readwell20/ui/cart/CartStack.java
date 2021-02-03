package ph.edu.dlsu.readwell20.ui.cart;

import java.util.ArrayList;

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

    public ArrayList<Book> toArray() {
        ArrayList<Book> toReturn = new ArrayList<>();
        CartStackItem temp = topMost;

        while (temp.bottom != null) {
            toReturn.add(temp.book);
            temp = temp.bottom;
        }

        return toReturn;
    }

    public boolean contains(Book book) {
        CartStackItem temp = topMost;

        while (temp.bottom != null) {
            if (temp.book.title.equals(book.title)) return true;
            temp = temp.bottom;
        }

        return false;
    }

    public CartStackItem getRoot() {
        return root;
    }

    public void remove(Book book) {
        CartStackItem temp = topMost;

        while (temp.bottom != null) {
            if (temp.book.title.equals(book.title)) {
                if (temp.top != null) temp.top.bottom = temp.bottom;
                else topMost = temp.bottom;
                temp.bottom.top = temp.top;
                return;
            }
            temp = temp.bottom;
        }
    }
}