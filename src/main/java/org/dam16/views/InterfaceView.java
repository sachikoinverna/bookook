package org.dam16.views;

import java.awt.event.ActionListener;

public interface InterfaceView {
    void initWindow();
    void showWindow();
    void closeWindow();
    void setCommands();
    void addListener(ActionListener listener);
    void initComponents();
}
