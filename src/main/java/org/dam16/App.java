package org.dam16;

import org.dam16.controllers.*;
import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;
import org.dam16.models.LibroModel;
import org.dam16.services.XMLService;
import org.dam16.views.CrearProductoPanel;
import org.dam16.views.MainFrame;
import org.dam16.views.VerProductosPanel;
import org.dam16.xml.XMLManager;

import javax.swing.*;
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
                ArrayList <AutorModel> autorModelArrayList = new ArrayList<>();
                String[] autores = new String[]{
                "Gabriel García Márquez", "Jorge Luis Borges", "Jane Austen", "Virginia Woolf", "Haruki Murakami", "Franz Kafka", "William Faulkner", "Fyodor Dostoevsky", "Leo Tolstoy", "Suzzane Collins", "Isabel Allende", "Charles Dickens", "George Orwell", "Ernest Hemingway", "James Joyce", "Julio Cortázar", "J.K. Rowling", "Mark Twain", "Emily Brontë", "Aldous Huxley"};
                for (int i = 0; i < 20; i++) {
                     autorModelArrayList.add(new AutorModel(i+1,autores[i]));
                 }
                boolean ok = XMLManager.crearAutores(autorModelArrayList);
                if(ok){
                    JOptionPane.showMessageDialog(null, "Autores creados");
                }
            }
            if(XMLManager.getAllGeneros()==null){
                ArrayList <GeneroModel> generoModelArrayList = new ArrayList<>();
                String[] generos = new String[]{
                "Novela","Cuento","Poesía","Ensayo","Drama","Ciencia ficción","Fantasía","Realismo mágico","Terror","Suspense","Romance","Aventura","Biografía","Autobiografía","Crónica","Sátira","Épica","Distopía","Historia","Fábula"};
                for (int i = 0; i < 20; i++) {
                    generoModelArrayList.add(new GeneroModel(i+1,generos[i]));
                }
                boolean ok = XMLManager.crearGeneros(generoModelArrayList);
                if(ok){
                    JOptionPane.showMessageDialog(null,"Generos creados");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //
        LibroModel libro = XMLManager.getLibroById(326);
        if(libro!=null) {
            JOptionPane.showMessageDialog(null, libro);
        }
        //
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
