/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

/**
 *
 * @author Dylan Johnson
 */
class RogueWinFrameAdapter extends ComponentAdapter {
    
    MapGridPanel gridPanel;
    
    public RogueWinFrameAdapter(MapGridPanel gridPanel) {
        super();
        
        this.gridPanel = gridPanel;
    }
    
    public MapGridPanel getGridPanel() {
        return gridPanel;
    }
    
    public void setGridPanel(MapGridPanel gridPanel) {
        this.gridPanel = gridPanel;
    }
    
    @Override
    public void componentResized(ComponentEvent evt) {
        JFrame frame = (JFrame)evt.getSource();
        
        if(frame == null)
            return;
        
        gridPanel.setSize(frame.getContentPane().getSize());
    }
}
