// write a class to throw your own exception if the input value is not valid
package com.ofs.training;

public class CustomExceptionHandling {

    private void division(int dividend, int divisor) throws InvalidDivisorException {

        if (divisor == 0) {
            throw new InvalidDivisorException("Divisor cannot be " + divisor);
        }

        int result = dividend / divisor;
        System.out.format("%d/%d = %d", dividend, divisor, result);
    }

    public static void main(String[] args) {

        CustomExceptionHandling customException = new CustomExceptionHandling();

        int dividend = 10;
        int divisor = 0;

        try {
            customException.division(dividend, divisor);
        } catch(InvalidDivisorException invalid) {
            System.out.format("%d/%d = UNDEFINED RESULT \n", dividend, divisor);
            System.out.format("Exception occured: %s", invalid);
        }
    }
}
