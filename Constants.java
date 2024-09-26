/**
 * This class contains a set of constant strings used throughout the program
 * to display messages and show the flow of operations on strings.
 * Owner: Tanuj Yadav
 * Date: 23/09/2024
 */
class Constants {
    public static final String WELCOME_MESSAGE = "Welcome to the Number Converter!";
    public static final String ENTER_BASE_MESSAGE = "Please enter a base (2-16): ";
    public static final String ENTER_NUMBER_MESSAGE = "Enter a number in base %d: ";
    public static final String OPERATION_PROMPT = "Do you want to convert (c) or operate (o) on the number? ";
    public static final String ANOTHER_CONVERSION_OR_OPERATION = "Do you want to perform another conversion/operation? (y/n): ";
    public static final String END_PROGRAM_PROMPT = "Do you want to end the program? (y/n): ";
    public static final String GOODBYE_MESSAGE = "Thank you for using the Number Converter. Goodbye!";
    public static final String RESTART_PROGRAM_MESSAGE = "Restarting the program...\n";
    public static final String CONVERT_MESSAGE = "Choose a base to convert to: ";
    public static final String OPERATION_SELECTION_PROMPT = "Choose an operation (+ or -): ";
    public static final String RESULT_IN_DECIMAL = "Result in Decimal: ";
    public static final String RESULT_IN_ORIGINAL_BASE = "Result in base ";
    public static final String RESULT = "Result in ";
    /**
     * This class contains a set of error messages used throughout the program.
     * These messages provide feedback for various types of invalid input or conditions.
     */
    public static class ErrorMessages {
        public static final String ERROR_BASE_RANGE = "Error: Base must be between 2 and 16.";
        public static final String ERROR_INVALID_INPUT_BASE = "Error: Invalid input. Please enter a valid base.";
        public static final String ERROR_INVALID_NUMBER = "Error: Invalid number for base ";
        public static final String ERROR_NUMBER_TOO_LONG = "Error: Number is too long. Maximum length is 100 characters.";
        public static final String ERROR_INVALID_INPUT = "Error: Invalid input. Please try again.";
        public static final String ERROR_INVALID_OPERATION = "Error: Invalid operation. Please enter either + or -.";
        public static final String ERROR_ENTER_SINGLE_CHARACTER = "Error: Please enter a single character.";
        public static final String ERROR_INVALID_YES_NO_INPUT = "Error: Please enter 'y' or 'n'.";
    }
}
