/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * Class to manage all gui elements for a roguelike game.
 * Can be used with both a character grid or a grid of images
 * to display the map with
 * 
 * @author Dylan Johnson
 */
public class RogueWin {

    JFrame frame;
    String title;
    int gridSize;
    
    MapGridPanel gridPanel;
    RogueWinFrameAdapter adapter;

    /**
     * 
     * Creates the window for the map with the given parameters. Defaults all characters
     * to a single space (Empty) and creates a window that is not resizeable
     * 
     * @param title The title of the window to be created
     * @param width The width (in px) of the window to be created
     * @param height The height (in px) of the window to be created
     * @param gridSize The size of the grid of tiles/characters for the map - always a square grid
     */
    public RogueWin(String title, int width, int height, int gridSize) {
        this.title = title;
        this.gridSize = gridSize;

        frame = new JFrame();
        frame.pack();
        setContentSize(width, height);
        
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        gridPanel = new MapGridPanel(gridSize, false, width, height);
        frame.add(gridPanel);
        adapter = new RogueWinFrameAdapter(gridPanel);
        frame.addComponentListener(adapter);
    }
    
    /**
     * Creates the window for the map with default width and height of 800px
     * and a grid size of 10
     */
    public RogueWin() {
        this("Rogue Map", 800, 800, 10);
    }
    
    /**
     * 
     * @return The title of the window 
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the title of the window displayed to the user
     * 
     * @param title New title for the window
     */
    public void setTitle(String title) {
        this.title = title;
        frame.setTitle(title);
    }

    /**
     * 
     * @return The width of the window in pixels excluding the insets - only the
     * content pane
     */
    public int getWidth() {
        return frame.getContentPane().getWidth();
    }

    /**
     * 
     * @return The height of the window in pixels excluding the insets - only the
     * content pane
     */
    public int getHeight() {
        return frame.getContentPane().getHeight();
    }

    /**
     * 
     * Updates the size of the window to the specified values - excludes the
     * insets of the window, width and height correspond to content pane size
     * 
     * @param width New width of the window in pixels
     * @param height New height of the window in pixels
     */
    public void setContentSize(int width, int height) {
        Insets insets = frame.getInsets();
        frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
    }

    /**
     * 
     * @return Grid size of the window - how many tiles are in each row and column
     */
    public int getGridSize() {
        return gridSize;
    }
    
    /**
     * 
     * Updates the gridSize of the map.
     * 
     * <p>
     * NOTICE: This will clear the map, it is recommended to display a new
     * map immediately after setting the gridSize
     * 
     * @param gridSize New grid size for the map - number of rows and columns
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        
        frame.remove(gridPanel);
        gridPanel = new MapGridPanel(gridSize, false, getWidth(), getHeight());
        adapter.setGridPanel(gridPanel);
        frame.add(gridPanel);
    }
    
    /**
     * 
     * Adds a new layer to the grid on top of the highest layer
     * with the given images as the tiles of the grid
     * 
     * @param tiles Images to set the  new layer's tiles as
     */
    public void addLayer(BufferedImage[][] tiles) {
        ImageGridLayer newLayer = new ImageGridLayer(gridSize);
        newLayer.setTiles(tiles);
        
        gridPanel.addLayer(newLayer);
    }
    
    /**
     * 
     * Updates a layer of images in the grid to show to the user
     * 
     * @param layer Index of the layer to update
     * @param tiles New images to set the layer tiles to
     */
    public void updateLayer(int layer, BufferedImage[][] tiles) {
        gridPanel.getLayer(layer).setTiles(tiles);
    }
    
    /**
     * 
     * Displays the map to the user on the created window.
     * 
     * @param chars Character array to display
     * @param useImages Whether or not to use the image tiles
     */
    public void displayMap(char[][] chars, boolean useImages) {
        gridPanel.setUseImages(useImages);
        gridPanel.setCharTiles(chars);
        
        frame.revalidate();
        frame.repaint();
    }
    
    /**
     * 
     * Displays the character array as a grid on the window.
     * 
     * @param chars Character array to display on the map
     */
    public void displayMap(char[][] chars) {
        displayMap(chars, false);
    }
}
