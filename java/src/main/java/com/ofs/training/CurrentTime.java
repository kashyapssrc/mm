/**
 *
 */
package com.ofs.training;

import java.time.LocalTime;
import java.util.Date;

/**
 * @author mohammed.mohammed
 * @since  Sep 25, 2018
 */
public class CurrentTime {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Date presentDate = new Date();

        System.out.println("Current Time :");
        System.out.println(presentDate);
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalTime.now());
    }

}
