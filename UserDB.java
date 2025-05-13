
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static final String USER_FILE_PATH = "users.txt";

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