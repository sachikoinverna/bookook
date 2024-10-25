package org.dam16.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;
import org.dam16.models.LibroModel;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
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
    private JButton bt_cancelar;
    private JSlider sl_precio;
    private String errorMessage;
    private Image backgroundImage;
    public boolean idExistente;
    public void again (){
        setImagePreviewPanel();
    }
    public CrearProductoPanel() {
        add(mainPanel);
        mainPanel.setOpaque(false);
        setImagePreviewPanel();
        setCommands();
        tx_id.addKeyListener(new KeyAdapter() {
                                 @Override
                                 public void keyReleased(KeyEvent e) {
                                     if((!Character.isDigit(e.getKeyChar()) || (tx_id.getText().equals("0") && tx_id.getText().length() ==1)) && tx_id.getText().length()>0){
                                         tx_id.setText(tx_id.getText().substring(0,tx_id.getText().length()-1));
                                     }
                                 }
                             }
        );
        tx_numeroejemplares.addKeyListener(new KeyAdapter() {
           public void keyReleased(KeyEvent e) {
               if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())  && tx_numeroejemplares.getText().length()>0){
                   tx_numeroejemplares.setText(tx_numeroejemplares.getText().substring(0,tx_numeroejemplares.getText().length()-1));
               }
           }
        });
        tx_precio.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())  && tx_precio.getText().length()>0){
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
    public void setIdIncorrect(){
        tx_id.setBorder(BorderFactory.createLineBorder(Color.red));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0,getWidth(),getHeight(), this);
        }
    }
    public void setBackgroundImage(String path) {
        backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
        repaint();
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
        if(autorModels!=null){
            for (AutorModel autorModel : autorModels) {
                modelo.addElement(autorModel);
            }
        }
        setAutoresSelectedModel(modelo);
    }
    private void setImagePreviewPanel(){
        imagePanel = new ImagePanel();
        imagePreviewPanel.add(imagePanel);
        imagePanel.setOpaque(false);
        imagePreviewPanel.setOpaque(false);
    }
    public ImagePanel getImagePreviewPanel(){
        return imagePanel;
    }
    private void setCommands(){
        bt_autoreselected.setActionCommand(CHANGE_TO_JLSELECTED);
        bt_autoresnotselected.setActionCommand(CHANGE_TO_JLNOTSELECTED);
        bt_eliminarseleccion.setActionCommand(DELETE_SELECTION);
        bt_seleccionartodos.setActionCommand(SELECT_ALL);
    }
    public void setCreateMode(){
        bt_crearEditar.setActionCommand(CREATE_PRODUCT);
        bt_crearEditar.setText("Crear");
        bt_cancelar.setActionCommand(CANCELAR_CREATE);
        tx_id.setEnabled(true);
        cb_generos.setEnabled(false);
    }
    public void setEditMode(){
        bt_crearEditar.setActionCommand(EDIT_PRODUCT);
        bt_crearEditar.setText("Editar");
        bt_cancelar.setActionCommand(CANCELAR_EDIT);
        tx_id.setEnabled(false);
        cb_generos.setEnabled(true);
    }
    public void addListener(ActionListener listener){
        bt_autoresnotselected.addActionListener(listener);
        bt_autoreselected.addActionListener(listener);
        bt_eliminarseleccion.addActionListener(listener);
        bt_seleccionartodos.addActionListener(listener);
        bt_crearEditar.addActionListener(listener);
        tx_id.addFocusListener((FocusListener) listener);
        bt_cancelar.addActionListener(listener);
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
        tx_titulo.setText("");
        tx_numeroejemplares.setText("");
        tx_precio.setText(String.valueOf(0));
        dp_fechapublicacion.setDateToToday();
        cb_generos.setSelectedIndex(0);
        cb_generos.setEnabled(false);
        ck_stock.setSelected(false);
        imagePanel.setDefaultImage();
        tx_numeroejemplares.setText(String.valueOf(1));
        tx_id.setBorder(tx_titulo.getBorder());
    }
    public JTextField getTx_titulo(){
        return tx_titulo;
    }
    public JSlider getSl_precio() {
        return sl_precio;
    }
    public JTextField getTx_precio(){
        return tx_precio;
    }
    public JTextField getTx_id() {
        return tx_id;
    }
    private boolean checkFieldsValueIncorrect(){
        if(tx_precio.getText().equals("0") && checkFieldsEmpty()) {
            errorMessage="Los siguientes datos no pueden ser cero: \n";
            errorMessage+="✯Precio \n";
            return false;
        }else if(tx_precio.getText().equals("0") && !checkFieldsEmpty()){
            errorMessage+="Los siguientes datos no pueden ser cero: \n";
            errorMessage+="✯Precio \n";
            return false;
        }else if (!tx_precio.getText().equals("0") && !checkFieldsEmpty()){
            return false;
        }
        return true;
    }
    private boolean checkFieldsEmpty(){

        if(tx_id.getText().isEmpty() || tx_titulo.getText().isEmpty() || tx_precio.getText().isEmpty() || tx_numeroejemplares.getText().isEmpty() || getAutoresSelected().isEmpty() || dp_fechapublicacion.getDate()==null) {
            errorMessage="Faltan los siguientes datos: \n";
            if (tx_id.getText().isEmpty()) {
                errorMessage += "✯Id \n";
            }
            if (tx_titulo.getText().isEmpty()) {
                errorMessage += "✯Titulo \n";
            }
            if (tx_precio.getText().isEmpty()) {
                errorMessage += "✯Precio \n";
            }
            if (tx_numeroejemplares.getText().isEmpty()) {
                errorMessage += "✯Numero de ejemplares \n";
            }
            if (getAutoresSelected().isEmpty()) {
                errorMessage += "✯Autores seleccionados \n";
            }
            if (dp_fechapublicacion.getDate()==null) {
                errorMessage += "✯Fecha de publicacion \n";
            }
            return false;
        }
        return true;
    }
    private boolean checkLength (){
        if(tx_id.getText().length()>7 && !checkFieldsValueIncorrect()){
            errorMessage="Los siguientes datos no pueden contener mas de 7 caracteres: \n";
            errorMessage+="✯Id \n";
            return false;
        } else if (tx_precio.getText().length()>7 && checkFieldsValueIncorrect()) {
            errorMessage+="Los siguientes datos no pueden contener mas de 7 caracteres: \n";
            errorMessage+="✯Id \n";
            return false;
        }else if(!checkFieldsValueIncorrect()){
            return false;
        }
        return true;
    }
    private boolean checkFinal(){
        if(idExistente && checkLength()) {
            errorMessage="✯Id existente \n";
            return false;
        }else if(idExistente && !checkLength()){
            errorMessage="✯Id existente \n";
            return false;
        } else if (!idExistente && !checkLength()) {
            return false;
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
    public void setLibroModel(LibroModel libroModel){
        tx_id.setText(String.valueOf(libroModel.getId()));
        tx_precio.setText(String.valueOf(libroModel.getPrecio()));
        tx_titulo.setText(libroModel.getTitulo());
        tx_numeroejemplares.setText(String.valueOf(libroModel.getEjemplares()));
        cb_generos.setSelectedItem(libroModel.getGenero());
        ck_stock.setSelected(libroModel.isStock());
        imagePanel.setSelectedImage(libroModel.getImagen());
        imagePanel.setBackgroundImage(imagePanel.getSetSelectedImage());
        dp_fechapublicacion.setDate(libroModel.getFecha_publicacion().toLocalDate());
        try {
            if(XMLManager.getAllAutores()!=null) {
                ArrayList<AutorModel> autoresNotSelected = XMLManager.getAllAutores();
                for (int i = 0; i < autoresNotSelected.size(); i++) {
                    for (int j=0;j<libroModel.getAutor().size();j++) {
                        if (autoresNotSelected.get(i).getId() == libroModel.getAutor().get(j).getId()) {
                            autoresNotSelected.remove(i);
                        }
                    }
                }
                loadJListaNotSelected(autoresNotSelected);
                loadJListaSelected(libroModel.getAutor());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public LibroModel getLibroModel() {
        if(!checkFinal()){
            JOptionPane.showMessageDialog(mainPanel,errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
        }
        else if(checkFinal()) {
            return new LibroModel(Integer.valueOf(tx_id.getText()), tx_titulo.getText(), getSelectedValues(), (GeneroModel) cb_generos.getSelectedItem(), Double.valueOf(tx_precio.getText()), Date.valueOf(dp_fechapublicacion.getDate()), Integer.valueOf(tx_numeroejemplares.getText()), ck_stock.isSelected());
        }
        return null;
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JComboBox getCb_generos(){
        return cb_generos;
    }
}
