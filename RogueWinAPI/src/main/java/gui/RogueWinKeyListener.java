/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dylan Johnson
 */
class RogueWinKeyListener implements KeyListener {

    private static final int MAX_KEYCODE = 525;

    private ArrayList<RogueWinBehavior> behaviorList;
    private boolean[] currentPressed;

    public RogueWinKeyListener() {
        behaviorList = new ArrayList<>();
        currentPressed = new boolean[MAX_KEYCODE + 1];
    }

    public void addBehavior(RogueWinBehavior b) {
        behaviorList.add(b);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Deliberately empty
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() < 0 || e.getKeyCode() > MAX_KEYCODE) {
            return;
        }

        for (RogueWinBehavior b : behaviorList) {
            if (!currentPressed[e.getKeyCode()]) {
                b.keyDown(e);
            }

            b.keyPressed(e);
        }

        currentPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() < 0 || e.getKeyCode() > MAX_KEYCODE) {
            return;
        }

        for (RogueWinBehavior b : behaviorList) {
            b.keyReleased(e);
        }

        currentPressed[e.getKeyCode()] = false;
    }

}
