package org.dam16.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.dam16.controllers.MainFrameController.*;

public class MainFrame extends JFrame implements InterfaceView{
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
        initWindow();
        addPanels();

    }
    private void addPanels() {
        navegador = (CardLayout) contenedorPaneles.getLayout();
        inicioPanel = new InicioPanel();
        inicioPanel.setBackgroundImage("/backgroundpruebal.jpg");
        crearProductoPanel = new CrearProductoPanel();
        crearProductoPanel.setBackgroundImage("/backgroundprueba.jpg");
        verProductosPanel = new VerProductosPanel(this);
        verProductosPanel.setBackgroundImage("/backgroundpruebas.jpg");
        contenedorPaneles.add(inicioPanel,"inicioPanel");
        contenedorPaneles.add(crearProductoPanel,"crearProductoPanel");
        contenedorPaneles.add(verProductosPanel,"verProductosPanel");

    }
    public InicioPanel getInicioPanel() {
        return inicioPanel;
    }
    public CrearProductoPanel getCrearProductoPanel() {
        return crearProductoPanel;
    }
    public VerProductosPanel getVerProductosPanel() {
        return verProductosPanel;
    }
    public void navigate(String panelName) {
        navegador.show(contenedorPaneles, panelName);
    }

    @Override
    public void initWindow() {
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setCommands();
    }

    @Override
    public void showWindow() {
        setVisible(true);
    }

    @Override
    public void closeWindow() {
        dispose();
    }

    public void setCommands(){
        bt_inicio.setActionCommand(GO_INICIO_PANEL);
        bt_creararticulos.setActionCommand(GO_CREAR_PRODUCTO_PANEL);
        bt_verarticulos.setActionCommand(GO_VER_PRODUCTOS_PANEL);
    }
    public void addListener(ActionListener listener) {
        bt_inicio.addActionListener(listener);
        bt_creararticulos.addActionListener(listener);
        bt_verarticulos.addActionListener(listener);
    }

    @Override
    public void initComponents() {

    }
}
