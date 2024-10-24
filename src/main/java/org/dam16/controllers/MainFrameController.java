package org.dam16.controllers;

import org.dam16.views.MainFrame;
import org.dam16.xml.XMLManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class MainFrameController implements ActionListener {
    private MainFrame mainFrame;
    public static final String GO_INICIO_PANEL = "GO_INICIO_PANEL";
    public static final String GO_CREAR_PRODUCTO_PANEL = "GO_CREAR_PRODUCTO_PANEL";
    public static final String GO_VER_PRODUCTOS_PANEL = "GO_VER_PRODUCTOS_PANEL";
    public MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    private void handlerGoInicioPanel() {
        mainFrame.navigate("inicioPanel");
    }
    private void handlerGoCrearProductoPanel() {
        mainFrame.getCrearProductoPanel().setCreateMode();
        mainFrame.getCrearProductoPanel().cleanFields();
        mainFrame.getCrearProductoPanel().add(mainFrame.getCrearProductoPanel().getMainPanel());
        mainFrame.getCrearProductoPanel().revalidate();
        mainFrame.getCrearProductoPanel().repaint();
        mainFrame.navigate("crearProductoPanel");
    }
    private void handlerGoVerProductoPanel() {
        mainFrame.navigate("verProductosPanel");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case GO_INICIO_PANEL:
                handlerGoInicioPanel();
                break;
                case GO_CREAR_PRODUCTO_PANEL:
                    handlerGoCrearProductoPanel();
                    break;
                    case GO_VER_PRODUCTOS_PANEL:
                        try {
                            if(XMLManager.getAllLibros()!=null) {
                                mainFrame.getVerProductosPanel().setLibroPanel(XMLManager.getAllLibros());
                            }
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        handlerGoVerProductoPanel();
                        break;
        }
    }
}
