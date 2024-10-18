package org.dam16.views;

import org.dam16.models.LibroModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LibroPanel extends JPanel {
    private JPanel mainPanel;
    private LibroModel libro;
    public LibroPanel(LibroModel libro) {
        add(mainPanel);
        setCommands();
    }
    private void setCommands(){

    }
    public void addListeners(ActionListener listener){

    }
}
