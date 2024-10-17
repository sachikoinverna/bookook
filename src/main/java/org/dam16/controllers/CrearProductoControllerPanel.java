package org.dam16.controllers;

import org.dam16.views.CrearProductoPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearProductoControllerPanel implements ActionListener {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    private final CrearProductoPanel crearProductoPanel;
    public CrearProductoControllerPanel(CrearProductoPanel crearProductoPanel) {
        this.crearProductoPanel = crearProductoPanel;
    }
    private void handlerCreateProduct() {

    }
    private void handlerEditProduct() {}
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case CREATE_PRODUCT:
                handlerCreateProduct();
                break;
                case EDIT_PRODUCT:
                    handlerEditProduct();
                    break;
        }
    }
}
