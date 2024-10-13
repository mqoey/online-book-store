package accounts;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public User(int id, String firstName, String lastName, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }

    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
}
