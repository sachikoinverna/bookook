package org.dam16.xml;

import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;

import org.dam16.models.LibroModel;
import org.dam16.services.XMLService;
import org.w3c.dom.*;

import java.util.ArrayList;

import static org.dam16.services.XMLService.ROOT_NODE;
public class XMLManager {
    public static boolean createLibro(LibroModel libro) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            boolean generoExists = false;
            ArrayList<Boolean> autoresExists = new ArrayList<>();
            NodeList nList = document.getElementsByTagName("genero");
            if (nList.getLength() > 0) {
                for (int i = 0; i < nList.getLength(); i++) {
                    Element element = (Element) nList.item(i);
                    if (element.getAttribute("id").equals(libro.getGenero().getIdGenero())) {
                        generoExists = true;
                    }
                }
            }
            NodeList nListAutores = document.getElementsByTagName("autor");
            if (nListAutores.getLength() > 0) {
                for (int i = 0; i < nListAutores.getLength(); i++) {
                    Element element = (Element) nListAutores.item(i);
                    for (int j = 0; j < libro.getAutor().size(); j++) {
                        if (element.getAttribute("id").equals(libro.getAutor().get(j).getId())) {
                            autoresExists.add(true);
                        }
                    }
                }
            }
            if (generoExists && !autoresExists.contains(false)) {
                Element element = document.createElement("libro");
                element.setAttribute("id", String.valueOf(libro.getId()));
                element.setAttribute("titulo", libro.getTitulo());
                element.setAttribute("genero", String.valueOf(libro.getGenero().getIdGenero()));
                element.setAttribute("ejemplares", String.valueOf(libro.getEjemplares()));
                element.setAttribute("publicacion", libro.getFecha_publicacion().toString());
                Element autoresElement = document.createElement("autores");
                element.appendChild(autoresElement);
                for (int i = 0; i < libro.getAutor().size(); i++) {
                    Element autorElement = document.createElement("autor");
                    autorElement.setAttribute("id", String.valueOf(libro.getAutor().get(i).getId()));
                    autoresElement.appendChild(autorElement);
                }
                NodeList nodeList = document.getElementsByTagName("libros");
                if (nodeList.getLength() > 0) {
                    Element parent = (Element) nodeList.item(0);
                    parent.appendChild(element);
                    return XMLService.saveXML(document);
                }
            }
        }
        return false;
    }

    public static boolean crearGeneros(ArrayList<GeneroModel> generoModels) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            Element element = document.createElement("genero");
            for (GeneroModel generoModel : generoModels) {
                element.setAttribute("id", String.valueOf(generoModel.getIdGenero()));
                element.setAttribute("nombre", generoModel.getGenero());
                NodeList nodeList = document.getElementsByTagName("generos");
                if (nodeList.getLength() > 0) {
                    Element parent = (Element) nodeList.item(0);
                    parent.appendChild(element);
                }
            }
            return XMLService.saveXML(document);
        }
        return false;
    }
    public static GeneroModel getGeneroById(int id) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try {
                NodeList nodeList = document.getElementsByTagName("genero");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    if (element.getAttribute("id").equals(String.valueOf(id))) {
                        return new GeneroModel((Integer.parseInt(element.getAttribute("id"))),element.getAttribute("nombre"));
                    }
                }
            }catch (Exception e) {

            }
        }
        return null;
    }
    public static boolean crearAutores(ArrayList<AutorModel> autorModels) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            Element element = document.createElement("autor");
            for (AutorModel autorModel : autorModels) {
                element.setAttribute("id", String.valueOf(autorModel.getId()));
                element.setAttribute("nombre", autorModel.getName());
                NodeList nodeList = document.getElementsByTagName("autores");
                if (nodeList.getLength() > 0) {
                    Element parent = (Element) nodeList.item(0);
                    parent.appendChild(element);
                }
            }
            return XMLService.saveXML(document);
        }
        return false;
    }
    public static AutorModel getAutoresById(int id) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try {
                NodeList nodeList = document.getElementsByTagName("autor");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    if (element.getAttribute("id").equals(String.valueOf(id))) {
                        return new AutorModel((Integer.parseInt(element.getAttribute("id"))), element.getAttribute("nombre"));
                    }
                }
            } catch (Exception e) {
                throw new Exception("Error al obtener el genero");
            }
        }else {
            throw new Exception("Error al obtener el xml");
        }
        return null;
    }
    public static LibroModel getLibroById(int id) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            NodeList nodeList = document.getElementsByTagName("libros");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if(Integer.valueOf(element.getAttribute("id"))==id) {
                    ArrayList<AutorModel> autorModels = new ArrayList<>();
                    NodeList nodel = element.getChildNodes();
                    for (int j = 0; j < nodel.getLength(); j++) {
                        autorModels.add((AutorModel) nodel.item(j));

                    }
                    return new LibroModel() =
                            new LibroModel(Integer.valueOf(element.getAttribute("id")),element.getAttribute("titulo"),element.getAttribute());
                }
            }
        }
        return null;
    }
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
