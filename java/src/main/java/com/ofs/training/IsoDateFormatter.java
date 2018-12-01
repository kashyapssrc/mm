/**
 *
 */
package com.ofs.training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author mohammed.mohammed
 * @since  Sep 25, 2018
 */
public class IsoDateFormatter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateformat = DateTimeFormatter.ISO_DATE;
        String currentDateFormatted = currentDate.format(dateformat);
        log("Current date in ISO Standard: %s%n", currentDateFormatted);
    }

    private static void log(String printFormat, Object ...value) {
        System.out.format(printFormat, value);
    }
}
