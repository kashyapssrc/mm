/**
 *
 */
package com.ofs.training;

import java.time.Instant;

/**
 * @author mohammed.mohammed
 * @since  Sep 26, 2018
 */
public class EpochTimePrinter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Instant epochInstant = Instant.EPOCH;
        log("Epoch Time : %s", epochInstant);
    }

    private static void log(String printFormat, Object ...values) {
        System.out.format(printFormat, values);
    }
}
