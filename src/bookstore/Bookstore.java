package bookstore;

import inventory.Book;
import inventory.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bookstore {
    private final int id;
    private final String name;
    private final List<Category> categories;
    private final Map<Category, List<Book>> booksByCategory;
    private final List<Book> books;


    public Bookstore(int id, String name) {
        this.id = id;
        this.name = name;
        this.categories = new ArrayList<>();
        this.booksByCategory = new HashMap<>();
        books = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
        booksByCategory.put(category, new ArrayList<>()); // Initialize an empty list for each category
    }

    public void addBook(Book book, Category category) {
        if (booksByCategory.containsKey(category)) {
            booksByCategory.get(category).add(book);
        }
    }

    public List<Book> getBooksByCategory(Category category) {
        return booksByCategory.getOrDefault(category, new ArrayList<>());
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) { // Compare names (case insensitive)
                return category;
            }
        }
        return null; // Return null if no category is found
    }

    // Method to get all books (inventory)
    public List<Book> getInventory() {
        return books; // Return the list of all books
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Book getBookByTitle(String title) {
        for (List<Book> bookList : booksByCategory.values()) {
            for (Book book : bookList) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    return book;
                }
            }
        }
        return null; // Book not found
    }

    // Getters for id and name
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
