package org.dam16.views;

import org.dam16.models.AutorModel;
import org.dam16.models.LibroModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static org.dam16.controllers.LibroPanelController.EDIT_PRODUCT;
import static org.dam16.controllers.LibroPanelController.DELETE_PRODUCT;

public class LibroPanel extends JPanel {
    private JPanel mainPanel;
    private JLabel lb_imagen;
    private JLabel lb_id;
    private JLabel lb_ejemplares;
    private JList jl_escritores;
    private JLabel lb_precio;
    private JLabel lb_titulo;
    private JLabel lb_genero;
    private JLabel lb_publicacion;
    private JCheckBox cb_stock;
    private JButton bt_editar;
    private JButton bt_eliminar;
    private LibroModel libro;
    public LibroPanel(LibroModel libro) {
        add(mainPanel);
        setCommands();
        setData(libro);
        this.libro = libro;
    }
    public LibroModel getLibro() {
        return libro;
    }
    private void setProductImage(String image) {
        ImageIcon icon = new ImageIcon(image);
        Image imagenEscalada = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lb_imagen.setIcon(new ImageIcon(imagenEscalada));
    }
    public void setData(LibroModel libro) {
        setProductImage(libro.getImagen());
        lb_id.setText(String.valueOf(libro.getId()));
        lb_ejemplares.setText(String.valueOf(libro.getEjemplares()));
        lb_precio.setText(String.valueOf(libro.getPrecio()));
        lb_titulo.setText(libro.getTitulo());
        lb_genero.setText(libro.getGenero().toString());
        lb_publicacion.setText(libro.getFecha_publicacion().toString());
        cb_stock.setSelected(libro.isStock());
        loadListAutores(libro.getAutor());
    }
    private void loadListAutores(ArrayList<AutorModel> autores) {
        DefaultListModel modelo = new DefaultListModel();
        for (AutorModel autor : autores) {
            modelo.addElement(autor);
        }
        jl_escritores.setModel(modelo);
    }
    private void setCommands(){
        bt_editar.setActionCommand(EDIT_PRODUCT);
        bt_eliminar.setActionCommand(DELETE_PRODUCT);
    }
    public void addListeners(ActionListener listener){
        bt_editar.addActionListener(listener);
        bt_eliminar.addActionListener(listener);
    }
}
