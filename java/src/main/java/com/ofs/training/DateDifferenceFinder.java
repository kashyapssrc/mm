/**
 *
 */
package com.ofs.training;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author mohammed.mohammed
 * @since  Sep 25, 2018
 */
public class DateDifferenceFinder {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2018, 9, 15);
        LocalDate endDate = LocalDate.of(2018, 9, 25);

        Period dateDifference = Period.between(startDate, endDate);
        int periodDays = dateDifference.getDays();
        log("Difference between %s and %s is of %s days%n", startDate,
                                                            endDate,
                                                            periodDays);
    }

    private static void log(String printFormat, Object ...value) {
        System.out.format(printFormat, value);
    }
}
