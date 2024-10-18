package org.dam16.xml;

import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;

import org.dam16.services.XMLService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import static org.dam16.services.XMLService.ROOT_NODE;
public class XMLManager {
    public static ArrayList<GeneroModel> getAllGeneros() throws Exception {
        ArrayList<GeneroModel> generos = new ArrayList<>();
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try {
                NodeList nodeList = document.getElementsByTagName("genero");
                if(nodeList.getLength()>0){
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element element = (Element) nodeList.item(i);
                        generos.add(new GeneroModel(Integer.valueOf(element.getAttribute("id")),element.getAttribute("nombre")));
                    }
                    return generos;
                }
            }catch (Exception e) {

            }
        }
        return null;

    }
    public static ArrayList<AutorModel> getAllAutores() throws Exception {
        ArrayList<AutorModel> autores = new ArrayList<>();
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try {
                NodeList nodeList = document.getElementsByTagName("autor");
                if(nodeList.getLength()>0){
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element element = (Element) nodeList.item(i);
                        autores.add(new AutorModel(Integer.valueOf(element.getAttribute("id")),element.getAttribute("nombre")));
                    }
                    return autores;
                }
            }catch (Exception e) {

            }
        }
        return null;

    }
}
