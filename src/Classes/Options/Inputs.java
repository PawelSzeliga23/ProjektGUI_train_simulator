package Classes.Options;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {
    final static String incorrectInputCommunicate = "Incorrect input, please enter a value that describes the option";
    static Scanner scanner = new Scanner(System.in);

    public static int userMenuInput() {
        while (true) {
            try {
                System.out.print("Type in selected option's number:");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, your value is not number." + '\n' +
                        "Please enter new value that describes option");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double doubleCreatorInput() {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input < 0) {
                    System.out.println("Incorrect input, your value is not not positive number." + '\n' +
                            "Please enter positive number");
                } else
                    return input;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, your value is not number." + '\n' +
                        "Please enter new value");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static int integerCreatorInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Incorrect input, your value is not not positive number." + '\n' +
                            "Please enter positive number");
                } else
                    return input;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, your value is not number." + '\n' +
                        "Please enter new value");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static boolean booleanCreatorInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("yes") || input.equals("Yes") || input.equals("YES"))
                return true;
            else if (input.equals("no") || input.equals("No") || input.equals("NO"))
                return false;
            else {
                System.out.println("Incorrect input, please enter yes/no :");
            }
        }
    }
}
