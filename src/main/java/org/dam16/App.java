package org.dam16;

import org.dam16.controllers.CrearProductoControllerPanel;
import org.dam16.controllers.ImagePanelcontroller;
import org.dam16.controllers.MainFrameController;
import org.dam16.controllers.VerProductoPanelController;
import org.dam16.models.GeneroModel;
import org.dam16.services.XMLService;
import org.dam16.views.CrearProductoPanel;
import org.dam16.views.MainFrame;
import org.dam16.views.VerProductosPanel;
import org.dam16.xml.XMLManager;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class  App
{
    public static void main( String[] args )
    {
        try {
            if(XMLManager.getAllAutores()==null){
                ArrayList <GeneroModel> generoModelArrayList = new ArrayList<>();
                String[] generos = new String[]{"Novela", "Cuento", "Poesía",
                        "Ensayo", "Drama","Ciencia ficción",
                       "Fantasía","Realismo mágico","Terror",
                        "Suspense","Romance","Aventura",
                        "Biografía","Autobiografía","Crónica",
                        "Sátira","Épica","Distopía",
                        "Historia","Fábula"};
                for (int i = 1; i < 21; i++) {
                     generoModelArrayList.add(new GeneroModel(i,generos[i]));
                 }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        MainFrame frame = new MainFrame();
        MainFrameController mainFrameControllerontroller = new MainFrameController(frame);
        CrearProductoControllerPanel crearProductoControllerPanel = new CrearProductoControllerPanel(frame);
        ImagePanelcontroller imagePanelcontroller = new ImagePanelcontroller(frame.getCrearProductoPanel().getImagePreviewPanel());
        VerProductoPanelController verProductoPanelController = new VerProductoPanelController(frame.getVerProductosPanel());

        frame.addListener(mainFrameControllerontroller);
        frame.getCrearProductoPanel().addListener(crearProductoControllerPanel);
        frame.getCrearProductoPanel().getImagePreviewPanel().addListener(imagePanelcontroller);
        frame.getVerProductosPanel().addListener(verProductoPanelController);

        frame.showWindow();
    }
}
