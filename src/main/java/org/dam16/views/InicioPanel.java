package org.dam16.views;

import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {
    private JPanel mainPanel;
    private Image backgroundImage;
    public InicioPanel() {
        add(mainPanel);
        mainPanel.setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0,getWidth(),getHeight(), this);
        }
    }
    public void setBackgroundImage(String path) {
        backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
        repaint();
    }
}
