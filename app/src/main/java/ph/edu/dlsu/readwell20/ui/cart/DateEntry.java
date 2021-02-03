package ph.edu.dlsu.readwell20.ui.cart;

import java.util.ArrayList;

public class DateEntry {
    public String date;
    public ArrayList<String> entries;

    public DateEntry(String date) {
        this.date = date;
        this.entries = new ArrayList<>();
    }

    public void addEntry(String titleAndCount) {
        entries.add(titleAndCount);
    }
}