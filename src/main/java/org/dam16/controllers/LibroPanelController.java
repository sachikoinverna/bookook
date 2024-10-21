package org.dam16.controllers;

import org.dam16.views.EditarProductoDialog;
import org.dam16.views.LibroPanel;
import org.dam16.views.MainFrame;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class LibroPanelController  implements ActionListener {
    private LibroPanel libroPanel;
    private MainFrame mainFrame;
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String DELETE_PRODUCT = "DELETE_PRODUCT";
    public LibroPanelController(LibroPanel libroPanel, MainFrame mainFrame) {
        this.libroPanel = libroPanel;
        this.mainFrame = mainFrame;
    }
    private void handlerEditProduct(){
        EditarProductoDialog editarProductoDialog = new EditarProductoDialog(mainFrame, libroPanel.getLibro());
        editarProductoDialog.setSize(1150,680);
        editarProductoDialog.setVisible(true);
    }
    private void handlerDeleteProduct(){
        boolean eliminado = XMLManager.deleteLibroById(libroPanel.getLibro().getId());
        if(eliminado){
            JOptionPane.showMessageDialog(null, "El libro se elimino");
            try {
                if(XMLManager.getAllLibros()!=null) {
                    mainFrame.getVerProductosPanel().setLibroPanel(XMLManager.getAllLibros());
                }
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else if (!eliminado) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar el libro");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case EDIT_PRODUCT:
                handlerEditProduct();
                break;
                case DELETE_PRODUCT:
                    handlerDeleteProduct();
                    break;
        }
    }
}
