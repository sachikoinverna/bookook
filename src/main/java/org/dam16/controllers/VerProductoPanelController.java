package org.dam16.controllers;

import org.dam16.models.LibroModel;
import org.dam16.views.VerProductosPanel;
import org.dam16.xml.XMLManager;

import java.awt.event.*;
import java.util.ArrayList;
import java.sql.Date;

public class VerProductoPanelController implements ActionListener, KeyListener {
    private final VerProductosPanel verProductosPanel;
    public static final String FILTRAR_FECHA = "FILTRAR_FECHA";
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
    private void handlerFiltrarFecha(){
        try {
            if(verProductosPanel.getDp_fechaDesde()!=null && verProductosPanel.getDp_fechaHasta()!=null) {
                ArrayList<LibroModel> libros = XMLManager.getLibrosByFechaPublicacion(Date.valueOf(verProductosPanel.getDp_fechaDesde().getDate()),Date.valueOf(verProductosPanel.getDp_fechaHasta().getDate()));
                if (libros != null) {
                    verProductosPanel.setLibroPanel(libros);
                }
            } else if (verProductosPanel.getDp_fechaDesde()!=null && verProductosPanel.getDp_fechaHasta()==null) {
                ArrayList<LibroModel> libros = XMLManager.getLibrosByFechaPublicacion(Date.valueOf(verProductosPanel.getDp_fechaDesde().getDate()),null);
                if (libros != null) {
                    verProductosPanel.setLibroPanel(libros);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case FILTRAR_FECHA:
                handlerFiltrarFecha();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /*if(verProductosPanel.gettx_idLibro().isFocusOwner() && !verProductosPanel.gettx_idLibro().getText().isEmpty()){
            if(!Character.isDigit(e.getKeyChar())){
                e.consume();
            }*/

        //}
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
            try {
                ArrayList<LibroModel> libros = XMLManager.getLibrosByWords(verProductosPanel.gettx_idLibro().getText());
                if (libros != null) {
                    verProductosPanel.setLibroPanel(libros);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
        }
    }
    private boolean checkNumbers(String cadena) {
        char[] ch = cadena.toCharArray();
        for (char c : ch) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
