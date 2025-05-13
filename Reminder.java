import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a reminder with an ID, title, date, and time.
 */
public class Reminder {
    private String reminderId;
    private String title;
    private String date;
    private String time;

    /**
     * Constructs a Reminder with string values for all fields.
     *
     * @param reminderId The unique identifier for the reminder.
     * @param title      The title or description of the reminder.
     * @param date       The date of the reminder as a string.
     * @param time       The time of the reminder as a string.
     */
    public Reminder(String reminderId, String title, String date, String time) {
        this.reminderId = reminderId;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    /**
     * Constructs a Reminder using LocalDate and LocalTime.
     *
     * @param reminderId The unique identifier for the reminder.
     * @param title      The title of the reminder.
     * @param localDate  The date of the reminder.
     * @param localTime  The time of the reminder.
     */
    public Reminder(String reminderId, String title, LocalDate localDate, LocalTime localTime) {
        this.reminderId = reminderId;
        this.title = title;
        this.date = localDate.toString();
        this.time = localTime.toString();
    }

    /**
     * An incomplete constructor (currently does nothing).
     * You may want to implement this if needed.
     *
     * @param reminderId The reminder ID.
     * @param title      The title of the reminder.
     * @param localDate  The date of the reminder.
     * @param time       The time of the reminder.
     */
    public Reminder(String reminderId, String title, LocalDate localDate, String time) {
        // This constructor currently does nothing.
        // Consider implementing or removing it if not needed.
    }

    /**
     * Gets the reminder ID.
     *
     * @return The reminder ID.
     */
    public String getReminderId() {
        return reminderId;
    }

    /**
     * Gets the title of the reminder.
     *
     * @return The reminder title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the date of the reminder.
     *
     * @return The reminder date as a string.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time of the reminder.
     *
     * @return The reminder time as a string.
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns a string representation of the reminder.
     *
     * @return A string in object-style format.
     */
    @Override
    public String toString() {
        return "Reminder{" +
               "reminderId='" + reminderId + '\'' +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               '}';
    }

    /**
     * Returns the reminder details in CSV format.
     *
     * @return A CSV-formatted string of the reminder.
     */
    public String toCSV() {
        return reminderId + "," + title + "," + date + "," + time;
    }
}
