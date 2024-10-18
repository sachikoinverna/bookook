package org.dam16;

import org.dam16.controllers.CrearProductoControllerPanel;
import org.dam16.controllers.ImagePanelcontroller;
import org.dam16.controllers.MainFrameController;
import org.dam16.controllers.VerProductoPanelController;
import org.dam16.views.CrearProductoPanel;
import org.dam16.views.MainFrame;
import org.dam16.views.VerProductosPanel;

/**
 * Hello world!
 *
 */
public class  App
{
    public static void main( String[] args )
    {

        MainFrame frame = new MainFrame();
        MainFrameController mainFrameControllerontroller = new MainFrameController(frame);
        CrearProductoControllerPanel crearProductoControllerPanel = new CrearProductoControllerPanel(frame.getCrearProductoPanel());
        ImagePanelcontroller imagePanelcontroller = new ImagePanelcontroller(frame.getCrearProductoPanel().getImagePreviewPanel());
        VerProductoPanelController verProductoPanelController = new VerProductoPanelController(frame.getVerProductosPanel());

        frame.addListener(mainFrameControllerontroller);
        frame.getCrearProductoPanel().addListener(crearProductoControllerPanel);
        frame.getCrearProductoPanel().getImagePreviewPanel().addListener(imagePanelcontroller);
        frame.getVerProductosPanel().addListener(verProductoPanelController);

        frame.showWindow();
    }
}
