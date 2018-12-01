package com.ofs.training;

//Print Fibonacci series using recursion

class FibonacciRecursion {

     int numOne = 0;
     int numTwo = 1;
     int res;

    public void printFibonacciRecursion(int count) {

        if (count > 0) {
        res = numOne + numTwo;
        numOne = numTwo;
        numTwo = res;
        System.out.print(res + "   ");
        printFibonacciRecursion(count - 1);
        }

    }

    public static void main(String[] args) {

        FibonacciRecursion series = new FibonacciRecursion();
        System.out.print("Fibonacci series using RECURSION : ");
        System.out.print(series.numOne + "   " + series.numTwo + "   ");
        series.printFibonacciRecursion(10);

    }

}