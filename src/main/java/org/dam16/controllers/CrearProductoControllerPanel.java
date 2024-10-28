package org.dam16.controllers;

import org.dam16.models.AutorModel;
import org.dam16.models.LibroModel;
import org.dam16.utils.FileUtils;
import org.dam16.views.CrearProductoPanel;
import org.dam16.views.MainFrame;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

public class CrearProductoControllerPanel implements ActionListener, FocusListener, ChangeListener {
    public static final String CREATE_PRODUCT = "CREATE_PRODUCT";
    public static final String EDIT_PRODUCT = "EDIT_PRODUCT";
    public static final String CHANGE_TO_JLNOTSELECTED = "CHANGE_TO_JLNOTSELECTED";
    public static final String CHANGE_TO_JLSELECTED = "CHANGE_TO_JLSELECTED";
    public static final String DELETE_SELECTION = "DELETE_SELECTION";
    public static final String SELECT_ALL = "SELECT_ALL";
    public static final String CANCELAR_CREATE = "CANCELAR_CREATE";
    public static final String CANCELAR_EDIT = "CANCELAR_EDIT";
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
    private void handlerEditProduct() throws ParseException {
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
                    mainFrame.getVerProductosPanel().setLibroPanel(XMLManager.getAllLibros());
                    mainFrame.closeWindow();
                    mainFrame.showWindow();
                    mainFrame.getVerProductosPanel().revalidate();
                    mainFrame.getVerProductosPanel().repaint();
                }
        }
    }
    private void handlerCancelCreate(){
        int opcion = JOptionPane.showConfirmDialog(null,"¿Deseas volver a la pantalla inicial?","Atencion",JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            mainFrame.navigate("inicioPanel");
        }
    }
    private void handlerCancelEdit(){
        int opcion = JOptionPane.showConfirmDialog(null,"¿Deseas salir del modo edicion?","Atencion",JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            mainFrame.closeWindow();
            mainFrame.showWindow();
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
                    try {
                        handlerEditProduct();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
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
            case CANCELAR_CREATE:
                handlerCancelCreate();
                break;
            case CANCELAR_EDIT:
                handlerCancelEdit();
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField tx_id = (JTextField) e.getSource();
        if(!tx_id.isFocusOwner()){
            if(!tx_id.getText().isEmpty()) {
                if (XMLManager.getLibroById(Integer.parseInt(tx_id.getText())) == null) {
                    crearProductoPanel.idExistente = false;
                    crearProductoPanel.getTx_id().setBorder(crearProductoPanel.getTx_titulo().getBorder());
                    crearProductoPanel.getCb_generos().setEnabled(true);}
                else if (XMLManager.getLibroById(Integer.parseInt(tx_id.getText())) != null) {
                    crearProductoPanel.idExistente = true;
                    crearProductoPanel.setIdIncorrect();
                    crearProductoPanel.getCb_generos().setEnabled(false);
                }
                }
            }
        }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
