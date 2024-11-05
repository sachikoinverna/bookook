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

import static org.dam16.services.XMLService.ROOT_NODE;

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
            if (document.getElementsByTagName("libros").getLength()==0)
            {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0 ) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
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
        if(document == null) {
            return false;
        }
        if(document.getElementsByTagName("generos").getLength()==0) {
            NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
            if (nodeListRoot.getLength() > 0 ) {
                Element parent = (Element) nodeListRoot.item(0);
                Element elementoGeneros = document.createElement("generos");
                parent.appendChild(elementoGeneros);
            }
        }
                for (GeneroModel generoModel : generoModels) {
                    if(getGeneroById(generoModel.getIdGenero()) == null) {
                        Element element = document.createElement("genero");
                        element.setAttribute("id", String.valueOf(generoModel.getIdGenero()));
                        element.setAttribute("nombre", generoModel.getGenero());
                        NodeList nodeList = document.getElementsByTagName("generos");
                        if (nodeList.getLength() > 0) {
                            Element parent = (Element) nodeList.item(0);
                            parent.appendChild(element);
                        }
                    }
                }
            return XMLService.saveXML(document);
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
    public static boolean crearAutores(ArrayList<AutorModel> autorModels) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document == null) {
            return false;
        }
        if(document.getElementsByTagName("autores").getLength()==0) {
            NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
            if (nodeListRoot.getLength() > 0 ) {
                Element parent = (Element) nodeListRoot.item(0);
                Element elementoGeneros = document.createElement("autores");
                parent.appendChild(elementoGeneros);
            }
        }
        for (AutorModel autorModel : autorModels) {
            if(getAutoresById(autorModel.getId()) == null) {
                Element element = document.createElement("autor");
                element.setAttribute("id", String.valueOf(autorModel.getId()));
                element.setAttribute("nombre", autorModel.getName());
                NodeList nodeList = document.getElementsByTagName("autores");
                if (nodeList.getLength() > 0) {
                    Element parent = (Element) nodeList.item(0);
                    parent.appendChild(element);
                }
            }
        }
        return XMLService.saveXML(document);
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
            if (document.getElementsByTagName("libros").getLength() == 0) {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
            NodeList nodeList = document.getElementsByTagName("libro");
            ArrayList<AutorModel> autores = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.getAttribute("id").equals(String.valueOf(id))) {
                    NodeList autoresN = element.getElementsByTagName("autorLibro");
                    NodeList nAutores = document.getElementsByTagName("autor");
                    for (int z = 0; z < autoresN.getLength(); z++) {
                        Element autor = (Element) autoresN.item(z);
                        for (int j = 0; j < nAutores.getLength(); j++) {
                            Element autorLibro = (Element) nAutores.item(j);
                            if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                                autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                            }
                        }
                    }
                    NodeList nodeL = document.getElementsByTagName("genero");
                    GeneroModel generoModel = new GeneroModel();
                    for (int x = 0; x < nodeL.getLength(); x++) {
                        Element generoElement = (Element) nodeL.item(x);
                        if (generoElement.getAttribute("id").equals(element.getAttribute("generoLibro"))) {
                            generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                            generoModel.setGenero(generoElement.getAttribute("nombre"));
                        }
                    }
                    return new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen"));
                }
            }
        }
        return null;
    }
    public static ArrayList<LibroModel> getLibrosById(String nombre) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            ArrayList<LibroModel> libros = new ArrayList();
            if (document.getElementsByTagName("libros").getLength() == 0) {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
            NodeList nodeList = document.getElementsByTagName("libro");
            ArrayList<AutorModel> autores = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                NodeList autoresN = element.getElementsByTagName("autorLibro");
                NodeList nAutores = document.getElementsByTagName("autor");
                for (int z = 0; z < autoresN.getLength(); z++) {
                    Element autor = (Element) autoresN.item(z);
                    for (int j = 0; j < nAutores.getLength(); j++) {
                        Element autorLibro = (Element) nAutores.item(j);
                        if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                            autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                        }
                    }
                }
                NodeList nodeL = document.getElementsByTagName("genero");
                GeneroModel generoModel = new GeneroModel();
                for (int x = 0; x < nodeL.getLength(); x++) {
                    Element generoElement = (Element) nodeL.item(x);
                    if (generoElement.getAttribute("id").equals(element.getAttribute("generoLibro"))) {
                        generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                        generoModel.setGenero(generoElement.getAttribute("nombre"));
                    }
                }
                for (AutorModel autor : autores) {
                    if (autor.getName().toLowerCase().contains(nombre.toLowerCase())) {
                        libros.add(new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen")));
                        break;
                    }
                }
            }
            return libros;
        }
        return null;
    }
    public static ArrayList<LibroModel> getLibrosByWords(String texto) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            ArrayList<LibroModel> libros = new ArrayList();
            ArrayList<LibroModel> librosId = new ArrayList();
            ArrayList<LibroModel> librosTitulo = new ArrayList();
            ArrayList<LibroModel> librosAutores = new ArrayList();
            if(getLibroById(Integer.parseInt(texto)) != null) {
                librosId.add(getLibroById(Integer.parseInt(texto)));
            }
            if (getLibrosByAutores(texto) != null) {
                librosAutores.addAll(getLibrosByAutores(texto));
            }
            if(getLibrosByTitulo(texto) != null) {
                librosTitulo.addAll(getLibrosByTitulo(texto));
            }
            ArrayList<Integer> idLibros = new ArrayList();
            for (LibroModel libroAutor :librosAutores) {
                if (!idLibros.contains(libroAutor.getId())) {
                idLibros.add(libroAutor.getId());}
            }
            for (LibroModel libroTitulo :librosTitulo) {
                if (!idLibros.contains(libroTitulo.getId())) {
                    idLibros.add(libroTitulo.getId());}
            }
            for (LibroModel libroId :librosId) {
                if (!idLibros.contains(libroId.getId())) {
                    idLibros.add(libroId.getId());
                }
            }
            for (Integer id :idLibros) {
                libros.add(getLibroById(id));
            }
        }
        return null;
    }
    public static ArrayList<LibroModel> getLibrosByAutores(String nombre) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            ArrayList<LibroModel> libros = new ArrayList();
            if (document.getElementsByTagName("libros").getLength() == 0) {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
            NodeList nodeList = document.getElementsByTagName("libro");
            ArrayList<AutorModel> autores = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                NodeList autoresN = element.getElementsByTagName("autorLibro");
                NodeList nAutores = document.getElementsByTagName("autor");
                for (int z = 0; z < autoresN.getLength(); z++) {
                    Element autor = (Element) autoresN.item(z);
                    for (int j = 0; j < nAutores.getLength(); j++) {
                        Element autorLibro = (Element) nAutores.item(j);
                        if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                            autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                        }
                    }
                }
                NodeList nodeL = document.getElementsByTagName("genero");
                GeneroModel generoModel = new GeneroModel();
                for (int x = 0; x < nodeL.getLength(); x++) {
                    Element generoElement = (Element) nodeL.item(x);
                    if (generoElement.getAttribute("id").equals(element.getAttribute("generoLibro"))) {
                        generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                        generoModel.setGenero(generoElement.getAttribute("nombre"));
                    }
                }
                for (AutorModel autor : autores) {
                    if (autor.getName().toLowerCase().contains(nombre.toLowerCase())) {
                        libros.add(new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen")));
                        break;
                    }
                }
            }
            return libros;
        }
        return null;
    }
    public static ArrayList<LibroModel> getLibrosByTitulo(String titulo) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            ArrayList<LibroModel> libros = new ArrayList();
            if (document.getElementsByTagName("libros").getLength() == 0) {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
            NodeList nodeList = document.getElementsByTagName("libro");
            ArrayList<AutorModel> autores = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if(element.getAttribute("titulo").toLowerCase().contains(titulo.toLowerCase())) {
                    NodeList autoresN = element.getElementsByTagName("autorLibro");
                    NodeList nAutores = document.getElementsByTagName("autor");
                    for (int z = 0; z < autoresN.getLength(); z++) {
                        Element autor = (Element) autoresN.item(z);
                        for (int j = 0; j < nAutores.getLength(); j++) {
                            Element autorLibro = (Element) nAutores.item(j);
                            if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                                autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                            }
                        }
                    }
                    NodeList nodeL = document.getElementsByTagName("genero");
                    GeneroModel generoModel = new GeneroModel();
                    for (int x = 0; x < nodeL.getLength(); x++) {
                        Element generoElement = (Element) nodeL.item(x);
                        if (generoElement.getAttribute("id").equals(element.getAttribute("generoLibro"))) {
                            generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                            generoModel.setGenero(generoElement.getAttribute("nombre"));
                        }
                    }
                            libros.add(new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen")));
                    }
                }
            return libros;
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
    public static ArrayList<LibroModel> getLibrosByFechaPublicacion(Date fechaPublicacionDesde, Date fechaPublicacionHasta) {
        ArrayList<LibroModel> libroModelArrayList = new ArrayList<>();
        Document document = XMLService.loadOrCreateXML();
        if (document != null) {
            if (document.getElementsByTagName("libros").getLength() == 0) {
                NodeList nodeListRoot = document.getElementsByTagName(ROOT_NODE);
                if (nodeListRoot.getLength() > 0) {
                    Element parent = (Element) nodeListRoot.item(0);
                    Element elementoLibros = document.createElement("libros");
                    parent.appendChild(elementoLibros);
                }
            }
            NodeList nodeList = document.getElementsByTagName("libro");
            ArrayList<AutorModel> autores = new ArrayList();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if(fechaPublicacionDesde!=null && (Date.valueOf(element.getAttribute("publicacion")).after(fechaPublicacionDesde) || Date.valueOf(element.getAttribute("publicacion")).equals(fechaPublicacionDesde))) {
                        NodeList autoresN = element.getElementsByTagName("autoresLibro");
                        NodeList nAutores = document.getElementsByTagName("autor");
                        for (int z = 0; z < autoresN.getLength(); z++) {
                            Element autor = (Element) autoresN.item(z);
                            for (int j = 0; j < nAutores.getLength(); j++) {
                                Element autorLibro = (Element) nAutores.item(j);
                                if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                                    autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                                }
                            }
                        }
                        NodeList nodeL = element.getElementsByTagName("generos");
                        GeneroModel generoModel = new GeneroModel();
                        for (int x = 0; x < nodeL.getLength(); x++) {
                            Element generoElement = (Element) nodeL.item(x);
                            if (generoElement.getAttribute("id").equals(element.getAttribute("genero"))) {
                                generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                                generoModel.setGenero(generoElement.getAttribute("nombre"));
                            }
                        }
                        libroModelArrayList.add(new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen")));

                    }
                if (fechaPublicacionHasta!=null & (Date.valueOf(element.getAttribute("publicacion")).after(fechaPublicacionHasta) || Date.valueOf(element.getAttribute("publicacion")).equals(fechaPublicacionHasta))) {
                        NodeList autoresN = element.getElementsByTagName("autoresLibro");
                        NodeList nAutores = document.getElementsByTagName("autor");
                        for (int z = 0; z < autoresN.getLength(); z++) {
                            Element autor = (Element) autoresN.item(z);
                            for (int j = 0; j < nAutores.getLength(); j++) {
                                Element autorLibro = (Element) nAutores.item(j);
                                if (autor.getAttribute("id").equals(autorLibro.getAttribute("id"))) {
                                    autores.add(new AutorModel((Integer.parseInt(autorLibro.getAttribute("id"))), autorLibro.getAttribute("nombre")));
                                }
                            }
                        }
                        NodeList nodeL = element.getElementsByTagName("generos");
                        GeneroModel generoModel = new GeneroModel();
                        for (int x = 0; x < nodeL.getLength(); x++) {
                            Element generoElement = (Element) nodeL.item(x);
                            if (generoElement.getAttribute("id").equals(element.getAttribute("genero"))) {
                                generoModel.setIdGenero(Integer.parseInt(generoElement.getAttribute("id")));
                                generoModel.setGenero(generoElement.getAttribute("nombre"));
                            }
                        }
                        libroModelArrayList.add(new LibroModel(Integer.valueOf(element.getAttribute("id")), element.getAttribute("titulo"), autores, generoModel, Double.valueOf(element.getAttribute("precio")), Date.valueOf(element.getAttribute("publicacion")), Integer.valueOf(element.getAttribute("ejemplares")), Boolean.valueOf(element.getAttribute("stock")), element.getAttribute("imagen")));
                    }
                }
            return libroModelArrayList;
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
