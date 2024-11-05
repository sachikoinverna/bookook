package org.dam16.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam16.controllers.LibroPanelController;
import org.dam16.models.GeneroModel;
import org.dam16.models.LibroModel;
import org.dam16.xml.XMLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static org.dam16.controllers.VerProductoPanelController.FILTRAR_FECHA;

public class VerProductosPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel booksListPanel;
    private JTextField tx_idLibro;
    private DatePicker dp_fechaDede;
    private DatePicker dp_fechaHasta;
    private JComboBox cb_generoBuscar;
    private JButton bt_filtrarFecha;
    private Image backgroundImage;
    private MainFrame mainFrame;
    public VerProductosPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        add(mainPanel);
        mainPanel.setOpaque(false);
        setCommands();
        cb_generoBuscar.setName("comboBusqueda");
        booksListPanel.setLayout(new BoxLayout(booksListPanel,BoxLayout.Y_AXIS));
        try {
            loadComboGeneros(XMLManager.getAllGeneros());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public void setLibroPanel(ArrayList<LibroModel> listaLibros) {
        if(booksListPanel != null) {
            booksListPanel.removeAll();
        }
        for (LibroModel libro : listaLibros) {
            LibroPanel libroPanel = new LibroPanel(libro);
            booksListPanel.add(libroPanel);
            LibroPanelController libroPanelController = new LibroPanelController(libroPanel,mainFrame);
            libroPanel.addListeners(libroPanelController);

        }
    }
    public DatePicker getDp_fechaDesde() {
        return dp_fechaDede;
    }
    public DatePicker getDp_fechaHasta() {
        return dp_fechaHasta;
    }
    public JTextField gettx_idLibro() {
        return tx_idLibro;
    }
    private void initComponents() {
        tx_idLibro.setName("idLibro");
    }
    private void setCommands(){
        bt_filtrarFecha.setActionCommand(FILTRAR_FECHA);
    }
    public void addListener(ActionListener listener) {
        tx_idLibro.addKeyListener((KeyListener) listener);
        bt_filtrarFecha.addActionListener(listener);
        cb_generoBuscar.addItemListener((ItemListener) listener);
    }
    private void loadComboGeneros(ArrayList<GeneroModel> generos) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (GeneroModel genero : generos) {
            modelo.addElement(genero);
        }
        cb_generoBuscar.setModel(modelo);

    }
}
