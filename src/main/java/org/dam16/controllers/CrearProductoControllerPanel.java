package org.dam16.controllers;

import org.dam16.models.AutorModel;
import org.dam16.models.LibroModel;
import org.dam16.utils.FileUtils;
import org.dam16.views.CrearProductoPanel;
import org.dam16.views.MainFrame;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CrearProductoControllerPanel implements ActionListener {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String CHANGE_TO_JLNOTSELECTED = "CHANGE_TO_JLNOTSELECTED";
    public static final String CHANGE_TO_JLSELECTED = "CHANGE_TO_JLSELECTED";
    public static final String DELETE_SELECTION = "DELETE_SELECTION";
    public static final String SELECT_ALL = "SELECT_ALL";
    private final CrearProductoPanel crearProductoPanel;
    private final MainFrame mainFrame;
    public CrearProductoControllerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.crearProductoPanel = mainFrame.getCrearProductoPanel();
    }
    private void handlerCreateProduct() {
        if (crearProductoPanel.getLibroModel() != null) {
            if (XMLManager.getLibroById(crearProductoPanel.getLibroModel().getId()) == null) {
                if (!mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage().equals("src/images/default.jpg")) {
                    FileUtils.guardarImagen(mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage());
                }
                LibroModel libro = crearProductoPanel.getLibroModel();
                libro.setImagen(mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage());
                boolean okCrear = XMLManager.createLibro(libro);
                if (okCrear) {
                    JOptionPane.showMessageDialog(null, "Felicidades");
                }
            }

        }
    }
    private void handlerDeleteSelection(){
        if(!crearProductoPanel.getAutoresSelected().isEmpty()){
            int length = crearProductoPanel.getAutoresSelected().size();
            ArrayList<AutorModel> autorModels = new ArrayList<>();
            for(int i = 0; i < length; i++){
                autorModels.add((AutorModel) crearProductoPanel.getAutoresSelected().getElementAt(i));
            }
            for(AutorModel autorModel : autorModels){
                crearProductoPanel.getAutoresNotselected().addElement(autorModel);
            }
            crearProductoPanel.getAutoresSelected().removeAllElements();
        }
    }
    private void handlerAddAll(){
        if(!crearProductoPanel.getAutoresNotselected().isEmpty()){
            int length = crearProductoPanel.getAutoresNotselected().size();
            ArrayList<AutorModel> autorModels = new ArrayList<>();
            for(int i = 0; i < length; i++){
                autorModels.add((AutorModel) crearProductoPanel.getAutoresNotselected().getElementAt(i));
            }
            for(AutorModel autorModel : autorModels){
                crearProductoPanel.getAutoresSelected().addElement(autorModel);
                crearProductoPanel.getAutoresNotselected().removeElement(autorModel);
            }
        }
    }
    private void handlerChangeToJLNOTSELECTED() {
        if(!crearProductoPanel.getJlAutoreselected().isSelectionEmpty()){
            int length = crearProductoPanel.getJlAutoreselected().getSelectedIndices().length;
            int[] indexValues = new int[length];
            for (int i = 0; i < indexValues.length; i++) {
                indexValues[i] = crearProductoPanel.getJlAutoreselected().getSelectedIndex();
                AutorModel autorModel = (AutorModel) crearProductoPanel.getAutoresSelected().getElementAt(indexValues[i]);
                crearProductoPanel.getAutoresNotselected().addElement(autorModel);
                crearProductoPanel.getAutoresSelected().removeElement(autorModel);
            }
        }
    }
    private void handlerChangeToJLSELECTED() {
        if(!crearProductoPanel.getJl_autoresnotselected().isSelectionEmpty()){
            int length = crearProductoPanel.getJl_autoresnotselected().getSelectedIndices().length;
            int[] indexValues = new int[length];
            for (int i = 0; i < indexValues.length; i++) {
                indexValues[i] = crearProductoPanel.getJl_autoresnotselected().getSelectedIndex();
                AutorModel autorModel = (AutorModel) crearProductoPanel.getAutoresNotselected().getElementAt(indexValues[i]);
                crearProductoPanel.getAutoresSelected().addElement(autorModel);
                crearProductoPanel.getAutoresNotselected().removeElement(autorModel);
            }
        }
    }
    private void handlerEditProduct() {
        if (crearProductoPanel.getLibroModel() != null) {
                if (!mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage().equals("src/images/default.jpg")) {
                    FileUtils.guardarImagen(mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage());
                }
                LibroModel libro = crearProductoPanel.getLibroModel();
                libro.setImagen(mainFrame.getCrearProductoPanel().getImagePreviewPanel().getSetSelectedImage());
                boolean okCrear = XMLManager.editLibro(libro);
                if (okCrear) {
                    JOptionPane.showMessageDialog(null, "Felicidades");
                    mainFrame.getCrearProductoPanel().cleanFields();
                }
        }
    }
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
