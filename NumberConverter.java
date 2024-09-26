/**
 * NumberConverter class handles user interactions for number conversion
 * and arithmetic operations. It prompts users for input and delegates
 * the conversion logic to the NumberOperations class.
 * Owner: Tanuj Yadav
 * Date: 23/09/2024
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class NumberConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(Constants.WelcomeMessage);
        while (true) {
            int base = getBase(input);
            String number = getNumberFromUser(input, base);
            while (true) {
                System.out.println(Constants.OperationPrompt);
                char action = getValidCharInput(input);
                if (action == 'c') {
                    NumberOperations.performConversion(number, base, input);
                } else if (action == 'o') {
                    NumberOperations.performOperation(number, base, input);
                } else {
                    System.out.println(Constants.ErrorMessages.ErrorInvalidInput);
                    continue;
                }
                System.out.print(Constants.AnotherConversion);
                boolean repeat = getYesOrNoInput(input);
                if (!repeat) {
                    break;
                }
            }
            System.out.print(Constants.EndsProgram);
            boolean endProgram = getYesOrNoInput(input);
            if (endProgram) {
                System.out.println(Constants.GoodByeMessage);
                break;
            } else {
                System.out.println(Constants.RestartProgram);
            }
        }
        input.close();
    }

    /*
     * Prompts the user for the base of the number and validates the input.
     * @param input Scanner object to read user input.
     * @return Validated base input from the user.
     */
    public static int getBase(Scanner input) {
        while (true) {
            System.out.print(Constants.EnterBase);
            try {
                int base = input.nextInt();
                if (base >= 2 && base <= 16) {
                    input.nextLine();
                    return base;
                } else {
                    System.out.println(Constants.ErrorMessages.ErrorBaseRange);
                }
            } catch (InputMismatchException e) {
                System.out.println(Constants.ErrorMessages.ErrorInvalidBase);
                input.next();
            }
        }
    }
    /*
     * Prompts the user for a number and validates the input against the specified base.
     * @param input  Scanner object to read user input.
     * @param base Base of the input number.
     * @return Validated number input from the user.
     */
    public static String getNumberFromUser(Scanner input, int base) {
        while (true) {
            System.out.printf(Constants.EnterNumber, base);
            String number = input.nextLine().trim();
            String processedNumber = number.replace(" ", "");
            if (processedNumber.length() > 100) {
                System.out.println(Constants.ErrorMessages.ErrorNumberTooLong);
                continue;
            }
            if (isValidNumber(processedNumber, base)) {
                return processedNumber;
            } else {
                System.out.println(Constants.ErrorMessages.ErrorInvalidNumber + base + ". Please try again.");
            }
        }
    }
    /*
     * Validates the provided number for the specified base.
     * Disallows negative and decimal numbers.
     * @param number The number as a string to validate.
     * @param base   The base of the number.
     * @return true if valid, false otherwise.
     */
    private static boolean isValidNumber(String number, int base) {
        if (number.contains("-") || number.contains(".")) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if ((c >= '0' && c <= '9' && (c - '0' >= base)) ||
                    (c >= 'A' && c <= 'F' && (c - 'A' + 10 >= base)) ||
                    (c >= 'a' && c <= 'f' && (c - 'a' + 10 >= base))) {
                return false;
            }
        }
        return true;
    }
    /*
     * Prompts the user for a character input and validates it.
     * @param input Scanner object to read user input.
     * @return Validated character input.
     */
    public static char getValidCharInput(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.length() == 1) {
                return input.charAt(0);
            }
            System.out.println(Constants.ErrorMessages.ErrorInvalidInput);
        }
    }
    /*
     * Prompts the user for a yes/no response and validates it.
     * @param input Scanner object to read user input.
     * @return true for yes, false for no.
     */
    public static boolean getYesOrNoInput(Scanner input) {
        while (true) {
            String response = input.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                return true;
            } else if (response.equals("n")) {
                return false;
            }
            System.out.println(Constants.ErrorMessages.ErrorYesOrNO);
        }
    }
}
