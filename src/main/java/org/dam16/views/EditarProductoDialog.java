package org.dam16.views;

import org.dam16.models.LibroModel;
import sun.java2d.Disposer;

import javax.swing.*;
import java.awt.event.*;

public class EditarProductoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private CrearProductoPanel crearProductoPanel;
    private LibroModel libroModel;
    private MainFrame mainFrame;
    public EditarProductoDialog(MainFrame mainFrame,LibroModel libroModel) {
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonCancel);
        setCrearProductoPanel(mainFrame.getCrearProductoPanel());
        this.libroModel = libroModel;
        crearProductoPanel.setEditMode();
        crearProductoPanel.setLibroModel(libroModel);
        //mainFrame.getCrearProductoPanel().s
        mainPanel.add(crearProductoPanel);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void setCrearProductoPanel(CrearProductoPanel crearProductoPanel) {
        this.crearProductoPanel = crearProductoPanel;
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
       // EditarProductoDialog dialog = new EditarProductoDialog(MainFrame mainFrame);
       // dialog.pack();
       // dialog.setVisible(true);
       // System.exit(0);
    }
}
