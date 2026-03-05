import java.util.Scanner;

public class Main {

    private enum UserChoice {
        ENCODE, DECODE, QUIT
    }

    /** Runs the program. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runCodec(scanner);
    }

    /** Prompts the user to choose between encoding, decoding, or exiting. */
    private static void runCodec(Scanner scanner) {
        UserChoice choice = getUserChoice(scanner);
        while (!choice.equals(UserChoice.QUIT)) {
            if (choice == UserChoice.ENCODE) {
                displayEncodedText(scanner);
            } else {
                displayDecodedText(scanner);
            }
            choice = getUserChoice(scanner);
        }
    }

    private static UserChoice getUserChoice(Scanner scanner) {
        System.out.print("Would you like to encode, decode, or quit? (E/D/Q): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        while (!choice.equals("E") && !choice.equals("D") && !choice.equals("Q")) {
            System.out.print("Enter a valid choice: ");
            choice = scanner.nextLine().trim().toUpperCase();
        }
        return switch (choice) {
            case "E" -> UserChoice.ENCODE;
            case "D" -> UserChoice.DECODE;
            case "Q" -> UserChoice.QUIT;
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    private static void displayEncodedText(Scanner scanner) {
        System.out.print("Enter text to encode (only A-Z, 0-9, space): ");
        String encodedText = getEncodedText(scanner);
        System.out.println("Message created. It is on the line below.\n" + encodedText);
    }

    private static String getEncodedText(Scanner scanner) {
        String text = scanner.nextLine().trim().toUpperCase();
        while (!text.matches("^[A-Z0-9 ]+$") || text.isEmpty()) {
            System.out.print("Enter only valid characters. Must be at least one character in length: ");
            text = scanner.nextLine().trim().toUpperCase();
        }
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

    private static void displayDecodedText(Scanner scanner){
        System.out.print("Enter encoded message: ");
        String decodedText = getDecodedText(scanner);
        System.out.println("Message decoded. It is on the line below.\n" + decodedText);
    }

    private static String getDecodedText(Scanner scanner) {
        String encodedText = scanner.nextLine().replace("\n", "");
        while (encodedText.length() % 7 != 0) {
            System.out.print("Error. The string cannot be split evenly. " +
                    "Make sure you've properly copied the string at the exact bounds. Try again: ");
            encodedText = scanner.nextLine().replace("\n", "");
        }
        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < encodedText.length() - 6; i += 7) {
            String binaryChar = encodedText.substring(i, i + 7)
                    .replace(" ", "1").replace(" ", "0");
            char decodedChar = (char) (Integer.parseInt(binaryChar, 2));
            decodedText.append(decodedChar);
        }
        return decodedText.toString();
    }

}
