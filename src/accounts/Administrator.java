package accounts;

public class Administrator extends User {
    private String username;
    private String password;

    public Administrator(int id, String firstName, String lastName, String username, String password) {
        super(id, firstName, lastName, "", "");  // No need for address and phone
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
