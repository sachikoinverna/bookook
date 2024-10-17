package org.dam16.views;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CrearProductoPanel extends JPanel {
    private JPanel mainPanel;
    private JCheckBox ck_stock;
    private JTextField tx_id;
    private ImagePanel imagePanel;
    private JPanel imagePreviewPanel;
    private JTextField tx_titulo;

    public CrearProductoPanel() {
        add(mainPanel);
        mainPanel.setOpaque(false);
        setImagePreviewPanel();
        tx_id.addKeyListener(new KeyAdapter() {
                                 @Override
                                 public void keyReleased(KeyEvent e) {
                                     if(Character.isLetter(e.getKeyChar()) || Character.isSpaceChar(e.getKeyChar())){
                                         tx_id.setText(tx_id.getText().substring(0,tx_id.getText().length()-1));
                                     }
                                 }
                             }
        );
    }
    private void setImagePreviewPanel(){
        imagePanel = new ImagePanel();
        imagePreviewPanel.add(imagePanel);
    }
    public ImagePanel getImagePreviewPanel(){
        return imagePanel;
    }
    private void setCommands(){

    }
    public void addListener(ActionListener listener){

    }
    //public LibroModel getLibroModel() {
     //  return new LibroModel(tx_id.getText(),,ck_stock.isSelected());
    //}
}
