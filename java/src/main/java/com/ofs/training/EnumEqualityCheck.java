/**
 * 
 */
package com.ofs.training;

/**
 * @author mohammed.mohammed
 *
 */
// class EnumEqualityCheck {
public class EnumEqualityCheck {

    private static final String ERR_NULL_ENUM_VALUE = "Cannot accept NULL enum values";
    
    // enum enumName {enumValue}
    public enum RainbowColour {
                        VIOLET,
                        INDIGO,
                        BLUE,
                        GREEN,
                        YELLOW,
                        ORANGE,
                        RED;
    }
    
    public boolean equalsMethodCompare(RainbowColour colour,
                                       RainbowColour anotherColour) {

        if (colour == null || anotherColour == null) {
            throw new RuntimeException(ERR_NULL_ENUM_VALUE);
        }
        
        // compare enumObject.equals(anotherEnumObject);
        boolean check = colour.equals(anotherColour);
        return check;
    }

    public boolean equalsOperatorCompare(RainbowColour colour,
                                         RainbowColour anotherColour) {

        if (colour == null || anotherColour == null) {
            throw new RuntimeException(ERR_NULL_ENUM_VALUE);
        }
        
        // compare enumObject == anotherEnumObject;
        boolean check = (colour == anotherColour);
        return check;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        EnumEqualityCheck enumEqualityCheck = new EnumEqualityCheck();

        System.out.println("Equality Check for Enum values RED and BLUE :");

        boolean equalsMethodFirstCheck = enumEqualityCheck.
                                         equalsMethodCompare(
                                         RainbowColour.RED,
                                         RainbowColour.BLUE);

        boolean equalsOperatorFirstCheck = enumEqualityCheck.
                                           equalsOperatorCompare(
                                           RainbowColour.RED,
                                           RainbowColour.BLUE);

        System.out.println(equalsMethodFirstCheck);
        System.out.println(equalsOperatorFirstCheck);

        System.out.println("Equality Check for Enum values GREEN and RED :");

        boolean equalsMethodSecondCheck = enumEqualityCheck.
                                          equalsMethodCompare(
                                          RainbowColour.GREEN,
                                          RainbowColour.RED);

        boolean equalsOperatorSecondCheck = enumEqualityCheck.
                                            equalsOperatorCompare(
                                            RainbowColour.GREEN,
                                            RainbowColour.RED);

        System.out.println(equalsMethodSecondCheck);
        System.out.println(equalsOperatorSecondCheck);
    }

}
