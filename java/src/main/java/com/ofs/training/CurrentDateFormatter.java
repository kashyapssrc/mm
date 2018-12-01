/**
 *
 */
package com.ofs.training;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author mohammed.mohammed
 * @since  Sep 25, 2018
 */
public class CurrentDateFormatter {

    private void formatCurrentDate() {
        ZoneId zoneId = ZoneId.of("US/Pacific");
        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        String currentDateFormatted = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                                                       .format(currentDate);

        System.out.format("Current Date in one format: ");
        log("%s%n",currentDateFormatted);
    }

    private void anotherFormatCurrentDate() {
        ZoneId zoneId = ZoneId.of("US/Pacific");
        ZonedDateTime currentDate = ZonedDateTime.now(zoneId);
        String currentDateFormatted = DateTimeFormatter.ofPattern("yyyy.MM.dd 'at' HH:mm:ss z")
                                                       .format(currentDate);

        System.out.format("Current Date in another format: ");
        log("%s%n",currentDateFormatted);
    }

    private void log(String printFormat, Object ...value) {
        System.out.format(printFormat, value);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CurrentDateFormatter currentDateFormatter = new CurrentDateFormatter();
        currentDateFormatter.formatCurrentDate();
        currentDateFormatter.anotherFormatCurrentDate();
    }
}
