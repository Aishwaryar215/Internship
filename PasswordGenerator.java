import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

 class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input for the length of the password
        System.out.print("Enter the length of the password: ");
        int passwordLength = scanner.nextInt();

        // Take input for characters to include in the password
        System.out.print("Enter the characters you want in the password (without repetition): ");
        scanner.nextLine(); // Consume the newline character
        String characters = scanner.nextLine();

        // Validate input characters
        if (!validateCharacters(characters)) {
            System.out.println("Invalid input characters. Please make sure there are no repetitions.");
            return;
        }

        // Generate the password
        String password = generatePassword(passwordLength, characters);

        // Display the generated password
        System.out.println("Generated Password: " + password);
    }

    // Function to generate password
    private static String generatePassword(int length, String characters) {
        StringBuilder password = new StringBuilder();
        Set<Character> charSet = new HashSet<>();

        for (char ch : characters.toCharArray()) {
            charSet.add(ch);
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            char selectedChar = characters.charAt(randomIndex);

            while (!charSet.contains(selectedChar) || password.toString().contains(String.valueOf(selectedChar))) {
                randomIndex = (int) (Math.random() * characters.length());
                selectedChar = characters.charAt(randomIndex);
            }

            password.append(selectedChar);
        }

        return password.toString();
    }

    // Function to validate input characters
    private static boolean validateCharacters(String characters) {
        Set<Character> charSet = new HashSet<>();

        for (char ch : characters.toCharArray()) {
            if (charSet.contains(ch)) {
                return false; // Invalid input characters (repeated characters)
            }
            charSet.add(ch);
        }

        return true;
    }
}
