package org.dam16.controllers;

import org.dam16.views.EditarProductoDialog;
import org.dam16.views.LibroPanel;
import org.dam16.views.MainFrame;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

public class LibroPanelController  implements ActionListener, MouseListener {
    private LibroPanel libroPanel;
    private MainFrame mainFrame;
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String DELETE_PRODUCT = "DELETE_PRODUCT";
    public LibroPanelController(LibroPanel libroPanel, MainFrame mainFrame) {
        this.libroPanel = libroPanel;
        this.mainFrame = mainFrame;
    }
    private void handlerClicked(){
        int option = JOptionPane.showOptionDialog(libroPanel,"¿Que deseas hacer?","Libro",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Editar","Borrar"},"Editar");
        if(option == 0) {
            EditarProductoDialog editarProductoDialog = new EditarProductoDialog(mainFrame, libroPanel.getLibro(), true);
            editarProductoDialog.setSize(1200, 720);
            editarProductoDialog.showWindow();
        } else if (option == 1) {
            int option2 = JOptionPane.showOptionDialog(libroPanel,"¿Realmente desea eliminar el producto?","Atencion",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,new ImageIcon("deleteicon.jpg"),new Object[]{"Cancelar","Aceptar"},"Cancelar");
            if(option2 == 0) {
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
            }}
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            handlerClicked();
        }
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
