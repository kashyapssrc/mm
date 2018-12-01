/**
 *
 */
package com.ofs.training;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author mohammed.mohammed
 * @since  Oct 1, 2018
 */
public class BigDecimalRounder {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BigDecimal bigDecimalNumber = new BigDecimal(123456.654321);
//        MathContext roundSetting = new MathContext(8, RoundingMode.UP);

//        BigDecimal roundedBigDecimal = bigDecimalNumber.round(roundSetting);
//        log("%f rounded to precision %d in %s rounding mode, scaled to %d decimal points:%n%f",
//                                                                   bigDecimalNumber,
//                                                                   roundSetting.getPrecision(),
//                                                                   roundSetting.getRoundingMode(),
//                                                                   scaledBigDecimal.scale(),
//                                                                   scaleBigDecimal);

        BigDecimal scaledBigDecimal = bigDecimalNumber.setScale(4, RoundingMode.UP);
        log("%f rounded to %d decimal points : %f%n", bigDecimalNumber,
                                                      scaledBigDecimal.scale(),
                                                      scaledBigDecimal);

        BigDecimal absoluteBigDecimal = bigDecimalNumber.abs();
        log("Absolute value of %f is : %f%n", bigDecimalNumber, absoluteBigDecimal);

        BigDecimal ceilingBigDecimal = bigDecimalNumber.setScale(3, BigDecimal.ROUND_CEILING);
        log("Ceiling value of %f is : %f%n", bigDecimalNumber, ceilingBigDecimal);

        BigDecimal floorBigDecimal = bigDecimalNumber.setScale(3, BigDecimal.ROUND_FLOOR);
        log("Floor value of %f is : %f%n", bigDecimalNumber, floorBigDecimal);

        double doubleTypeValue = bigDecimalNumber.doubleValue();
        double rintValue = Math.rint(doubleTypeValue);
        log("The rounded integer value for %f is : %f%n", bigDecimalNumber, rintValue);

        BigDecimal anotherBigDecimal = BigDecimal.valueOf(9876.54321);
        BigDecimal maxValue = bigDecimalNumber.max(anotherBigDecimal);
        log("Maximum value out of %f and %f is : %f%n", bigDecimalNumber,
                                                    anotherBigDecimal,
                                                    maxValue);

        BigDecimal minValue = bigDecimalNumber.min(anotherBigDecimal);
        log("Minimum value out of %f and %f is : %f%n", bigDecimalNumber,
                                                    anotherBigDecimal,
                                                    minValue);
    }

    private static void log(String printFormat, Object... values) {
        System.out.format(printFormat, values);
    }
}
