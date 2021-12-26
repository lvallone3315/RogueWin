/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dylan Johnson
 */
class MapGridPanel extends JPanel {

    int gridSize;

    ArrayList<ImageGridLayer> layers;

    JPanel characterPanel;
    JLabel[][] charTiles;
    boolean useImages;

    public MapGridPanel(int gridSize, boolean useImages, int width, int height) {

        if (gridSize <= 0) {
            gridSize = 1;
        }

        this.gridSize = gridSize;
        this.useImages = useImages;

        characterPanel = new JPanel();
        characterPanel.setSize(width, height);
        characterPanel.setLayout(new GridLayout(gridSize, gridSize));

        charTiles = new JLabel[gridSize][gridSize];

        int w = width / gridSize;
        int h = height / gridSize;
        Dimension tileSize = new Dimension(w, h);
        Font tileFont = createFont(tileSize);

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                JLabel newLabel = new JLabel(" ");
                newLabel.setVerticalAlignment(JLabel.CENTER);
                newLabel.setHorizontalAlignment(JLabel.CENTER);
                newLabel.setFont(tileFont);
                charTiles[r][c] = newLabel;
                characterPanel.add(newLabel);
            }
        }

        add(characterPanel);
        characterPanel.setVisible(!useImages);

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
            System.out.println("Invalid layer index: " + index);
            return null;
        }

        return layers.get(index);
    }

    public void setCharTiles(char[][] newTiles) {
        if (newTiles.length != gridSize || newTiles[0].length != gridSize) {
            System.out.println("Invalid newTiles char array");
            return;
        }

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                charTiles[r][c].setText(String.valueOf(newTiles[r][c]));
            }
        }
    }

    public void setUseImages(boolean useImages) {
        this.useImages = useImages;
        characterPanel.setVisible(!useImages);
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
        Font tileFont = createFont(tileSize);

        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                charTiles[r][c].setPreferredSize(tileSize);
                charTiles[r][c].setSize(tileSize);
                charTiles[r][c].setFont(tileFont);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth() / gridSize;
        int h = getHeight() / gridSize;

        if (useImages) {
            for (ImageGridLayer layer : layers) {
                for (int r = 0; r < gridSize; r++) {
                    for (int c = 0; c < gridSize; c++) {
                        BufferedImage image = layer.getTile(r, c);

                        g.drawImage(image, c * w, r * h, w, h, this);
                    }
                }
            }
        }
    }
}
