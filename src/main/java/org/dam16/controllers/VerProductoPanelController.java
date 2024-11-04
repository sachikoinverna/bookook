package org.dam16.controllers;

import org.dam16.models.LibroModel;
import org.dam16.views.VerProductosPanel;
import org.dam16.xml.XMLManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            if(verProductosPanel.getDp_fechaDesde().getDate()!=null) {
                ArrayList<LibroModel> libros = XMLManager.getLibrosByFechaPublicacion(Date.valueOf(verProductosPanel.getDp_fechaDesde().getDate()),Date.valueOf(verProductosPanel.getDp_fechaHasta().getDate()));
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
        if(verProductosPanel.gettx_idLibro().isFocusOwner() && !verProductosPanel.gettx_idLibro().getText().isEmpty()){
            if(!Character.isDigit(e.getKeyChar())){
                e.consume();
            }
            try {
                LibroModel libro = XMLManager.getLibroById(Integer.parseInt(verProductosPanel.gettx_idLibro().getText().toString()));
                if (libro != null) {
                    ArrayList<LibroModel> libroModels = new ArrayList<>();
                    libroModels.add(libro);
                    verProductosPanel.setLibroPanel(libroModels);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
