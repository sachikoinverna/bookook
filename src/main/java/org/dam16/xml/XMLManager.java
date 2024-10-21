package org.dam16.xml;

import org.dam16.models.AutorModel;
import org.dam16.models.GeneroModel;

import org.dam16.models.LibroModel;
import org.dam16.services.XMLService;
import org.w3c.dom.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
public class XMLManager {
    public static boolean createLibro(LibroModel libro) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            /*boolean generoExists = false;
            ArrayList<Boolean> autoresExists = new ArrayList<>();
            NodeList nList = document.getElementsByTagName("genero");
            if (nList.getLength() > 0) {
                for (int i = 0; i < nList.getLength(); i++) {
                    Element element = (Element) nList.item(i);
                    if (element.getAttribute("id").equals(libro.getGenero().getIdGenero())) {
                        generoExists = true;
                    }
                }
            }*/
            //Comprobar que el mismo libro del autor con el mismo titulo no exista
            /*NodeList nListAutores = document.getElementsByTagName("autor");
            if (nListAutores.getLength() > 0) {
                for (int i = 0; i < nListAutores.getLength(); i++) {
                    Element element = (Element) nListAutores.item(i);
                    for (int j = 0; j < libro.getAutor().size(); j++) {
                        if (element.getAttribute("id").equals(libro.getAutor().get(j).getId())) {
                            autoresExists.add(true);
                        }
                    }
                }
            }*/
            //if (generoExists && !autoresExists.contains(false)) {
                Element element = document.createElement("libro");
                element.setAttribute("id", String.valueOf(libro.getId()));
                element.setAttribute("titulo", libro.getTitulo());
                element.setAttribute("generoLibro", String.valueOf(libro.getGenero().getIdGenero()));
                element.setAttribute("ejemplares", String.valueOf(libro.getEjemplares()));
                element.setAttribute("publicacion", libro.getFecha_publicacion().toString());
                element.setAttribute("imagen",libro.getImagen());
                element.setAttribute("precio", String.valueOf(libro.getPrecio()));
                Element autoresElement = document.createElement("autoresLibro");
                element.appendChild(autoresElement);
                for (int i = 0; i < libro.getAutor().size(); i++) {
                    Element autorElement = document.createElement("autorLibro");
                    autorElement.setAttribute("id", String.valueOf(libro.getAutor().get(i).getId()));
                    autoresElement.appendChild(autorElement);
                }
                NodeList nodeList = document.getElementsByTagName("libros");
                if (nodeList.getLength() > 0) {
                    Element parent = (Element) nodeList.item(0);
                    parent.appendChild(element);
                    return XMLService.saveXML(document);
                //}
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
                if(element.getAttribute("id").equals(String.valueOf(id))) {
                    ArrayList<AutorModel> autorModels = new ArrayList<>();
                    NodeList nodel = element.getChildNodes();
                    for (int j = 0; j < nodel.getLength(); j++) {
                        autorModels.add((AutorModel) nodel.item(j));

                    }
                    NodeList nodeL = element.getElementsByTagName("generos");
                    GeneroModel generoModel = new GeneroModel();
                    for (int j = 0; j < nodeL.getLength(); j++) {
                        Element autorElement = (Element) nodeL.item(j);
                        if(autorElement.getAttribute("id").equals(element.getAttribute("genero"))){
                            generoModel.setIdGenero(Integer.parseInt(autorElement.getAttribute("id")));
                            generoModel.setGenero(autorElement.getAttribute("nombre"));
                        }
                    }
               //    return new LibroModel() =
               //           new LibroModel(Integer.valueOf(element.getAttribute("id")),element.getAttribute("titulo"),autorModels,generoModel,Double.valueOf(element.getAttribute("precio")), element.getAttribute("publicacion"),Integer.valueOf(element.getAttribute("ejemplares")),Boolean.valueOf(element.getAttribute("stock")),element.getAttribute("imagen"));
                }
            }
        }
        return null;
    }
    public static boolean deleteLibroById(int id) {
        Document document = XMLService.loadOrCreateXML();
        if (document != null) {
            NodeList nodeList = document.getElementsByTagName("libro");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(String.valueOf(id))) {
                    element.getParentNode().removeChild(element);
                    XMLService.saveXML(document);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean editLibro(LibroModel libro) {
        Document document = XMLService.loadOrCreateXML();
        if (document != null) {
            NodeList nodeList = document.getElementsByTagName("libro");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(String.valueOf(libro.getId()))) {
                    element.setAttribute("id", String.valueOf(libro.getId()));
                    element.setAttribute("titulo", libro.getTitulo());
                    element.setAttribute("generoLibro", String.valueOf(libro.getGenero().getIdGenero()));
                    element.setAttribute("ejemplares", String.valueOf(libro.getEjemplares()));
                    element.setAttribute("publicacion", libro.getFecha_publicacion().toString());
                    element.setAttribute("imagen",libro.getImagen());
                    element.setAttribute("precio", String.valueOf(libro.getPrecio()));
                    NodeList autores = element.getElementsByTagName("autoresLibro");
                    Element autorElementNode = (Element) autores.item(0);
                    NodeList nodeAutor = element.getElementsByTagName("autorLibro");
                    if (nodeAutor.getLength() < libro.getAutor().size()) {
                        for (int j = 0; j < nodeAutor.getLength(); j++) {
                            Element autorElement = (Element) nodeAutor.item(j);
                            autorElement.setAttribute("id", String.valueOf(libro.getAutor().get(j).getId()));
                        }
                        for (int z = nodeAutor.getLength(); z < libro.getAutor().size(); z++) {
                            Element autorElement = document.createElement("autorLibro");
                            autorElement.setAttribute("id", String.valueOf(libro.getAutor().get(z).getId()));
                            autorElementNode.appendChild(autorElement);
                        }
                    } else if (nodeAutor.getLength()>libro.getAutor().size()) {
                        for (int j = 0; j < libro.getAutor().size(); j++) {
                            Element autorElement = (Element) nodeAutor.item(j);
                            autorElement.setAttribute("id", String.valueOf(libro.getAutor().get(j).getId()));
                        }
                        for (int z = libro.getAutor().size(); z < nodeAutor.getLength(); z=libro.getAutor().size()) {
                            Element autorElement = (Element) nodeAutor.item(z);
                            autorElement.getParentNode().removeChild(autorElement);
                        }
                    }
                }
                        return XMLService.saveXML(document);
                }
            }
        return false;
    }
    public static ArrayList<LibroModel> getAllLibros() throws ParseException {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            ArrayList<LibroModel> libroModelArrayList = new ArrayList<>();
            NodeList nodeList = document.getElementsByTagName("libro");
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    ArrayList<AutorModel> autorModels = new ArrayList<>();
                    NodeList nodel = element.getElementsByTagName("autorLibro");
                    NodeList nodeAutores = document.getElementsByTagName("autor");
                    for (int j = 0; j < nodel.getLength(); j++) {
                        Element autorElement = (Element) nodel.item(j);
                        for (int s = 0; s < nodeAutores.getLength(); s++) {
                            Element autorElement2 = (Element) nodeAutores.item(s);
                            if(autorElement2.getAttribute("id").equals(autorElement.getAttribute("id"))) {
                                AutorModel autorModel = new AutorModel();
                                autorModel.setId(Integer.parseInt(autorElement2.getAttribute("id")));
                                autorModel.setName(autorElement2.getAttribute("nombre"));
                                autorModels.add(autorModel);
                            }
                        }

                    }
                    NodeList nodeL = document.getElementsByTagName("genero");
                    GeneroModel generoModel = new GeneroModel();
                    for (int z = 0; z < nodeL.getLength(); z++) {
                        Element generoElement = (Element) nodeL.item(z);
                        if (generoElement.getAttribute("id").equals(element.getAttribute("generoLibro"))) {
                            generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                            generoModel.setGenero(generoElement.getAttribute("nombre"));
                            break;
                        }
                    }
                    libroModelArrayList.add(
                            new LibroModel(Integer.parseInt(element.getAttribute("id")),
                                    element.getAttribute("titulo"),
                                    autorModels,
                                    generoModel,
                                    Double.valueOf(element.getAttribute("precio")),
                                    Date.valueOf(element.getAttribute("publicacion")), Integer.parseInt(element.getAttribute("ejemplares")),
                                    Boolean.parseBoolean(element.getAttribute("stock")),
                                    element.getAttribute("imagen")));
                }
                return libroModelArrayList;
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
