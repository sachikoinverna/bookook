package org.dam16.controllers;

import org.dam16.models.LibroModel;
import org.dam16.views.VerProductosPanel;
import org.dam16.xml.XMLManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerProductoPanelController implements ActionListener {
    private final VerProductosPanel verProductosPanel;
    public VerProductoPanelController(VerProductosPanel verProductosPanel) {
        this.verProductosPanel = verProductosPanel;
        handlerCargarLibros();
    }
    private void handlerCargarLibros(){
        try {
            ArrayList<LibroModel> libros = XMLManager.getAllLibros();
            if (libros != null) {
                verProductosPanel.setLibroPanel(libros);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
