package org.dam16.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.dam16.controllers.ImagePanelcontroller.DELETE_IMAGE;
import static org.dam16.controllers.ImagePanelcontroller.SELECT_IMAGE;

public class ImagePanel extends JPanel {
    private JPanel mainPanel;
    private JLabel lb_image;
    private JButton bt_elegirImagen;
    private JButton bt_eliminarImagen;
    private Image image;
    private String setSelectedImage;
    public ImagePanel() {
        add(mainPanel);
        setCommands();
        mainPanel.setOpaque(false);
    }
    public String getSetSelectedImage() {
        return setSelectedImage;
    }
    public void setSelectedImage(String setSelectedImage) {
        this.setSelectedImage = setSelectedImage;
    }
    /*public void setBackgroundImage(String image){
        ImageIcon icon = new ImageIcon(image);
        Image imagenEscalada = icon.getImage().getScaledInstance(209, 209, Image.SCALE_SMOOTH);
        lb_image.setIcon(new ImageIcon(imagenEscalada));
    }*/
    public void setBackgroundImage(String image) {
        if(image.equals("default")){
            setDefaultImage();
            return;
        }
        ImageIcon icon = new ImageIcon(image);
        Image imagenEscalada = icon.getImage().getScaledInstance(209, 209, Image.SCALE_SMOOTH);
        lb_image.setIcon(new ImageIcon(imagenEscalada));
    }
    /*public void setDefaultImage(){
        setSelectedImage("src/images/default.jpg");
        setBackgroundImage(getSetSelectedImage());
    }*/
    public void setDefaultImage() {
        Image imagenEscalada = new ImageIcon(getClass().getResource("/default.jpg"))
                .getImage().getScaledInstance(162, 162, Image.SCALE_SMOOTH);
        image = imagenEscalada;
        lb_image.setIcon(new ImageIcon(imagenEscalada));
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
