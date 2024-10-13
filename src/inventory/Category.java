package inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private final String name;
    private final String description;
    private final List<Book> books;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();  // Initialize list of books for this category
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addBook(Book book) {
        books.add(book);  // Add book to the category's book list
    }

    public List<Book> getBooks() {
        return books;
    }

    public abstract String getCategoryDetails();
}
