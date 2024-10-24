package org.dam16.views;

import org.dam16.models.LibroModel;
import javax.swing.*;
import java.awt.*;

public class EditarProductoDialog extends JDialog {
    private JPanel mainPanel;
    private CrearProductoPanel crearProductoPanel;
    private LibroModel libroModel;
    private MainFrame mainFrame;
    public EditarProductoDialog(MainFrame mainFrame,LibroModel libroModel, boolean modal) {
        super(mainFrame, modal);
        this.mainFrame = mainFrame;
        this.libroModel = libroModel;
        initWindow();

    }
    public void initWindow() {
        mainPanel.add(mainFrame.getCrearProductoPanel().getMainPanel());
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        mainFrame.getCrearProductoPanel().setEditMode();
        mainFrame.getCrearProductoPanel().setLibroModel(libroModel);
        mainPanel.setBackground(new Color(111,203,229));
    }

    public void showWindow() {
        setVisible(true);
    }
}
