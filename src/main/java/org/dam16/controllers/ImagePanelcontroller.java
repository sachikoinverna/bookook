package org.dam16.controllers;

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
    private void handlerSetDefaultImage(){
        try {
            String imagen = URLDecoder.decode(getClass().getResource("/default.jpg").getPath(),"UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}