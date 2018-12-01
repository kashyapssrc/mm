package com.ofs.training;

//Compare the enum values using equal method and == operator

// class EnumEqualityChecker {
public class EnumEqualityChecker {

    // enum enumName {enumValue}
    enum RainbowColour {
        VIOLET,
        INDIGO,
        BLUE,
        GREEN,
        YELLOW,
        ORANGE,
        RED;
    }

    // static void execute() {
    public static void main(String[] args) {

        // enumName enumObject = enumValue;
        RainbowColour vibgyorFirst = RainbowColour.RED;
        RainbowColour vibgyorSecond = RainbowColour.BLUE;
        RainbowColour vibgyorThird = RainbowColour.GREEN;
        RainbowColour vibgyorFourth = RainbowColour.RED;

        // Console console = new Console()....
        // console.print(enumValue);
        System.out.println(vibgyorFirst);
        System.out.println(vibgyorSecond);
        System.out.println(vibgyorThird);
        System.out.println(vibgyorFourth);

        // compare enumObject.equals(anotherEnumObject);
        if (vibgyorFirst.equals(vibgyorFourth)) {

            // Console console = new Console()....
            // console.print(message for equality);
            System.out.println("first and fourth are equal");
        }

        // compare enumObject == anotherEnumObject;
        if ( vibgyorSecond == vibgyorThird) {

            // Console console = new Console()....
            // console.print(message for equality);
            System.out.println("Second and Third are equal");
        }

    }
}