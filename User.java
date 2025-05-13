
public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return String.join(",",
            username.replace(",", "_"),
            email.replace(",", "_"),
            phoneNumber.replace(",", "_"),
            password.replace(",", "_")
        );
    }

    public static User fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid user data format");
        }
        return new User(
            parts[0].replace("_", ","),
            parts[1].replace("_", ","),
            parts[2].replace("_", ","),
            parts[3].replace("_", ",")
        );
    }
}