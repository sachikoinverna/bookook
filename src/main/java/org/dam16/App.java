package org.dam16;

import org.dam16.controllers.MainFrameController;
import org.dam16.views.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        MainFrame frame = new MainFrame();
        MainFrameController mainFrameControllerontroller = new MainFrameController(frame);
        frame.addListener(mainFrameControllerontroller);
        frame.showWindow();
    }
}
