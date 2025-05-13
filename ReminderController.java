import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller class for handling reminder creation, validation, and display.
 */
public class ReminderController {

    /**
     * Validates the title and date of a reminder.
     *
     * @param title The title of the reminder.
     * @param date  The date of the reminder.
     * @return true if the title is not empty and the date is not in the past, false otherwise.
     */
    public boolean validateData(String title, Date date) {
        return title != null && !title.trim().isEmpty() &&
               date != null && !date.before(new Date());
    }

    /**
     * Creates a new Reminder object if the data is valid.
     *
     * @param title The title of the reminder.
     * @param date  The date of the reminder.
     * @return A new Reminder object, or null if validation fails.
     */
    public Reminder createReminder(String title, Date date) {
        if (!validateData(title, date)) {
            return null;
        }

        String reminderId = "REM-" + System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        return new Reminder(
            reminderId,
            title,
            dateFormat.format(date),
            timeFormat.format(new Date())
        );
    }

    /**
     * Displays the list of reminders associated with a user's email.
     *
     * @param email The email address of the user.
     */
    public void displayReminderList(String email) {
        List<Reminder> reminders = UserDB.getUserReminders(email);
        if (reminders.isEmpty()) {
            System.out.println("No reminders found.");
            return;
        }

        System.out.println("\nYour Reminders:");
        System.out.println("----------------------------------------");
        for (Reminder reminder : reminders) {
            System.out.println("ID: " + reminder.getReminderId());
            System.out.println("Title: " + reminder.getTitle());
            System.out.println("Date: " + reminder.getDate());
            System.out.println("Time: " + reminder.getTime());
            System.out.println("----------------------------------------");
        }
    }
}
