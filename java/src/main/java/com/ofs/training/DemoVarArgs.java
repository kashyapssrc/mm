package com.ofs.training;

// Demonstration of VarArgs Example

// class VarArgsDemonstration {
public class DemoVarArgs {

// varArgsSum(... variableArguments) {
    void varArgsSum(int... argument) {

        // print(No. of arguments);
        System.out.println("Number of arguments = " + argument.length);

        // find values of variableArguments
        // print(values of variableArguments)
        System.out.print("Value of Arguments : ");

        for (int index : argument) {
            System.out.print(index + "  ");
        }

        // finding sum of variableArguments
        int sum = 0;
        for (int index = 0; index < argument.length; index++) {
            sum = sum + argument[index];
        }

        // print(sum of variableArguments)
        System.out.println("\nSum of arguments = " + sum);

        System.out.println();
    }

    // static void execute(){
    public static void main(String[] args) {

         // DemoVarArgs varArgs = getCurrentProgram();
         DemoVarArgs varArgs = new DemoVarArgs();

        // calling varArgs(variable arguments);
        varArgs.varArgsSum(10);
        varArgs.varArgsSum(1, 2);
        varArgs.varArgsSum(5, 10, 15, 20, 25, 30);
        varArgs.varArgsSum();

    }
}

