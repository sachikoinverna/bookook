package org.dam16.views;

import org.dam16.models.AutorModel;
import org.dam16.models.LibroModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
    private JTable tb_datos;
    private JList l_autores;
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
        Image imagenEscalada = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lb_imagen.setIcon(new ImageIcon(imagenEscalada));
    }
    public void setData(LibroModel libro) {
        setProductImage(libro.getImagen());
        String[][] datos= new String[][]{new String[]{String.valueOf(libro.getId()), String.valueOf(libro.getEjemplares()), String.valueOf(libro.getPrecio()), libro.getTitulo(), libro.getGenero().toString(), String.valueOf(libro.getFecha_publicacion()), String.valueOf(libro.isStock())}};
        DefaultTableModel tableModel = new DefaultTableModel(datos,new String[]{"Id","Ejemplares","Precio","Titulo","Genero","Fecha de publicacion","Stock"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tb_datos.setModel(tableModel);
        /*lb_id.setText(String.valueOf(libro.getId()));
        lb_ejemplares.setText(String.valueOf(libro.getEjemplares()));
        lb_precio.setText(String.valueOf(libro.getPrecio()));
        lb_titulo.setText(libro.getTitulo());
        lb_genero.setText(libro.getGenero().toString());
        lb_publicacion.setText(libro.getFecha_publicacion().toString());
        cb_stock.setSelected(libro.isStock());*/
        loadListAutores(libro.getAutor());
    }
    private void loadListAutores(ArrayList<AutorModel> autores) {
        DefaultListModel modelo = new DefaultListModel();
        for (AutorModel autor : autores) {
            modelo.addElement(autor);
        }
        l_autores.setModel(modelo);
    }
    private void setCommands(){
    }
    public void addListeners(ActionListener listener){
        tb_datos.addMouseListener((MouseListener) listener);
    }
}
