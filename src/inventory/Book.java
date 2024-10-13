package inventory;

public class Book {
    private final int id;
    private final String title;
    private final String author;
    private double price;
    private final String isbn;

    public Book(int id, String title, String author, double price, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getIsbn() { return isbn; }

    public void setPrice(double price) { this.price = price; }
}
