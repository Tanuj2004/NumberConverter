/**
 * NumberConverter class handles the conversion and arithmetic operations
 * between numbers of different bases (from base 2 to base 16).
 * It allows users to input numbers in a specific base, convert them to another base,
 * and perform arithmetic operations (addition and subtraction) between two numbers in the same base.
 * Owner: Tanuj Yadav
 * Date: 23/09/2024
 */
 import java.util.InputMismatchException;
 import java.util.Scanner;
 import java.math.BigInteger;
 public class NumberConverter {
     public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         System.out.println(Constants.WELCOME_MESSAGE);
         while (true) {
             int base = getBase(input);
             String number = getNumberFromUser(input, base);
             while (true) {
                 System.out.println(Constants.OPERATION_PROMPT);
                 char action = getValidCharInput(input);
                 if (action == 'c') {
                     performConversion(number, base, input);
                 } else if (action == 'o') {
                     performOperation(number, base, input);
                 } else {
                     System.out.println(Constants.ErrorMessages.ERROR_INVALID_INPUT);
                     continue;
                 }
                 System.out.print(Constants.ANOTHER_CONVERSION_OR_OPERATION);
                 boolean repeat = getYesOrNoInput(input);
                 if (!repeat) {
                     break;
                 }
             }
             System.out.print(Constants.END_PROGRAM_PROMPT);
             boolean endProgram = getYesOrNoInput(input);
             if (endProgram) {
                 System.out.println(Constants.GOODBYE_MESSAGE);
                 break;
             } else {
                 System.out.println(Constants.RESTART_PROGRAM_MESSAGE);
             }
         }
         input.close();
     }
     /*
      * Prompts the user for the base of the number and validates the input.
      * @param input Scanner object to read user input.
      * @return Validated base input from the user.
      */
     private static int getBase(Scanner sc) {
         while (true) {
             System.out.print(Constants.ENTER_BASE_MESSAGE);
             try {
                 int base = sc.nextInt();
                 if (base >= 2 && base <= 16) {
                     sc.nextLine();
                     return base;
                 } else {
                     System.out.println(Constants.ErrorMessages.ERROR_BASE_RANGE);
                 }
             } catch (InputMismatchException e) {
                 System.out.println(Constants.ErrorMessages.ERROR_INVALID_INPUT_BASE);
                 sc.next();
             }
         }
     }
     /*
      * Prompts the user for a number and validates the input against the specified base.
      * @param input  Scanner object to read user input.
      * @param base Base of the input number.
      * @return Validated number input from the user.
      */
     private static String getNumberFromUser(Scanner sc, int base) {
         while (true) {
             System.out.printf(Constants.ENTER_NUMBER_MESSAGE, base);
             String number = sc.nextLine().trim();
             String processedNumber = number.replace(" ", "");
             if (processedNumber.length() > 100) {
                 System.out.println(Constants.ErrorMessages.ERROR_NUMBER_TOO_LONG);
                 continue;
             }
             if (isValidNumber(processedNumber, base)) {
                 return processedNumber;
             } else {
                 System.out.println(Constants.ErrorMessages.ERROR_INVALID_NUMBER + base + ". Please try again.");
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
      * Converts the given number from one base to decimal (base 10).
      * @param number The input number as a string.
      * @param base   The base of the input number.
      * @return The decimal equivalent of the input number as BigInteger.
      */
     private static BigInteger convertToDecimal(String number, int base) {
         BigInteger decimalValue = BigInteger.ZERO;
         for (int i = 0; i < number.length(); i++) {
             char digit = number.charAt(i);
             int value;
             if (digit >= '0' && digit <= '9') {
                 value = digit - '0';
             } else if (digit >= 'A' && digit <= 'F') {
                 value = digit - 'A' + 10;
             } else {
                 value = digit - 'a' + 10;
             }
             decimalValue = decimalValue.multiply(BigInteger.valueOf(base));
             decimalValue = decimalValue.add(BigInteger.valueOf(value));
         }
         return decimalValue;
     }
     /*
      * Converts a given decimal number to any base (e.g., binary, octal, or hexadecimal).
      * @param number The decimal number to be converted.
      * @param base   The target base (between 2 and 16).
      * @return The number converted to the target base as a string.
      */
     private static String decimalToBase(BigInteger number, int base) {
         if (number.equals(BigInteger.ZERO)) {
             return "0";
         }
         String result = "";
         boolean isNegative = false;
         if (number.compareTo(BigInteger.ZERO) < 0) {
             isNegative = true;
             number = number.multiply(BigInteger.valueOf(-1));
         }
         while (number.compareTo(BigInteger.ZERO) > 0) {
             BigInteger remainder = number.remainder(BigInteger.valueOf(base));
             number = number.divide(BigInteger.valueOf(base));
             char digitChar;
             if (remainder.intValue() < 10) {
                 digitChar = (char) ('0' + remainder.intValue());
             } else {
                 digitChar = (char) ('A' + (remainder.intValue() - 10));
             }
             result = digitChar + result;
         }
         if (isNegative) {
             result = "-" + result;
         }
         return result;
     }
     /*
      * Performs conversion from one base to another.
      * @param number The number to be converted.
      * @param base   The original base of the number.
      * @param input  Scanner object to read user input.
      */
     private static void performConversion(String number, int base, Scanner sc) {
         System.out.print(Constants.CONVERT_MESSAGE);
         int convertToBase = getBase(sc);
         BigInteger decimalValue = convertToDecimal(number, base);
         String convertedValue = decimalToBase(decimalValue, convertToBase);
         System.out.println(Constants.RESULT_IN_DECIMAL + decimalValue);
         System.out.println(Constants.RESULT + convertToBase + " base: "  + convertedValue);
     }
     /*
      * Performs operations (addition or subtraction) on two numbers in the same base.
      * @param number The first number for the operation.
      * @param base   The base of the numbers.
      * @param input  Scanner object to read user input.
      */
     private static void performOperation(String number, int base, Scanner sc) {
         BigInteger firstDecimalValue = convertToDecimal(number, base);
         char operation;
         while (true) {
             System.out.print(Constants.OPERATION_SELECTION_PROMPT);
             operation = getValidCharInput(sc);
             if (operation == '+' || operation == '-') {
                 break;
             } else {
                 System.out.println(Constants.ErrorMessages.ERROR_INVALID_OPERATION);
             }
         }
         String secondNumber = getNumberFromUser(sc, base);
         BigInteger secondDecimalValue = convertToDecimal(secondNumber, base);
         BigInteger result;
         if (operation == '+') {
             result = firstDecimalValue.add(secondDecimalValue);
         } else {
             result = firstDecimalValue.subtract(secondDecimalValue);
         }
         System.out.println(Constants.RESULT_IN_DECIMAL + result);
         System.out.println(Constants.RESULT_IN_ORIGINAL_BASE + base + ": " + decimalToBase(result, base));
     }
     /*
      * Prompts the user for a character input and validates it.
      * @param input Scanner object to read user input.
      * @return Validated character input.
      */
     private static char getValidCharInput(Scanner sc) {
         while (true) {
             String input = sc.nextLine().trim();
             if (input.length() == 1) {
                 return input.charAt(0);
             } else {
                 System.out.println(Constants.ErrorMessages.ERROR_ENTER_SINGLE_CHARACTER);
             }
         }
     }
     /*
      * Prompts the user for a yes or no input and validates it.
      * @param input Scanner object to read user input.
      * @return true if input is 'y', false if input is 'n'.
      */
     private static boolean getYesOrNoInput(Scanner sc) {
         while (true) {
             String input = sc.nextLine().trim().toLowerCase();
             if (input.equals("y")) {
                 return true;
             } else if (input.equals("n")) {
                 return false;
             } else {
                 System.out.println(Constants.ErrorMessages.ERROR_INVALID_YES_NO_INPUT);
             }
         }
     }
 }
 