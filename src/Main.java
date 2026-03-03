import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to encode or decode? (E/D): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        while (!choice.equals("E") && !choice.equals("D")) {
            System.out.print("Enter a valid choice: ");
            choice = scanner.nextLine().trim().toUpperCase();
        }
        if (choice.equals("E")){
            encode(scanner);
        } else { // choice.equals("D")
            decode(scanner);
        }

    }

    private static void encode(Scanner scanner) {
        System.out.print("Enter text to encode (only A-Z, 0-9, space): ");
        String text = scanner.nextLine().trim().toUpperCase();
        while (!text.matches("^[A-Z0-9 ]+$")) {
            System.out.print("Dude come on: ");
            text = scanner.nextLine().trim().toUpperCase();
        }
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String charInBinary = Integer.toBinaryString(text.charAt(i));
            for (int j = 0; j < charInBinary.length(); j++) {
                if (charInBinary.charAt(j) == '1') {
                    encodedText.append(' ');
                } else { // charInBinary.charAt(j) == '0'
                    encodedText.append(' ');
                }
            }
        }
        System.out.println(encodedText);
    }

    private static void decode(Scanner scanner) {

    }

}
