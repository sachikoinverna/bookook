package org.dam16.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;
import org.dam16.models.LibroModel;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;

import static org.dam16.controllers.CrearProductoControllerPanel.*;

public class CrearProductoPanel extends JPanel {
    private JPanel mainPanel;
    private JCheckBox ck_stock;
    private JTextField tx_id;
    private ImagePanel imagePanel;
    private JPanel imagePreviewPanel;
    private JTextField tx_titulo;
    private DatePicker dp_fechapublicacion;
    private JTextField tx_precio;
    private JTextField tx_numeroejemplares;
    private JComboBox cb_generos;
    private JList jl_autoresnotselected;
    private JList jl_autoreselected;
    private JButton bt_autoresnotselected;
    private JButton bt_autoreselected;
    private JButton bt_eliminarseleccion;
    private JButton bt_seleccionartodos;
    private JButton bt_crearEditar;
    private String errorMessage;

    public CrearProductoPanel() {
        add(mainPanel);
        mainPanel.setOpaque(false);
        setImagePreviewPanel();
        setCommands();
        tx_id.addKeyListener(new KeyAdapter() {
                                 @Override
                                 public void keyReleased(KeyEvent e) {
                                     if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                                         tx_id.setText(tx_id.getText().substring(0,tx_id.getText().length()-1));
                                     }
                                 }
                             }
        );
        tx_numeroejemplares.addKeyListener(new KeyAdapter() {
           public void keyReleased(KeyEvent e) {
               if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                   tx_numeroejemplares.setText(tx_numeroejemplares.getText().substring(0,tx_numeroejemplares.getText().length()-1));
               }
           }
        });
        tx_precio.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                    tx_precio.setText(tx_precio.getText().substring(0,tx_precio.getText().length()-1));
                }
            }
        });
        dp_fechapublicacion.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                    dp_fechapublicacion.setText(dp_fechapublicacion.getText().substring(0,dp_fechapublicacion.getText().length()-1));
                }
            }
        });
        try {
            loadComboGeneros(XMLManager.getAllGeneros());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            loadJListaNotSelected(XMLManager.getAllAutores());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loadJListaSelected(new ArrayList<>());
    }
    private void loadComboGeneros(ArrayList<GeneroModel> generos) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (GeneroModel genero : generos) {
            modelo.addElement(genero);
        }
        cb_generos.setModel(modelo);

    }
    private void loadJListaNotSelected(ArrayList<AutorModel> autorModels) {
        DefaultListModel modelo = new DefaultListModel();
        for (AutorModel autorModel : autorModels) {
            modelo.addElement(autorModel);
        }
        setAutoresNotSelectedModel(modelo);
    }
    private void loadJListaSelected(ArrayList<AutorModel> autorModels) {
        DefaultListModel modelo = new DefaultListModel();
        setAutoresSelectedModel(modelo);
    }
    private void setImagePreviewPanel(){
        imagePanel = new ImagePanel();
        imagePreviewPanel.add(imagePanel);
    }
    public ImagePanel getImagePreviewPanel(){
        return imagePanel;
    }
    private void setCommands(){
        bt_autoreselected.setActionCommand(CHANGE_TO_JLSELECTED);
        bt_autoresnotselected.setActionCommand(CHANGE_TO_JLNOTSELECTED);
        bt_eliminarseleccion.setActionCommand(DELETE_SELECTION);
        bt_seleccionartodos.setActionCommand(SELECT_ALL);
        bt_crearEditar.setActionCommand(CREATE_PRODUCT);
    }

    public void addListener(ActionListener listener){
        bt_autoresnotselected.addActionListener(listener);
        bt_autoreselected.addActionListener(listener);
        bt_eliminarseleccion.addActionListener(listener);
        bt_seleccionartodos.addActionListener(listener);
        bt_crearEditar.addActionListener(listener);
    }
    public void setAutoresNotSelectedModel(DefaultListModel modelo){
        jl_autoresnotselected.setModel(modelo);
    }
    public DefaultListModel getAutoresNotselected(){
        return (DefaultListModel) jl_autoresnotselected.getModel();
    }
    public void setAutoresSelectedModel(DefaultListModel modelo){
        jl_autoreselected.setModel(modelo);
    }
    public DefaultListModel getAutoresSelected(){
        return (DefaultListModel) jl_autoreselected.getModel();
    }
    public JList getJlAutoreselected() {
        return jl_autoreselected;
    }
    public JList getJl_autoresnotselected() {
        return jl_autoresnotselected;
    }
    public void cleanFields(){
        if(!getAutoresSelected().isEmpty()){
            int length = getAutoresSelected().size();
            String [] values = new String[length];
            for(int i = 0; i < length; i++){
                values[i] = getAutoresSelected().get(i).toString();
                getAutoresNotselected().addElement(values[i]);
            }
            getAutoresSelected().removeAllElements();
        }
        tx_id.setText("");
    }
    private boolean checkFields(){
        errorMessage="Faltan los siguientes datos: \n";
        if(tx_id.getText().isEmpty()){
            errorMessage+= "✯Id \n";
            return false;
        }
        if (tx_titulo.getText().isEmpty()) {
           errorMessage+= "✯Titulo \n";
        }
        if (tx_precio.getText().isEmpty()) {
            errorMessage+= "✯Precio \n";
        }else if (tx_numeroejemplares.getText().isEmpty()) {
            errorMessage+="✯Numero de ejemplares \n";
        } else if (getAutoresSelected().isEmpty()) {
            errorMessage+="✯Autores seleccionados \n";
        } else if (dp_fechapublicacion.getDate()!=null) {
            errorMessage+="✯Fecha de publicacion \n";
        }
        return true;
    }
    private ArrayList<AutorModel> getSelectedValues(){
        if(!getAutoresSelected().isEmpty()) {
            ArrayList<AutorModel> valuesA = new ArrayList<>();
            int length = getAutoresSelected().size();
            for (int i = 0; i < length; i++) {
                valuesA.add((AutorModel) getAutoresSelected().getElementAt(i));
            }
            return valuesA;
        }
        return null;
    }
    public LibroModel getLibroModel() {
        if(!checkFields()){
            JOptionPane.showMessageDialog(mainPanel,errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(checkFields()) {
            return new LibroModel(Integer.valueOf(tx_id.getText()), tx_titulo.getText(), new ArrayList<AutorModel>(getSelectedValues()), (GeneroModel) cb_generos.getSelectedItem(), Double.valueOf(tx_precio.getText()), Date.valueOf(dp_fechapublicacion.getDate()), Integer.valueOf(tx_numeroejemplares.getText()), ck_stock.isSelected());
        }
        return null;
    }
}
