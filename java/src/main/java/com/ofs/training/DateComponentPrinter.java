/**
 *
 */
package com.ofs.training;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author mohammed.mohammed
 * @since  Sep 25, 2018
 */
public class DateComponentPrinter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        log("Current date is: %s%n", currentDate);

        int dayOfMonth = currentDate.getDayOfMonth();
        log("Day of the Month: %d%n", dayOfMonth);

        Month month = currentDate.getMonth();
        log("Month: %s%n", month);

        int year = currentDate.getYear();
        log("Year: %d%n", year);
    }

    private static void log(String printFormat, Object ...values) {
        System.out.format(printFormat, values);
    }
}
