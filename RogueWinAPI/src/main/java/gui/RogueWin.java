/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * Class to manage all gui elements for a roguelike game, can be used to display
 * either a character grid representation of a map or a grid of image tiles for
 * a visual representation of the map.
 * <br>
 * <h2> Creating a RogueWin window </h2>
 * RogueWin {windowName} = new RogueWin({title}, {screen width in px}, {screen
 * height in px}, {gridSize in characters});
 * <br>
 * char[][] grid = new char[gridSize][gridSize];
 * <br>
 * {Initialize grid array of characters}
 * <br>
 * {windowName}.displayMap(grid);
 *
 * @author Dylan Johnson
 */
public class RogueWin {

    /**
     * Default gridSize for the map: {@value DEFAULT_GRIDSIZE}
     */
    public static final int DEFAULT_GRIDSIZE = 10;
    /**
     * Default width for the window: {@value DEFAULT_WIN_WIDTH}px
     */
    public static final int DEFAULT_WIN_WIDTH = 800;
    /**
     * Default height for the window: {@value DEFAULT_WIN_HEIGHT}px
     */
    public static final int DEFAULT_WIN_HEIGHT = 800;
    /**
     * Default title for the window: {@value DEFAULT_TITLE}
     */
    public static final String DEFAULT_TITLE = "Rogue Map";

    /**
     * Minimum gridSize for the window: {@value MIN_GRIDSIZE}
     */
    public static final int MIN_GRIDSIZE = 5;
    /**
     * Maximum gridSize for the window: {@value MAX_GRIDSIZE}
     */
    public static final int MAX_GRIDSIZE = 30;

    /**
     * Minimum size for the window width and height: {@value MIN_SCREEN_SIZE}px
     */
    public static final int MIN_SCREEN_SIZE = 100;

    /**
     * Maximum length of the title of the window: {@value MAX_TITLE_LENGTH}ch
     */
    public static final int MAX_TITLE_LENGTH = 20;

    private JFrame frame;
    private int gridSize;

    private MapGridPanel gridPanel;
    private RogueWinFrameCompAdapter compAdapter;
    private RogueWinWindowAdapter winAdapter;
    private RogueWinKeyListener keyListener;

    /**
     *
     * Creates the window for the map with the given parameters. Defaults all
     * characters to a single space (Empty) and creates a window that is not
     * resizeable
     *
     * @param title The title of the window to be created - at most
     * {@value MAX_TITLE_LENGTH} characters
     * @param width The width (in px) of the window to be created - must be at
     * least {@value MIN_SCREEN_SIZE}
     * @param height The height (in px) of the window to be created - must be at
     * least {@value MIN_SCREEN_SIZE}
     * @param gridSize The size of the grid of tiles/characters for the map -
     * always a square grid. Must be in range
     * ({@value MIN_GRIDSIZE}, {@value MAX_GRIDSIZE})
     */
    public RogueWin(String title, int width, int height, int gridSize) {

        gridSize = GUIUtils.clamp(gridSize, MIN_GRIDSIZE, MAX_GRIDSIZE);
        width = Math.max(width, MIN_SCREEN_SIZE);
        height = Math.max(height, MIN_SCREEN_SIZE);
        title = validateTitle(title);

        this.gridSize = gridSize;

        frame = new JFrame();
        frame.pack();
        setContentSize(width, height);

        frame.setTitle(title);

        frame.setVisible(true);

        gridPanel = new MapGridPanel(gridSize, false, width, height);
        frame.add(gridPanel);

        compAdapter = new RogueWinFrameCompAdapter(gridPanel);
        winAdapter = new RogueWinWindowAdapter();
        keyListener = new RogueWinKeyListener();

        frame.addComponentListener(compAdapter);
        frame.addWindowListener(winAdapter);
        frame.addKeyListener(keyListener);
    }

    /**
     * Creates the window for the map with the title {@value DEFAULT_TITLE},
     * width of {@value DEFAULT_WIN_WIDTH}px, height of
     * {@value DEFAULT_WIN_HEIGHT}px, and a grid size of
     * {@value DEFAULT_GRIDSIZE}
     */
    public RogueWin() {
        this(DEFAULT_TITLE, DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT, DEFAULT_GRIDSIZE);
    }

    /**
     *
     * @return The title of the window
     */
    public String getTitle() {
        return frame.getTitle();
    }

    /**
     * Updates the title of the window displayed to the user
     *
     * @param title New title for the window
     */
    public void setTitle(String title) {
        frame.setTitle(validateTitle(title));
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
     * @return The height of the window in pixels excluding the insets - only
     * the content pane
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
     * @return Grid size of the window - how many tiles are in each row and
     * column
     */
    public int getGridSize() {
        return gridSize;
    }

    /**
     *
     * Updates the gridSize of the map.
     *
     * <p>
     * NOTICE: This will clear the map, it is recommended to display a new map
     * immediately after setting the gridSize
     *
     * @param gridSize New grid size for the map - number of rows and columns.
     * Must be between {@value MIN_GRIDSIZE} and {@value MAX_GRIDSIZE}
     */
    public void setGridSize(int gridSize) {
        gridSize = GUIUtils.clamp(gridSize, MIN_GRIDSIZE, MAX_GRIDSIZE);
        this.gridSize = gridSize;

        frame.remove(gridPanel);
        gridPanel = new MapGridPanel(gridSize, false, getWidth(), getHeight());
        compAdapter.setGridPanel(gridPanel);
        frame.add(gridPanel);
    }

    /**
     *
     * Adds a new layer to the grid on top of the highest layer with the given
     * images as the tiles of the grid
     *
     * @param tiles Images to set the new layer's tiles as
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

    /**
     *
     * Adds a new {@link RogueWinBehavior} instance to this RogueWin object.
     * Check {@link RogueWinBehavior} documentation for more details on how it
     * works numerous behaviors can be added to any one {@link RogueWin} object
     * for different desired logic
     *
     * @param b Behavior class to be called for different events on this
     * instance of {@link RogueWin}
     */
    public void addBehavior(RogueWinBehavior b) {
        keyListener.addBehavior(b);
    }

    /**
     *
     * @param title The title to be validated and returned
     * @return Validated title truncated to {@value MAX_TITLE_LENGTH} if
     * necessary or {@value DEFAULT_TITLE} if title is null
     */
    private String validateTitle(String title) {
        if (title == null) {
            return DEFAULT_TITLE;
        }

        if (title.length() > MAX_TITLE_LENGTH) {
            return title.substring(0, MAX_TITLE_LENGTH);
        }

        return title;
    }
}
