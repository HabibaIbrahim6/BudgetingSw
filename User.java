/**
 * Represents a user with basic information such as username, email, phone number, and password.
 */
public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    /**
     * Constructs a new User object.
     *
     * @param username    The user's username.
     * @param email       The user's email address.
     * @param phoneNumber The user's phone number.
     * @param password    The user's password.
     */
    public User(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username.
     */
    public String getUsername() { return username; }

    /**
     * Returns the email address of the user.
     *
     * @return The email.
     */
    public String getEmail() { return email; }

    /**
     * Returns the phone number of the user.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the password of the user.
     *
     * @return The password.
     */
    public String getPassword() { return password; }

    /**
     * Converts the user object to a string representation suitable for file storage.
     * Commas in fields are replaced with underscores.
     *
     * @return A comma-separated string representing the user.
     */
    @Override
    public String toString() {
        return String.join(",",
            username.replace(",", "_"),
            email.replace(",", "_"),
            phoneNumber.replace(",", "_"),
            password.replace(",", "_")
        );
    }

    /**
     * Parses a string to create a User object.
     * Underscores are converted back to commas.
     *
     * @param data A comma-separated string representing a user.
     * @return A User object.
     * @throws IllegalArgumentException if the string format is invalid.
     */
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
