package com.ofs.training;

// print fibonacci using for loop, while loop

class Fibonacci {

    int numOne;
    int numTwo;
    int res;

    public void printFibonacciFor() {

        System.out.print("Fibonacci Series using FOR loop: ");

        for (int index = 0; index < 1; index++ ) {
            numOne = index;
            numTwo = ++index;
            System.out.print(numOne + "   " + numTwo + "   ");
        }

        for ( int index = 0; index < 10; index++ ) {
            res = numOne + numTwo;
            numOne = numTwo;
            numTwo = res;
            System.out.print(res + "   ");
        }

        System.out.println();
    }

    public void printFibonacciWhile() {

        System.out.print("Fibonacci Series using WHILE loop: ");

        int index = 0;
        while (index < 11) {

            if (index < 1) {

                numOne = index;
                numTwo = ++index;
                System.out.print(numOne + "   " + numTwo + "   ");
                continue;

            } else {

                res = numOne + numTwo;
                numOne = numTwo;
                numTwo = res;
                System.out.print(res + "   ");

            }

        index++;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Fibonacci series = new Fibonacci();
        series.printFibonacciFor();
        series.printFibonacciWhile();

    }

}
