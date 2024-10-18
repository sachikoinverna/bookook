package org.dam16.controllers;

import org.dam16.views.LibroPanel;
import org.dam16.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibroPanelController  implements ActionListener {
    private LibroPanel libroPanel;
    private MainFrame mainFrame;
    private static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    private static final String DELETE_PRDDUCT = "DELETE_PRDDUCT";
    public LibroPanelController(LibroPanel libroPanel, MainFrame mainFrame) {
        this.libroPanel = libroPanel;
        this.mainFrame = mainFrame;
    }
    private void handlerEditProduct(){

    }
    private void handlerDeleteProduct(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case EDIT_PRODUCT:
                handlerEditProduct();
                break;
                case DELETE_PRDDUCT:
                    handlerDeleteProduct();
                    break;
        }
    }
}
