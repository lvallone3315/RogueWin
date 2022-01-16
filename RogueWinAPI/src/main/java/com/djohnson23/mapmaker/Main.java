package com.djohnson23.mapmaker;

import gui.RogueWin;
import gui.RogueWinBehavior;
import gui.Tileset;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dylan Johnson
 */
public class Main {

    static final int NUM_TILES = 25;

    public static void main(String[] args) {

        Tileset defaultTiles = new Tileset("res/DefaultTiles.png", 12, 12);

        RogueWin window = new RogueWin("My Map", 800, 800, 30);

        window.displayMap(randomGrid(window.getGridSize()));

        window.addBehavior(new RogueWinBehavior() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Up Key Pressed!");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }

            @Override
            public void keyDown(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Up Key Down!");
                }
            }
        });

        /*
        BufferedImage[][] tiles = new BufferedImage[defaultTiles.getRows()][defaultTiles.getRows()];
       
        for(int r = 0; r < window.getGridSize(); r++) {
            for(int c = 0; c < window.getGridSize(); c++) {
                tiles[r][c] = defaultTiles.getTile(r, c);
            }
        }
        
        BufferedImage[][] layer2 = new BufferedImage[defaultTiles.getRows()][defaultTiles.getRows()];
        
        layer2[0][0] = defaultTiles.getTile(11, 6);
        window.addLayer(layer2);
        
        window.updateLayer(0, tiles);
         */
        Random random = new Random();

        try {
            while (true) {
                window.displayMap(randomGrid(window.getGridSize()));
                Thread.sleep(1000);

                int max = RogueWin.MAX_GRIDSIZE;
                int min = RogueWin.MIN_GRIDSIZE;

                int size = random.nextInt(max - min + 1) + min;

                window.setGridSize(size);

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static char[][] randomGrid(int gridSize) {
        char[][] grid = new char[gridSize][gridSize];

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                grid[r][c] = (char) (Math.random() * 26 + 97);
            }
        }

        return grid;
    }
}
