package gui;

import accounts.Administrator;
import bookstore.Bookstore;
import executable.Cart;
import inventory.Book;
import inventory.Category;
import inventory.FictionCategory;
import inventory.NonFictionCategory;
import inventory.MysteryCategory;
import accounts.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookstoreGUI {
    private final JFrame frame;
    private final Bookstore bookstore;
    private final List<Administrator> administrators; // List of administrators
    private final Cart cart; // Store the cart for the customer

    public BookstoreGUI() {
        // Initialize components
        bookstore = new Bookstore(1, "Main Street");
        initializeBooks();
        // Store the customer
        Customer customer = new Customer(1, "John", "Doe", "123 Elm St", "555-5555", 100);
        cart = new Cart(1, customer.getId());
        administrators = new ArrayList<>(); // Admins list
        initializeAdmins();

        // Set up the frame
        frame = new JFrame("Online Bookstore");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Set up main panel
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton loginButton = new JButton("Admin Login");
        JButton viewBooksButton = new JButton("View Available Books by Category");
        JButton addToCartButton = new JButton("Add Book to Cart");
        JButton viewCartButton = new JButton("View Cart");
        JButton checkoutButton = new JButton("Checkout");

        mainPanel.add(loginButton);
        mainPanel.add(viewBooksButton);
        mainPanel.add(addToCartButton);
        mainPanel.add(viewCartButton);
        mainPanel.add(checkoutButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        loginButton.addActionListener(e -> adminLogin());
        viewBooksButton.addActionListener(e -> displayBooksByCategory());
        addToCartButton.addActionListener(e -> addBookToCart());
        viewCartButton.addActionListener(e -> viewCart());
        checkoutButton.addActionListener(e -> checkout());

        // Show the frame
        frame.setVisible(true);
    }

    // Admin login method
    private void adminLogin() {
        String username = JOptionPane.showInputDialog(frame, "Enter username:");
        String password = JOptionPane.showInputDialog(frame, "Enter password:");

        Administrator admin = validateAdmin(username, password);
        if (admin != null) {
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + admin.getFirstName());
            openAdminPanel();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.");
        }
    }

    // Validate the admin login credentials
    private Administrator validateAdmin(String username, String password) {
        for (Administrator admin : administrators) {
            if (admin.getUsername().equals(username) && admin.validatePassword(password)) {
                return admin;
            }
        }
        return null;
    }

    // Open admin panel after successful login
    private void openAdminPanel() {
        AdminPanel adminPanel = new AdminPanel(bookstore);
        adminPanel.display();
    }

    // Initialize some dummy admin users
    private void initializeAdmins() {
        administrators.add(new Administrator(1, "Alice", "Smith", "admin1", "password1"));
        administrators.add(new Administrator(2, "Bob", "Johnson", "admin2", "password2"));
    }

    private void displayBooksByCategory() {
        StringBuilder bookList = new StringBuilder("Available Books:\n");
        for (Category category : bookstore.getCategories()) {
            bookList.append(category.getName()).append(":\n");
            for (Book book : bookstore.getBooksByCategory(category)) {
                bookList.append(" - ").append(book.getTitle())
                        .append(" by ").append(book.getAuthor())
                        .append(" - $").append(book.getPrice()).append("\n");
            }
            bookList.append("\n");
        }
        JOptionPane.showMessageDialog(frame, bookList.toString());
    }

    private void addBookToCart() {
        String bookTitle = JOptionPane.showInputDialog(frame, "Enter the title of the book to add to the cart:");
        Book book = bookstore.getBookByTitle(bookTitle);

        if (book != null) {
            String quantityStr = JOptionPane.showInputDialog(frame, "Enter quantity:");
            try {
                int quantity = Integer.parseInt(quantityStr);
                cart.addBook(book, quantity);
                JOptionPane.showMessageDialog(frame, quantity + " copy/copies of " + book.getTitle() + " added to the cart.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid quantity entered.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Book not found.");
        }
    }

    private void viewCart() {
        StringBuilder cartContents = new StringBuilder("Cart Items:\n");

        // Check if the cart is empty
        if (cart.getBooksInCart().isEmpty()) {
            cartContents.append("Your cart is empty.");
        } else {
            // Iterate over the entries in the cart's book map
            for (Map.Entry<Book, Integer> entry : cart.getBooksInCart().entrySet()) {
                Book book = entry.getKey();        // Get the Book
                Integer quantity = entry.getValue(); // Get the associated quantity
                cartContents.append(" - ").append(book.getTitle())
                        .append(" x ").append(quantity).append("\n");
            }
        }

        // Display the cart contents in a dialog
        JOptionPane.showMessageDialog(frame, cartContents.toString());
    }

    private void checkout() {
        double total = cart.calculateTotal();
        JOptionPane.showMessageDialog(frame, "Total amount: $" + total + "\nThank you for your purchase!");
        cart.clearCart();
    }

    private void initializeBooks() {
        Category fiction = new FictionCategory("Fiction", "Fiction books");
        Category nonFiction = new NonFictionCategory("Non-Fiction", "Non-fiction books");
        Category mystery = new MysteryCategory("Mystery", "Mystery books");

        // Add categories to the bookstore
        bookstore.addCategory(fiction);
        bookstore.addCategory(nonFiction);
        bookstore.addCategory(mystery);

        // Add some example books
        Book book1 = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", 10.99, "9780743273565");
        Book book2 = new Book(2, "Sapiens", "Yuval Noah Harari", 15.99, "9780062316097");
        Book book3 = new Book(3, "The Theory of Everything", "Stephen Hawking", 12.99, "9780553380163");

        bookstore.addBook(book1, fiction);
        bookstore.addBook(book2, nonFiction);
        bookstore.addBook(book3, mystery);
    }
}
