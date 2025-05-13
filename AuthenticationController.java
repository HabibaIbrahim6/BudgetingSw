import java.util.Random;
import java.util.Scanner;

/**
 * The AuthenticationController class handles user authentication processes including sign up and login.
 * It validates input data, generates OTP for email verification, and manages user authentication.
 */
public class AuthenticationController {
    private static String currentOTP;

    /**
     * This method handles the user sign-up process. It collects user data,
     * validates the data, generates an OTP, and verifies the OTP.
     */
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

    /**
     * This method handles user login. It checks the provided email and password against the database.
     * @return the authenticated user or null if login fails.
     */
    public static User login() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
    
        if (!isValidEmail(email)) {
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

    /**
     * Validates the format of the given email address.
     * @param email the email address to validate
     * @return true if the email format is valid, otherwise false.
     */
    private static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    /**
     * Validates the provided email and phone number.
     * @param email the email address to validate
     * @param phoneNumber the phone number to validate
     * @return true if both email and phone number are valid, otherwise false.
     */
    public static boolean isValid(String email, String phoneNumber) {
        return isValidEmail(email) && checkDataFormat(phoneNumber);
    }

    /**
     * Checks if the provided phone number consists of only digits.
     * @param phoneNumber the phone number to validate
     * @return true if the phone number contains only digits, otherwise false.
     */
    public static boolean checkDataFormat(String phoneNumber) {
        if (!phoneNumber.matches("\\d+")) {
            showError("Phone number must contain only digits.");
            return false;
        }
        return true;
    }

    /**
     * Displays an error message.
     * @param message the error message to display
     */
    public static void showError(String message) {
        System.out.println(" ERROR: " + message);
    }

    /**
     * Generates a 6-digit OTP.
     * @return a randomly generated OTP as a string.
     */
    private static String generateOTP() {
        Random random = new Random();
        return String.valueOf(100000 + random.nextInt(900000));
    }

    /**
     * Verifies the provided OTP.
     * @param otp the OTP to verify
     * @return true if the OTP matches the generated OTP, otherwise false.
     */
    private static boolean verifyOTP(String otp) {
        return currentOTP != null && currentOTP.equals(otp);
    }
}
