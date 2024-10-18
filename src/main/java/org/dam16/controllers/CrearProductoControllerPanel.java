package org.dam16.controllers;

import org.dam16.views.CrearProductoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrearProductoControllerPanel implements ActionListener {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String CHANGE_TO_JLNOTSELECTED = "CHANGE_TO_JLNOTSELECTED";
    public static final String CHANGE_TO_JLSELECTED = "CHANGE_TO_JLSELECTED";
    public static final String DELETE_SELECTION = "DELETE_SELECTION";
    public static final String SELECT_ALL = "SELECT_ALL";
    private final CrearProductoPanel crearProductoPanel;
    public CrearProductoControllerPanel(CrearProductoPanel crearProductoPanel) {
        this.crearProductoPanel = crearProductoPanel;
    }
    private void handlerCreateProduct() {

    }
    private void handlerDeleteSelection(){
        if(!crearProductoPanel.getAutoresSelected().isEmpty()){

            int length = crearProductoPanel.getAutoresSelected().size();
            String [] values = new String[length];
            for(int i = 0; i < length; i++){
                values[i] = crearProductoPanel.getAutoresSelected().get(i).toString();
                crearProductoPanel.getAutoresNotselected().addElement(values[i]);
            }
            crearProductoPanel.getAutoresSelected().removeAllElements();
        }
    }
    private void handlerAddAll(){
        if(!crearProductoPanel.getAutoresNotselected().isEmpty()){
            int length = crearProductoPanel.getAutoresNotselected().size();
            String [] values = new String[length];
            for(int i = 0; i < length; i++){
                values[i] = crearProductoPanel.getAutoresNotselected().get(i).toString();
            }
            for(int i = 0; i < length; i++){
                crearProductoPanel.getAutoresSelected().addElement(values[i]);
                crearProductoPanel.getAutoresNotselected().removeElement(values[i]);
            }
        }
    }
    private void handlerChangeToJLNOTSELECTED() {
        if(!crearProductoPanel.getJlAutoreselected().isSelectionEmpty()){
            int length = crearProductoPanel.getJlAutoreselected().getSelectedIndices().length;
            String[] selectedValues = new String[length];
            for (int i = 0; i < selectedValues.length; i++) {
                selectedValues[i] = crearProductoPanel.getJlAutoreselected().getSelectedValue().toString();
                crearProductoPanel.getAutoresNotselected().addElement(selectedValues[i]);
                crearProductoPanel.getAutoresSelected().removeElement(selectedValues[i]);
            }
        }
    }
    private void handlerChangeToJLSELECTED() {
        if(!crearProductoPanel.getJl_autoresnotselected().isSelectionEmpty()){
            int length = crearProductoPanel.getJl_autoresnotselected().getSelectedIndices().length;
            String[] selectedValues = new String[length];
            for (int i = 0; i < selectedValues.length; i++) {
                selectedValues[i] = crearProductoPanel.getJl_autoresnotselected().getSelectedValue().toString();
                crearProductoPanel.getAutoresSelected().addElement(selectedValues[i]);
                crearProductoPanel.getAutoresNotselected().removeElement(selectedValues[i]);
            }
        }
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
                        case CHANGE_TO_JLSELECTED:
                            handlerChangeToJLSELECTED();
                            break;
                            case DELETE_SELECTION:
                                handlerDeleteSelection();
                                break;
                                case SELECT_ALL:
                                    handlerAddAll();
                                    break;
        }
    }
}
