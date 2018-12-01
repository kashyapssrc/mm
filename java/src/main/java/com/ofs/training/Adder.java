/**
 *
 */
package com.ofs.training;

/**
 * @author mohammed.mohammed
 *
 */
public class Adder {

    private static final String ERR_MIN_ARGS = "Cannot accept less than two integers for addition";

    public int addIntegers(String[] args) {

        if (args.length <= 1) {
            throw new RuntimeException(ERR_MIN_ARGS);
        }

        int sum = 0;
        for (String arg : args) {
            int integerValue = Integer.valueOf(arg);
            sum = sum + integerValue;
        }

        return sum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Adder adder = new Adder();

        int integerSum = adder.addIntegers(args);
        System.out.format("Sum of integers = %d", integerSum);
    }
}
