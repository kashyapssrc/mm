package com.ofs.training;

// demonstrate object equality using Object.equals() vs == using String objects

// class ObjectEqualityDemonstrator{
public class DemoObjectEquality {

    // String object = "...";
    String stringOne = "This is called first string";
    String stringTwo = "This is called second string";
    String stringThree = "This is called third string";
    String stringFour = "This is called third string";
    String five = new String("Almas");
    String six = new String("Almas");
    

    // static void execute() {
    public static void main(String[] args) {

        // ObjectEqualityDemonstrator currentProgram = getProgram();
        DemoObjectEquality objectEquality = new DemoObjectEquality();

        // compare currentProgram.object == currentProgram.object;
        // Console console = new Console()....
        // Console.print(message for equalities);
        if(objectEquality.stringOne == objectEquality.stringTwo) {
            System.out.println("stringOne and stringTwo are equal");
        }

        // compare currentProgram.object.equals(currentProgram.object);
        // Console console = new Console()....
        // Console.print(message for equalities);
        if(objectEquality.stringTwo.equals(objectEquality.stringThree)) {
            System.out.println("stringTwo and stringThree are equal");
        }

        if(objectEquality.stringThree.equals(objectEquality.stringFour)) {
            System.out.println("stringThree and stringFour are equal");
        }

        System.out.println(objectEquality.five == objectEquality.six);
        System.out.println(objectEquality.five.equals(objectEquality.six));
        System.out.println(objectEquality.stringFour == objectEquality.stringThree);
        
        
    }
}
