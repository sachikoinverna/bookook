package org.dam16.controllers;

import org.dam16.views.CrearProductoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearProductoControllerPanel implements ActionListener {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String CHANGE_TO_JLNOTSELECTED = "CHANGE_TO_JLNOTSELECTED";
    public static final String CHANGE_TO_JLNSELECTED = "CHANGE_TO_JLTSELECTED";
    private final CrearProductoPanel crearProductoPanel;
    public CrearProductoControllerPanel(CrearProductoPanel crearProductoPanel) {
        this.crearProductoPanel = crearProductoPanel;
    }
    private void handlerCreateProduct() {

    }
    private void handlerChangeToJLNOTSELECTED() {
        if(!crearProductoPanel.getJlAutoreselected().isSelectionEmpty()){
            for(Object selectedValue:crearProductoPanel.getJlAutoreselected().getSelectedValuesList()){
                //crearProductoPanel.l_autoresnotselected.add(selectedValue);
                //crearProductoPanel.l_autoreselected.remove(selectedValue);
            }
        }
    }
    private void handlerChangeToJLSELECTED() {

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
                    case CHANGE_TO_JLNOTSELECTED:
                        handlerChangeToJLNOTSELECTED();
                        break;
                        case CHANGE_TO_JLNSELECTED:
                            handlerChangeToJLSELECTED();
                            break;
        }
    }
}
