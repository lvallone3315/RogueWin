/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 * Utility class to assist with common functions that could be used in
 * conjunction with the RogueWin class
 *
 * @author Dylan Johnson
 */
public final class GUIUtils {

    private GUIUtils() {
        throw new IllegalStateException("Can not instantiate Utility Class");
    }

    /**
     *
     * @param val Value to be clamped between min and max
     * @param min Minimum (inclusive) value that val should be
     * @param max Maximum (inclusive) value that val should be
     * @return If val is less then minimum, min is returned. If val is greater
     * than max, max is returned. Otherwise returns val.
     */
    public static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        }

        if (val > max) {
            return max;
        }

        return val;
    }
}
