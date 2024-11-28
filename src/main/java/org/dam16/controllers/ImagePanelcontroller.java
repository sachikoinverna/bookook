package org.dam16.controllers;

import org.dam16.utils.FileUtils;
import org.dam16.views.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ImagePanelcontroller implements ActionListener, MouseListener {
    public static final String DELETE_IMAGE ="DELETE_IMAGE";
    public static final String SELECT_IMAGE ="SELECT_IMAGE";
    private final ImagePanel imagePanel;
    public ImagePanelcontroller(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
        handlerSetDefaultImage();
    }
    private void handlerSelectImage(){
        String imagen = FileUtils.seleccionarRutaImagen();
        if(imagen != null){
            imagePanel.setBackgroundImage(imagen);
            imagePanel.setSelectedImage(imagen);
            imagePanel.setVisibleEliminar();
        }
    }
    private void handlerSetDefaultImage(){
            imagePanel.setSelectedImage("default");
            imagePanel.setBackgroundImage(imagePanel.getSetSelectedImage());
            imagePanel.setNotVisibleEliminar();
    }
    private void handlerDeleteImage(){
        handlerSetDefaultImage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case DELETE_IMAGE:
                handlerDeleteImage();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        handlerSelectImage();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
