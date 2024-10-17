package org.dam16.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.dam16.controllers.ImagePanelcontroller.DELETE_IMAGE;
import static org.dam16.controllers.ImagePanelcontroller.SELECT_IMAGE;

public class ImagePanel extends JPanel {
    private JPanel mainPanel;
    private JLabel tx_image;
    private JButton bt_elegirImagen;
    private JButton bt_eliminarImagen;
    private Image image;
    public ImagePanel() {
        add(mainPanel);
    }
    private void setCommands(){
        bt_elegirImagen.setActionCommand(SELECT_IMAGE);
        bt_eliminarImagen.setActionCommand(DELETE_IMAGE);
    }
    public void addListener(ActionListener listener){
        bt_elegirImagen.addActionListener(listener);
        bt_eliminarImagen.addActionListener(listener);
    }
}
