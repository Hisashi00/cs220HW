package drawshapes;

import java.awt.Color;

/**
 * Utilities class containing methods to convert
 * strings to colors, colors to strings, write to files,
 * and so on.
 * 
 * This class contains static utility methods. It doesn't
 * make sene to create instances of this class.
 */
public class Util
{
    // private constructor
    private Util() {}
    public static String colorToString(Color color) {
        if (color.equals(Color.RED)) {
            return "RED";
        } else if (color.equals(Color.BLUE)) {
            return "BLUE";
        } else if (color.equals(Color.YELLOW)) {
            return "YELLOW";
        } else if (color.equals(Color.GREEN)) {
            return "GREEN";
        } else if (color.equals(Color.MAGENTA)) {
            return "MAGENTA";
        } else if (color.equals(Color.ORANGE)) {
            return "ORANGE";
        } else if (color.equals(Color.PINK)) {
            return "PINK";
        }
        throw new UnsupportedOperationException("Unexpected color: " + color);
    }
    

    public static Color stringToColor(String color) {
        if (color.equals("RED")) {
            return Color.RED;
        } else if (color.equals("BLUE")) {
            return Color.BLUE;
        } else if (color.equals("YELLOW")) {
            return Color.YELLOW;
        } else if (color.equals("GREEN")) {
            return Color.GREEN;
        } else if (color.equals("MAGENTA")) {
            return Color.MAGENTA;
        } else if (color.equals("ORANGE")) {
            return Color.ORANGE;
        } else if (color.equals("PINK")) {
            return Color.PINK;
        }
        throw new UnsupportedOperationException("Unexpected color: " + color);
    }
    
}
