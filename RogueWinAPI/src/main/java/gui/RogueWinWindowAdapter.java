/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Dylan Johnson
 */
public class RogueWinWindowAdapter extends WindowAdapter {

    private static int windowCount = 0;
    
    public RogueWinWindowAdapter() {
        windowCount++;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        windowCount--;
        
        if(windowCount == 0) {
            System.exit(0);
        }
    }
}
