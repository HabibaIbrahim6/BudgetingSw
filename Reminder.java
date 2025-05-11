import java.time.LocalDate;
import java.time.LocalTime;

public class Reminder {
    private String reminderId;
    private String title;
    private String date;
    private String time;

    public Reminder(String reminderId, String title, String date, String time) {
        this.reminderId = reminderId;
        this.title = title;
        this.date = date;
        this.time = time;
    }
    public Reminder(String reminderId, String title, LocalDate localDate, LocalTime localTime) {
        this.reminderId = reminderId;
        this.title = title;
        this.date = localDate.toString();  
        this.time = localTime.toString();  
    }

    public Reminder(String reminderId2, String title2, LocalDate localDate, String time2) {
       
    }

    public String getReminderId() {
        return reminderId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Reminder{" +
               "reminderId='" + reminderId + '\'' +
               ", title='" + title + '\'' +
               ", date='" + date + '\'' +
               ", time='" + time + '\'' +
               '}';
    }

    public String toCSV() {
        return reminderId + "," + title + "," + date + "," + time;
    }
}
