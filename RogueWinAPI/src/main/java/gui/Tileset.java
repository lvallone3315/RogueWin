/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * Creates an array of tiles based off of an image passed
 * to the object. Slices the image into a grid of tiles for
 * the tileset
 * 
 * @author Dylan Johnson
 */
public class Tileset {
    
    int rows, cols;
    private BufferedImage[][] tiles;
    
    /**
     * 
     * @param filePath Path to the image for the tilemap
     * @param rows Number of rows in the tilemap
     * @param cols Number of columns in the tilemap
     */
    public Tileset(String filePath, int rows, int cols) {
        if(rows < 0)
            rows = 1;
        
        if(cols < 0)
            cols = 1;
        
        this.rows = rows;
        this.cols = cols;
        
        tiles = new BufferedImage[rows][cols];
        
        try {
            BufferedImage tileMap = ImageIO.read(new File(filePath));
            
            int w = tileMap.getWidth() / cols;
            int h = tileMap.getHeight() / rows;
            
            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < cols; c++) {
                    tiles[r][c] = tileMap.getSubimage(c * w, r * h, w, h);
                }
            }
        }
        catch(IOException ex) {
            Logger.getLogger(Tileset.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Image File \"" + filePath + "\" Does not Exist");
        }
        
    }

    /**
     * 
     * @return Number of rows in the tileset
     */
    public int getRows() {
        return rows;
    }

    /**
     * 
     * @return Number of columns in the tileset
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * 
     * @param r The row of the tile that is being requested
     * @param c The column of the tile that is being requested
     * @return The requested tile at (r,c). Returns null if invalid coordinate
     */
    public BufferedImage getTile(int r, int c) {
        if(r < 0 || r >= rows || c < 0 || c >= cols) {
            System.out.println("Tile out of Bounds: (" + r + ", " + c + ")");
            return null;
        }
        
        return tiles[r][c];
    }
    
    
}
