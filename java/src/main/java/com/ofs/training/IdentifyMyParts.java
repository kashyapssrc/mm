package com.ofs.training;

// Consider the following class:
    // public class IdentifyMyParts {
        // public static int x = 7;
        // public int y = 3;
    // }
    // - What are the class variables?
    // - What are the instance variables?

// ---> In public static int x = 7, x is the class variable since it is declared as static and can be used over the whole class

// ---> In public int y = 3, y is the instance variable since it is not declared as static and can be used for instance only

// - What is the output from the following code:
    // IdentifyMyParts a = new IdentifyMyParts();
    // IdentifyMyParts b = new IdentifyMyParts();
    // a.y = 5;
    // b.y = 6;
    // a.x = 1;
    // b.x = 2;
    // System.out.println("a.y = " + a.y);
    // System.out.println("b.y = " + b.y);
    // System.out.println("a.x = " + a.x);
    // System.out.println("b.x = " + b.x);
    // System.out.println("IdentifyMyParts.x = " + IdentifyMyParts.x);

class IdentifyMyParts {

    public static int x = 7;
    public int y = 3;

    public static void main(String[] args) {

        IdentifyMyParts a = new IdentifyMyParts();
        IdentifyMyParts b = new IdentifyMyParts();

        a.y = 5;
        b.y = 6;
        a.x = 1;
        b.x = 2;

        System.out.println("a.y = " + a.y);
        System.out.println("b.y = " + b.y);
        System.out.println("a.x = " + a.x);
        System.out.println("b.x = " + b.x);
        System.out.println("IdentifyMyParts.x = " + IdentifyMyParts.x);

    }
}