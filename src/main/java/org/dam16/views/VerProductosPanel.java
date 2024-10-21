package org.dam16.views;

import org.dam16.controllers.LibroPanelController;
import org.dam16.models.LibroModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerProductosPanel extends JPanel {
    private JPanel mainPanel;
    private JPanel booksListPanel;
    private JTextField tx_idLibro;
    private Image backgroundImage;
    private MainFrame mainFrame;
    public VerProductosPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        add(mainPanel);
        mainPanel.setOpaque(false);
        setCommands();
        booksListPanel.setLayout(new BoxLayout(booksListPanel,BoxLayout.Y_AXIS));
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
    private void setCommands(){

    }
    public void addListener(ActionListener listener) {

    }
}
