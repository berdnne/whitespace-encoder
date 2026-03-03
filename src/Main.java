import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        promptUser(scanner);
    }

    private static void promptUser(Scanner scanner) {
        System.out.print("Would you like to encode, decode, or quit? (E/D/Q): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        while (!choice.equals("Q")) {
            while (!choice.equals("E") && !choice.equals("D") && !choice.equals("Q")) {
                System.out.print("Enter a valid choice: ");
                choice = scanner.nextLine().trim().toUpperCase();
            }
            switch (choice) {
                case "E":
                    encode(scanner);
                    break;
                case "D":
                    decode(scanner);
                    break;
                case "Q":
                    System.exit(0);
            }
            System.out.print("Would you like to encode, decode, or quit? (E/D/Q): ");
            choice = scanner.nextLine().trim().toUpperCase();
        }
    }

    private static void encode(Scanner scanner) {
        System.out.print("Enter text to encode (only A-Z, 0-9, space): ");
        String text = scanner.nextLine().trim().toUpperCase();
        while (!text.matches("^[A-Z0-9 ]+$") || text.isEmpty()) {
            System.out.print("Enter only valid characters. Must be at least one character in length: ");
            text = scanner.nextLine().trim().toUpperCase();
        }
        String encodedText = getEncodedText(text);
        Path file = Path.of("message.txt");
        try {
            Files.writeString(file, encodedText);
        } catch (IOException ignored) {
            System.out.println("Error. Remember to specify the correct file name.");
            return;
        }
        System.out.println("Message created.");
    }

    private static String getEncodedText(String text) {
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            StringBuilder charInBinary = new StringBuilder(Integer.toBinaryString(text.charAt(i)));
            while (charInBinary.length() < 7) {
                charInBinary.insert(0, "0");
            }
            for (int j = 0; j < charInBinary.length(); j++) {
                if (charInBinary.charAt(j) == '1') {
                    encodedText.append(' ');
                } else { // charInBinary.charAt(j) == '0'
                    encodedText.append(' ');
                }
            }
        }
        return encodedText.toString();
    }

    private static void decode(Scanner scanner) {
        System.out.print("Enter encoded message: ");
        String encodedText = scanner.nextLine();
        int length = encodedText.length();
        if (length % 7 != 0) {
            System.out.println("Error. The string cannot be split evenly. " +
                    "Make sure you've properly copied the string at the exact bounds.");
            return;
        }
        String decodedText = getDecodedText(encodedText);
        System.out.println(decodedText);
    }

    private static String getDecodedText(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < encodedText.length() - 6; i += 7) {
            String binaryChar = encodedText.substring(i, i + 7)
                    .replace(" ", "1").replace(" ", "0")
                    .replace("\n", "");
            char decodedChar = (char) (Integer.parseInt(binaryChar, 2));
            decodedText.append(decodedChar);
        }
        return decodedText.toString();
    }

}
