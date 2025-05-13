import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code UserDB} class provides functionality to save and retrieve
 * users and their reminders from a file-based storage system.
 * <p>
 * It handles user registration, authentication, and reminder management.
 */
public class UserDB {
    private static final String USER_FILE_PATH = "users.txt";

    /**
     * Saves a new user to the user file.
     *
     * @param user the {@link User} object to save
     */
    public static void saveUser(User user) {
        try {
            String userData = user.toString() + System.lineSeparator();
            Files.write(Paths.get(USER_FILE_PATH), userData.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            AuthenticationController.showError("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Checks if an email already exists in the user file.
     *
     * @param email the email address to check
     * @return {@code true} if the email exists, otherwise {@code false}
     */
    public static boolean emailExists(String email) {
        try {
            List<User> users = readUsers();
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return false;
    }

    /**
     * Reads all users from the user file.
     *
     * @return a list of {@link User} objects
     */
    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(USER_FILE_PATH))) {
                return users;
            }

            List<String> lines = Files.readAllLines(Paths.get(USER_FILE_PATH));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    try {
                        users.add(User.fromString(line));
                    } catch (Exception e) {
                        System.out.println("Skipping invalid user entry: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return users;
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param email    the email address
     * @param password the password
     * @return the authenticated {@link User} object, or {@code null} if not found
     */
    public static User authenticateUser(String email, String password) {
        List<User> users = readUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Saves a reminder associated with a user's email.
     *
     * @param email    the user's email address
     * @param reminder the {@link Reminder} object to save
     */
    public static void saveReminderToUser(String email, Reminder reminder) {
        try {
            String reminderData = email + "," + reminder.toString() + "\n";
            Files.write(Paths.get(USER_FILE_PATH), reminderData.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Reminder saved successfully!");
        } catch (Exception e) {
            AuthenticationController.showError("Error saving reminder: " + e.getMessage());
        }
    }

    /**
     * Retrieves all reminders for a specific user based on email.
     *
     * @param email the user's email address
     * @return a list of {@link Reminder} objects
     */
    public static List<Reminder> getUserReminders(String email) {
        List<Reminder> reminders = new ArrayList<>();
        try {
            if (!Files.exists(Paths.get(USER_FILE_PATH)))
                return reminders;

            List<String> lines = Files.readAllLines(Paths.get(USER_FILE_PATH));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",", 5);
                    if (parts.length >= 5 && parts[0].equalsIgnoreCase(email)) {
                        reminders.add(new Reminder(
                                parts[1],
                                parts[2],
                                parts[3],
                                parts[4]));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading reminders: " + e.getMessage());
        }
        return reminders;
    }
}
