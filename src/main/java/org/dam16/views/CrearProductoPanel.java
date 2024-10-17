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

import static org.dam16.controllers.CrearProductoControllerPanel.CHANGE_TO_JLNOTSELECTED;

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
    public JList l_autoreselected;
    public JList l_autoresnotselected;

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
                   tx_numeroejemplares.setText(tx_id.getText().substring(0,tx_id.getText().length()-1));
               }
           }
        });
        tx_precio.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                    tx_precio.setText(tx_id.getText().substring(0,tx_id.getText().length()-1));
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
    }
    private void loadComboGeneros(ArrayList<GeneroModel> generos) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (GeneroModel genero : generos) {
            modelo.addElement(genero.getGenero());
        }
        cb_generos.setModel(modelo);

    }
    private void loadJListaNotSelected(ArrayList<AutorModel> autorModels) {
        DefaultListModel modelo = new DefaultListModel();
        for (AutorModel autorModel : autorModels) {
            modelo.addElement(autorModel.name);
        }
        jl_autoresnotselected.setModel(modelo);
        l_autoresnotselected = new JList(modelo);
    }
    private void loadJListaSelected(ArrayList<AutorModel> autorModels) {
        DefaultListModel modelo = new DefaultListModel();
        jl_autoreselected.setModel(modelo);
        l_autoresnotselected = new JList(modelo);
    }
    private void setImagePreviewPanel(){
        imagePanel = new ImagePanel();
        imagePreviewPanel.add(imagePanel);
    }
    public ImagePanel getImagePreviewPanel(){
        return imagePanel;
    }
    private void setCommands(){
        bt_autoreselected.setActionCommand(CHANGE_TO_JLNOTSELECTED);
        bt_autoresnotselected.setActionCommand(CHANGE_TO_JLNOTSELECTED);
    }
    public void addListener(ActionListener listener){
        bt_autoresnotselected.addActionListener(listener);
        bt_autoreselected.addActionListener(listener);
    }
    public JList getJlAutoreselected() {
        return jl_autoresnotselected;
    }
    public JList getJl_autoresnotselected() {
        return jl_autoresnotselected;
    }
    public void cleanFields(){
        jl_autoresnotselected.removeAll();
    }
    public LibroModel getLibroModel() {
       return new LibroModel(Integer.valueOf(tx_id.getText()),tx_titulo.getText(),jl_autoreselected.toString(),cb_generos.getSelectedItem().toString(),Double.valueOf(tx_precio.getText()), Date.valueOf(dp_fechapublicacion.getDate()),Integer.valueOf(tx_numeroejemplares.getText()),ck_stock.isSelected());
    }
}
