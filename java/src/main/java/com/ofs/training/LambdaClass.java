package com.ofs.training;

public class LambdaClass {

    interface FirstInterface {
        public int add(int a, int b);
    }

    interface SecondInterface {
        public String printSum(int c);
    }

    public int addition(int a, int b, FirstInterface fiObj) {
        return fiObj.add(a, b);
    }

    public String printResult(int res, SecondInterface siObj) {
        return siObj.printSum(res);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        FirstInterface sum = (firstInteger, secondInteger) -> firstInteger + secondInteger;
        SecondInterface result = (integerSum) -> "Result = " + integerSum;

        LambdaClass lambdaTest = new LambdaClass();

        int res = lambdaTest.addition(10, 5, sum);
        System.out.println(lambdaTest.printResult(res, result));
    }

}
