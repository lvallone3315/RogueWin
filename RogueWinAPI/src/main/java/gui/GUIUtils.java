/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Dylan Johnson
 */
public class GUIUtils {
    public static int clamp(int val, int min, int max) {
        if(val < min)
            return min;
        
        if(val > max)
            return max;
        
        return val;
    }
}
