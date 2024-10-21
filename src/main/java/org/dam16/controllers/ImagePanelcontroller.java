package org.dam16.controllers;

import org.dam16.utils.FileUtils;
import org.dam16.views.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ImagePanelcontroller implements ActionListener {
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
        }
    }
    private void handlerSetDefaultImage(){
            imagePanel.setSelectedImage("src/images/default.jpg");
            imagePanel.setBackgroundImage(imagePanel.getSetSelectedImage());
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
                break;
                case SELECT_IMAGE:
                    handlerSelectImage();
                    break;
        }
    }
}
