package org.dam16.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame {
    private JPanel mainPanel;
    private JButton bt_inicio;
    private JButton bt_creararticulos;
    private JButton bt_verarticulos;
    private JPanel contenedorPaneles;
    private CardLayout navegador;
    private InicioPanel inicioPanel;
    private CrearProductoPanel crearProductoPanel;
    private VerProductosPanel verProductosPanel;
    public MainFrame() {

    }
    private void initComponents() {
    }

    private void setCommands(){
        bt_inicio.setActionCommand("");
    }
    public void addListener(ActionListener listener) {
        bt_inicio.addActionListener(listener);
        bt_creararticulos.addActionListener(listener);
        bt_verarticulos.addActionListener(listener);
    }
}
