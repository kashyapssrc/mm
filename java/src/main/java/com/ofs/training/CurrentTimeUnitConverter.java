/**
 *
 */
package com.ofs.training;

import java.time.LocalTime;

/**
 * @author mohammed.mohammed
 * @since  Sep 26, 2018
 */
public class CurrentTimeUnitConverter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        log("Current time: %s%n", currentTime);

        long currentTimeInMillis = System.currentTimeMillis();
        log("Current Time in milliseconds: %d%n", currentTimeInMillis);

        long currentTimeInNanos = System.nanoTime();
        log("Current Time in nanoseconds: %d%n", currentTimeInNanos);
    }

    private static void log(String printFormat, Object ...values) {
        System.out.format(printFormat, values);
    }
}
