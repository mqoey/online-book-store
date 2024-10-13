package inventory;

public class NonFictionCategory extends Category {
    private final String name;
    private final String description;

    public NonFictionCategory(String name, String description) {
        super(name, description);

        this.name = name;
        this.description = description;
    }

    @Override
    public String getCategoryDetails() {
        return "Category: " + name + ", Description: " + description;
    }
}
