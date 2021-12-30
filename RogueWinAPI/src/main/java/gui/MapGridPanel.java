/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Dylan Johnson
 */
class MapGridPanel extends JPanel {

    int gridSize;

    ArrayList<ImageGridLayer> layers;

    char[][] charTiles;
    boolean useImages;
    Font tileFont;

    public MapGridPanel(int gridSize, boolean useImages, int width, int height) {

        if (gridSize <= 0) {
            gridSize = 1;
        }

        this.gridSize = gridSize;
        this.useImages = useImages;

        charTiles = new char[gridSize][gridSize];

        int w = width / gridSize;
        int h = height / gridSize;
        Dimension tileSize = new Dimension(w, h);
        this.tileFont = createFont(tileSize);

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                charTiles[r][c] = ' ';
            }
        }

        setSize(width, height);

        layers = new ArrayList<>();
        addLayer();
    }

    public int numLayers() {
        return layers.size();
    }

    public ImageGridLayer addLayer() {
        ImageGridLayer newLayer = new ImageGridLayer(gridSize);
        layers.add(newLayer);

        return newLayer;
    }

    public ImageGridLayer addLayer(ImageGridLayer newLayer) {
        layers.add(newLayer);
        return newLayer;
    }

    public ImageGridLayer getLayer(int index) {
        if (index < 0 || index >= layers.size()) {
            throw new IndexOutOfBoundsException(index);
        }

        return layers.get(index);
    }

    public void setCharTiles(char[][] newTiles) {
        if (newTiles.length != gridSize || newTiles[0].length != gridSize) {
            throw new IllegalGridSizeException(newTiles);
        }

        charTiles = newTiles.clone();
    }

    public void setUseImages(boolean useImages) {
        this.useImages = useImages;
    }

    private Font createFont(Dimension tileSize) {

        int min = Math.min(tileSize.height, tileSize.width);

        return new Font("Monospaced", Font.PLAIN, min / 2);
    }

    @Override
    public void setSize(Dimension d) {
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        int w = width / gridSize;
        int h = height / gridSize;

        Dimension tileSize = new Dimension(w, h);
        this.tileFont = createFont(tileSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        float tileWidth = getWidth() / (float) gridSize;
        float tileHeight = getHeight() / (float) gridSize;

        if (useImages) {
            paintImages(g, tileWidth, tileHeight);
        } else {
            paintCharacters(g, tileWidth, tileHeight);
        }
    }

    private void paintImages(Graphics g, float tileWidth, float tileHeight) {
        for (ImageGridLayer layer : layers) {
            for (int r = 0; r < gridSize; r++) {
                for (int c = 0; c < gridSize; c++) {
                    BufferedImage image = layer.getTile(r, c);

                    g.drawImage(image, (int) (c * tileWidth), (int) (r * tileHeight), (int) tileWidth, (int) tileHeight, this);
                }
            }
        }
    }

    private void paintCharacters(Graphics g, float tileWidth, float tileHeight) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g.setFont(tileFont);

        FontMetrics metrics = g.getFontMetrics();
        float halfTileHeight = tileHeight / 2.0f;
        float halfTileWidth = tileWidth / 2.0f;

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                String text = String.valueOf(charTiles[r][c]);
                Rectangle2D rect = metrics.getStringBounds(text, g);

                float halfWidth = (float) (rect.getWidth() / 2);
                float halfHeight = (float) (rect.getHeight() / 2);

                int x = (int) (c * tileWidth + halfTileWidth - halfWidth);
                int y = (int) (r * tileHeight + halfTileHeight + halfHeight);

                g.drawString(text, x, y);
            }
        }
    }
}
