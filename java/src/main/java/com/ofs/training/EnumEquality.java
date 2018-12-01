package com.ofs.training;


class EnumEquality {
    enum RainbowColour {
        VIOLET,
        INDIGO,
        BLUE,
        GREEN,
        YELLOW,
        ORANGE,
        RED;
    }

    public static void main(String[] args) {
    RainbowColour vibgyorFirst = RainbowColour.RED;
    RainbowColour vibgyorSecond = RainbowColour.BLUE;
    RainbowColour vibgyorThird = RainbowColour.GREEN;
    RainbowColour vibgyorFourth = RainbowColour.RED;

    System.out.println(vibgyorFirst);
    System.out.println(vibgyorSecond);
    System.out.println(vibgyorThird);
    System.out.println(vibgyorFourth);

    if (vibgyorFirst.equals(vibgyorFourth)) {
        System.out.println("first and fourth are equal");
    }

    if ( vibgyorSecond == vibgyorThird) {
        System.out.println("Second and Third are equal");
    }

    }
}