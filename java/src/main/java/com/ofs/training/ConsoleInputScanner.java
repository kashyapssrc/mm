/**
 *
 */
package com.ofs.training;

import java.util.Scanner;

/**
 * @author mohammed.mohammed
 * @since  Sep 27, 2018
 */
public class ConsoleInputScanner {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConsoleInputScanner inputScanner = new ConsoleInputScanner();
        Scanner scan = new Scanner(System.in);
        System.out.print("Perform integer addition? (Y/N): ");
        String confirmation = scan.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            System.out.print("Enter first integer: ");
            int firstInteger = scan.nextInt();
            System.out.print("Enter second integer: ");
            int secondInteger = scan.nextInt();

            int sum = firstInteger + secondInteger;
            inputScanner.log("Result: %d + %d = %d", firstInteger, secondInteger, sum);

        } else if (confirmation.equalsIgnoreCase("N")) {
            System.out.println("Program Terminated");
            System.exit(0);

        } else {
            System.out.println("INVALID INPUT !!!");
        }

        scan.close();
    }

    private void log(String printFormat, Object... values) {
        System.out.format(printFormat, values);
    }
}
