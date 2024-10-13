package gui;

import bookstore.Bookstore;
import inventory.Book;
import inventory.Category;
import inventory.FictionCategory;
import inventory.NonFictionCategory;
import inventory.MysteryCategory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel {
    private JFrame frame;
    private final Bookstore bookstore;

    public AdminPanel(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public void display() {
        frame = new JFrame("Admin Panel");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1, 10, 10));

        JButton addBookButton = new JButton("Add Book");
        JButton addCategoryButton = new JButton("Add Category");

        frame.add(addBookButton);
        frame.add(addCategoryButton);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCategory();
            }
        });

        frame.setVisible(true);
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(frame, "Enter book title:");
        String author = JOptionPane.showInputDialog(frame, "Enter author name:");
        double price = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter book price:"));
        String isbn = JOptionPane.showInputDialog(frame, "Enter ISBN:");

        // Assuming user selects an existing category from a list
        Object[] categoryNames = bookstore.getCategories().stream()
                .map(Category::getName).toArray();
        String selectedCategory = (String) JOptionPane.showInputDialog(
                frame, "Choose a category", "Categories",
                JOptionPane.PLAIN_MESSAGE, null, categoryNames, categoryNames[0]
        );

        Category category = bookstore.getCategoryByName(selectedCategory);
        if (category != null) {
            Book book = new Book(bookstore.getInventory().size() + 1, title, author, price, isbn);
            bookstore.addBook(book, category);
            JOptionPane.showMessageDialog(frame, "Book added successfully.");
        }
    }

    private void addCategory() {
        String categoryName = JOptionPane.showInputDialog(frame, "Enter category name:");
        String categoryDescription = JOptionPane.showInputDialog(frame, "Enter category description:");
        Category newCategory = new Category(categoryName, categoryDescription) {
            @Override
            public String getCategoryDetails() {
                return "";
            }
        };
        bookstore.addCategory(newCategory);
        JOptionPane.showMessageDialog(frame, "Category added successfully.");
    }
}
