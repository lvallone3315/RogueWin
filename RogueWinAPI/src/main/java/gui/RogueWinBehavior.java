/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gui;

import java.awt.event.KeyEvent;

/**
 *
 * Interface to be implemented to handle all event listening for a
 * {@link RogueWin} object.
 *
 * @author Dylan Johnson
 */
public interface RogueWinBehavior {

    /**
     * Function called every time a key is pressed, repeats if the key is
     * held down
     *
     * @param e The event passed on the key press
     */
    public void keyPressed(KeyEvent e);

    /**
     * Function called every time a key is released
     *
     * @param e The event passed on the key release
     */
    public void keyReleased(KeyEvent e);

    /**
     * Function called only when the key is initially pressed, not repeated on
     * hold
     *
     * @param e The event passed on the key pressed
     */
    public void keyDown(KeyEvent e);
}
