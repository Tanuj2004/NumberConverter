/***
 * NumberOperations class is used to perform operation like conversion from one base to another upto 16 base.
 * And perform addition and subtraction on the number of the same base.
 * Owner: Tanuj Yadav
 * Date: 23/09/2024
 */
import java.util.Scanner;
public class NumberOperations {
    /*
     * Converts the given number from one base to decimal (base 10).
     * @param number The input number as a string.
     * @param base   The base of the input number.
     * @return The decimal equivalent of the input number as a string.
     */
    public static String convertToDecimal(String number, int base) {
        String decimalValue = "0";
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
            decimalValue = add(multiply(decimalValue, base), String.valueOf(value));
        }
        return decimalValue;
    }
    /*
     * Converts a given decimal number to any base (e.g., binary, octal, or hexadecimal).
     * @param number The decimal number to be converted as a string.
     * @param base   The target base (between 2 and 16).
     * @return The number converted to the target base as a string.
     */
    public static String decimalToBase(String number, int base) {
        if (number.equals("0")) {
            return "0";
        }
        String result = "";
        boolean isNegative = false;
        if (number.startsWith("-")) {
            isNegative = true;
            number = number.substring(1);
        }
        while (!number.equals("0")) {
            String[] divResult = divide(number, base);
            String remainder = divResult[1];
            number = divResult[0];

            char digitChar;
            int remainderInt = Integer.parseInt(remainder);
            if (remainderInt < 10) {
                digitChar = (char) ('0' + remainderInt);
            } else {
                digitChar = (char) ('A' + (remainderInt - 10));
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
    public static void performConversion(String number, int base, Scanner sc) {
        System.out.print(Constants.ConvertMessage);
        int convertToBase = NumberConverter.getBase(sc);
        String decimalValue = convertToDecimal(number, base);
        String convertedValue = decimalToBase(decimalValue, convertToBase);
        System.out.println(Constants.ResultInDecimal + decimalValue);
        System.out.println(Constants.ResultInBase + convertToBase + " base: " + convertedValue);
    }

    /*
     * Performs operations (addition or subtraction) on two numbers in the same base.
     * @param number The first number for the operation.
     * @param base   The base of the numbers.
     * @param input  Scanner object to read user input.
     */
    public static void performOperation(String number, int base, Scanner sc) {
        String firstDecimalValue = convertToDecimal(number, base);
        char operation;

        while (true) {
            System.out.print(Constants.OperationSelection);
            operation = NumberConverter.getValidCharInput(sc);
            if (operation == '+' || operation == '-') {
                break;
            } else {
                System.out.println(Constants.ErrorMessages.ErrorInvalidOperation);
            }
        }

        String secondNumber = NumberConverter.getNumberFromUser(sc, base);
        String secondDecimalValue = convertToDecimal(secondNumber, base);

        String result;
        if (operation == '+') {
            result = add(firstDecimalValue, secondDecimalValue);
        } else {
            result = subtract(firstDecimalValue, secondDecimalValue);
        }

        System.out.println(Constants.ResultInDecimal + result);
        System.out.println(Constants.ResultInBase+ base + ": " + decimalToBase(result, base));
    }
    private static String add(String num1, String num2) {
        String result = "";
        int carry = 0;
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        num1 = reverse(num1);
        num2 = reverse(num2);

        for (int i = 0; i < num1.length(); i++) {
            int sum = carry + (num1.charAt(i) - '0');
            if (i < num2.length()) {
                sum += (num2.charAt(i) - '0');
            }
            result += sum % 10; 
            carry = sum / 10; 
        }

        if (carry != 0) {
            result += carry; 
        }

        return reverse(result); 
    }

    private static String subtract(String num1, String num2) {
        boolean negativeResult = false;
        if (num1.length() < num2.length() || (num1.length() == num2.length() && num1.compareTo(num2) < 0)) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
            negativeResult = true;
        }
    
        int borrow = 0;
        String result = "";
        num1 = reverse(num1);
        num2 = reverse(num2);
    
        for (int i = 0; i < num1.length(); i++) {
            int diff = (num1.charAt(i) - '0') - borrow;
    
            if (i < num2.length()) {
                diff -= (num2.charAt(i) - '0');
            }
    
            if (diff < 0) { 
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
    
            result += diff; 
        }
        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result = result.substring(0, result.length() - 1);
        }
    
        result = reverse(result);
        if (negativeResult) {
            result = "-" + result;
        }
    
        return result;
    }
    
    private static String multiply(String num, int multiplier) {
        String result = "";
        int carry = 0;

        for (int i = num.length() - 1; i >= 0; i--) {
            int product = carry + ((num.charAt(i) - '0') * multiplier);
            result += product % 10;
            carry = product / 10;
        }

        while (carry != 0) {
            result += carry % 10;
            carry /= 10;
        }

        return reverse(result); 
    }
    private static String[] divide(String num, int divisor) {
        String quotient = "";
        int remainder = 0;

        for (int i = 0; i < num.length(); i++) {
            remainder *= 10;
            remainder += (num.charAt(i) - '0');
            quotient += remainder / divisor;
            remainder %= divisor;
        }
        while (quotient.length() > 1 && quotient.charAt(0) == '0') {
            quotient = quotient.substring(1);
        }

        return new String[] { quotient.isEmpty() ? "0" : quotient, Integer.toString(remainder) };
    }
    private static String reverse(String str) {
        String reversedStr = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversedStr += str.charAt(i);
        }
        return reversedStr;
    }
}