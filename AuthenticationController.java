import java.util.Random;
import java.util.Scanner;

public class AuthenticationController {
    private static String currentOTP;

    public static void signUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        if (isValid(email, phoneNumber)) {
            if (UserDB.emailExists(email)) {
                showError("Email already exists.");
            } else {
                currentOTP = generateOTP();
                System.out.println(" Your OTP is: " + currentOTP);
                System.out.print("Enter the OTP: ");
                String otp = scanner.nextLine().trim();

                if (verifyOTP(otp)) {
                    User user = new User(username, email, phoneNumber, password);
                    UserDB.saveUser(user);
                    System.out.println("Account created successfully!");
                } else {
                    showError("OTP verification failed.");
                }
            }
        }
    }

    public static User login() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
    
        if(!isValidEmail(email)) {
            showError("Invalid email format.");
            return null;
        }
    
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
    
        User user = UserDB.authenticateUser(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            return user;
        } else {
            showError("Invalid email or password.");
            return null;
        }
    }

    private static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValid(String email, String phoneNumber) {
        return isValidEmail(email) && checkDataFormat(phoneNumber);
    }

    public static boolean checkDataFormat(String phoneNumber) {
        if (!phoneNumber.matches("\\d+")) {
            showError("Phone number must contain only digits.");
            return false;
        }
        return true;
    }

    public static void showError(String message) {
        System.out.println(" ERROR: " + message);
    }

    private static String generateOTP() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    private static boolean verifyOTP(String otp) {
        return currentOTP != null && currentOTP.equals(otp);
    }
}