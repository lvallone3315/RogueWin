/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.image.BufferedImage;

/**
 *
 * @author Dylan Johnson
 */
class ImageGridLayer {

    int gridSize;
    BufferedImage[][] tiles;

    public ImageGridLayer(int gridSize) {
        if (gridSize <= 0) {
            gridSize = 1;
        }

        this.gridSize = gridSize;
        tiles = new BufferedImage[gridSize][gridSize];
    }

    public BufferedImage getTile(int r, int c) {
        if (!isValidCoord(r, c)) {
            throw new CoordinateOutOfBoundsException(r, c);
        }

        return tiles[r][c];
    }

    public void setTile(int r, int c, BufferedImage image) {
        if (!isValidCoord(r, c)) {
            throw new CoordinateOutOfBoundsException(r, c);
        }

        tiles[r][c] = image;
    }

    public void setTiles(BufferedImage[][] newTiles) {
        if (newTiles.length != gridSize || newTiles[0].length != gridSize) {
            throw new IllegalGridSizeException(newTiles);
        }

        tiles = newTiles.clone();
    }

    private boolean isValidCoord(int r, int c) {
        return r >= 0 && r < gridSize && c >= 0 && c < gridSize;
    }
}
