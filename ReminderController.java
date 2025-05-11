import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReminderController {
    public boolean validateData(String title, Date date) {
        return title != null && !title.trim().isEmpty() && 
               date != null && !date.before(new Date());
    }

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