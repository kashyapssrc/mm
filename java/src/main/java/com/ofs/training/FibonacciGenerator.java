package com.ofs.training;

//print fibonacci using for loop, while loop

// class FibonacciSeriesGenarator {
public class FibonacciGenerator {

    int numOne;
    int numTwo;
    int resultant;

    // void fibonacciUsingForLoop() {
    public void printFibonacciFor() {

        System.out.print("Fibonacci Series using FOR loop: ");

        // fibonacciForLoop = getFibonacci();
        // Console console = getConsole()....
        // console.print(fibonacciForLoop);
        for (int index = 0; index < 1; index++ ) {
            numOne = index;
            numTwo = ++index;
            System.out.print(numOne + "   " + numTwo + "   ");
        }

        for ( int index = 0; index < 10; index++ ) {
            resultant = numOne + numTwo;
            numOne = numTwo;
            numTwo = resultant;
            System.out.print(resultant + "   ");
        }

        System.out.println();
    }

    // void fibonacciUsingWhileLoop() {
    public void printFibonacciWhile() {

        System.out.print("Fibonacci Series using WHILE loop: ");

        // fibonacciWhileLoop = getFibonacci();
        // Console console = getConsole()....
        // console.print(fibonacciWhileLoop);
        int index = 0;
        while (index < 11) {

            if (index < 1) {

                numOne = index;
                numTwo = ++index;
                System.out.print(numOne + "   " + numTwo + "   ");
                continue;

            } else {

                resultant = numOne + numTwo;
                numOne = numTwo;
                numTwo = resultant;
                System.out.print(resultant + "   ");

            }

        index++;
        }

        System.out.println();
    }

    // static void execute() {
    public static void main(String[] args) {

        // FibonacciSeriesGenerator currentProgram = getProgram();
        FibonacciGenerator series = new FibonacciGenerator();

        // currentProgram.fibonacciUsingForLoop();
        series.printFibonacciFor();

        // currentProgram.fibonacciUsingWhileLoop();
        series.printFibonacciWhile();

    }
}