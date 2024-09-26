/**
 * This class contains a set of constant strings used throughout the program
 * to display messages and show the flow of operations on strings.
 * Owner: Tanuj Yadav
 * Date: 23/09/2024
 */
class Constants {
    public static final String WelcomeMessage = "Welcome to the Number Converter!";
    public static final String EnterBase = "Please enter a base (2-16): ";
    public static final String EnterNumber = "Enter a number in base %d: ";
    public static final String OperationPrompt = "Do you want to convert (c) or operate (o) on the number? ";
    public static final String AnotherConversion = "Do you want to perform another conversion/operation? (y/n): ";
    public static final String EndsProgram = "Do you want to end the program? (y/n): ";
    public static final String GoodByeMessage= "Thank you for using the Number Converter. Goodbye!";
    public static final String RestartProgram = "Restarting the program...\n";
    public static final String ConvertMessage = "Choose a base to convert to: ";
    public static final String OperationSelection = "Choose an operation (+ or -): ";
    public static final String ResultInDecimal = "Result in Decimal: ";
    public static final String ResultInBase = "Result in base ";
    public static final String Result = "Result in ";

    /**
     * This class contains a set of error messages used throughout the program.
     * These messages provide feedback for various types of invalid input or conditions.
     */
    public static class ErrorMessages {
        public static final String ErrorBaseRange = "Error: Base must be between 2 and 16.";
        public static final String ErrorInvalidBase = "Error: Invalid input. Please enter a valid base.";
        public static final String ErrorInvalidNumber = "Error: Invalid number for base ";
        public static final String ErrorNumberTooLong = "Error: Number is too long. Maximum length is 100 characters.";
        public static final String ErrorInvalidInput = "Error: Invalid input. Please try again.";
        public static final String ErrorInvalidOperation = "Error: Invalid operation. Please enter either + or -.";
        public static final String ErrorSingleCharacter = "Error: Please enter a single character.";
        public static final String ErrorYesOrNO = "Error: Please enter 'y' or 'n'.";
    }
}
