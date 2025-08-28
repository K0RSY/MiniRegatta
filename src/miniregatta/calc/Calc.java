package miniregatta.calc;

public class Calc {
    static public float findA(int degrees, float c) {
        return (float) - Math.cos(Math.toRadians(degrees)) * c;
    }

    static public float findB(int degrees, float c) {
        return (float) Math.sin(Math.toRadians(degrees)) * c;
    }

    static public int findDegrees(float a, float b) {
        int result = (int) Math.round(Math.toDegrees(Math.atan((double) -b / a)));
        if (a >= 0) {
            result += 180;
        }
        return getRoundedDegrees(result);
    }

    static public int getRoundedDegrees(int degrees) {
        while (degrees < 0) {
            degrees += 360;
        }
        return (degrees) % 360;
    }

    static public int findDifference(int degrees_a, int degrees_b) {
        int result = degrees_a - degrees_b;
        result = Math.abs(result) > 180 ? - (result % 180) : result;
        return result;
    }
}