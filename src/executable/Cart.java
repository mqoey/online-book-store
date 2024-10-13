package executable;

import java.util.HashMap;
import java.util.Map;
import inventory.Book;

public class Cart {
    private int cartId;
    private int customerId;
    private Map<Book, Integer> booksInCart;

    public Cart(int cartId, int customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.booksInCart = new HashMap<>();
    }

    public void addBook(Book book, int quantity) {
        booksInCart.put(book, booksInCart.getOrDefault(book, 0) + quantity);
    }

    public void viewCartItems() {
        for (Map.Entry<Book, Integer> entry : booksInCart.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " - Quantity: " + quantity);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Book, Integer> entry : booksInCart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void clearCart() {
        booksInCart.clear();
        System.out.println("Cart has been cleared.");
    }

    public Map<Book, Integer> getBooksInCart() {
        return booksInCart;
    }
}
