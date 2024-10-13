package accounts;

import java.util.List;
import inventory.Book;

public class Customer extends User {
    private int creditPoints;
    private List<Book> booksBought;

    public Customer(int id, String firstName, String lastName, String address, String phone, int creditPoints) {
        super(id, firstName, lastName, address, phone);
        this.creditPoints = creditPoints;
    }

    public int getCreditPoints() { return creditPoints; }
    public void setCreditPoints(int creditPoints) { this.creditPoints = creditPoints; }

    public List<Book> getBooksBought() { return booksBought; }
    public void addBook(Book book) { this.booksBought.add(book); }
}
