package org.dam16.services;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.InputStream;

public class XMLService {
    // Constantes para los nombres de nodos y atributos
    public static final String PROJECT_NAME = "bookook";
    public static final String ROOT_NODE = "inventario";
    public static final String ARCHIVO_XML = "datos.xml";


    /**
     * Carga el archivo XML desde la ruta especificada o lo crea si no existe.
     *
     * @return Document cargado si existe o si se crea correctamente; null si ocurre un error.
     */
    public static Document loadOrCreateXML() {
        // Intentar cargar el documento XML existente
        Document document = loadXML();

        // Si el documento no existe (es null), se crea un nuevo archivo XML
        if (document == null) {
            // Obtener la ruta del archivo XML
            String outputPath = getFilePath(ARCHIVO_XML);
            File outputFile = new File(outputPath);

            // Si el archivo XML se crea correctamente, se vuelve a cargar
            if (createXML(outputFile)) {
                document = loadXML();
            }
        }

        // Retornar el documento cargado o null si no se pudo crear/cargar
        return document;
    }


/**
 * Carga el archivo XML desde la ruta especificada si existe.
 *
 * @return Document cargado si el archivo XML existe y es válido; null si no existe o hay un error.
 */
    private static Document loadXML() {
        try {
            String xmlFilePath = getFilePath(ARCHIVO_XML);
            File xmlFile = new File(xmlFilePath);

            // Comprobar si el archivo existe
            if (!xmlFile.exists()) {
                // Si el archivo no existe, se crea uno nuevo
                if (createXML(xmlFile)) {
                    System.out.println("Archivo XML creado: " + xmlFilePath);
                } else {
                    System.err.println("No se pudo crear el archivo XML: " + xmlFilePath);
                    return null; // Retornar null si no se pudo crear el archivo
                }
            }

            // Crear el DocumentBuilder para cargar el archivo XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            return documentBuilder.parse(xmlFile); // Cargar el archivo desde el sistema de archivos

        } catch (Exception e) {
            System.err.println("Error al cargar XML: " + e.getMessage());
            return null; // Retornar null si ocurre un error
        }
    }


    /**
     * Guarda el documento XML en el archivo especificado.
     *
     * @param document Documento XML que se desea guardar.
     * @return true si el documento se guarda correctamente, false si ocurre un error.
     */
    public static boolean saveXML(Document document) {
        try {
            // Obtener la ruta completa del archivo XML a partir del nombre del archivo.
            String outputPath = getFilePath(ARCHIVO_XML);
            File outputFile = new File(outputPath);

            // Crear el directorio padre si no existe
            outputFile.getParentFile().mkdirs();

            // Crear un transformador para escribir el documento XML en el archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            // Configurar la salida hacia el archivo especificado
            Result output = new StreamResult(outputFile);
            // Configurar la fuente como el documento XML que se va a guardar
            Source input = new DOMSource(document);

            // Transformar y escribir el documento XML en el archivo
            transformer.transform(input, output);

            // Mostrar mensaje de éxito al usuario
            JOptionPane.showMessageDialog(null, "Se guardó en: " + outputPath);
            return true; // Indicar que la operación fue exitosa

        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante el proceso de guardado
            System.err.println("Error al GUARDAR XML: " + e.getMessage());
            return false; // Indicar que ocurrió un error
        }
    }


    /**
     * Método para crear un archivo XML nuevo con un nodo raíz especificado.
     *
     * @param outputFile Archivo donde se guardará el nuevo documento XML.
     * @return true si el archivo XML se creó correctamente, false si ocurrió un error.
     */
// Método para crear un archivo XML nuevo en la ruta especificada
    private static boolean createXML(File outputFile) {
        try {
            // Crear el directorio padre si no existe
            File parentDir = outputFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // Crear todos los directorios intermedios necesarios
            }

            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // Crear un DocumentBuilder a partir de la fábrica
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Crear un nuevo documento XML vacío
            Document newDocument = dBuilder.newDocument();

            // Cargar el archivo base desde el recurso
            InputStream inputStream = XMLService.class.getResourceAsStream("/xmlbase.xml");
            if (inputStream != null) {
                // Importar el contenido del XML base
                Document baseDocument = dBuilder.parse(inputStream);
                Element baseRoot = baseDocument.getDocumentElement(); // Obtener el nodo raíz del documento base

                // Importar el nodo raíz del archivo base al nuevo documento
                Node importedRoot = newDocument.importNode(baseRoot, true);
                newDocument.appendChild(importedRoot);

                System.out.println("Contenido XML importado correctamente desde: xmlbase.xml");
            } else {
                System.err.println("El archivo xmlBase.xml no se encontró en resources.");
                return false; // Detener el proceso si no se encuentra el archivo base
            }

            // Crear un Transformer para escribir el documento XML en el archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Opcional: Configurar salida formateada para legibilidad
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Definir el archivo de salida donde se guardará el documento
            Result output = new StreamResult(outputFile);
            // Definir la fuente del documento que se va a transformar (el nuevo documento)
            Source input = new DOMSource(newDocument);
            // Realizar la transformación y guardar el documento en el archivo
            transformer.transform(input, output);

            return true; // Retornar true si se creó correctamente
        } catch (Exception e) {
            // Manejar excepciones, imprimir un mensaje de error
            System.err.println("Error al crear XML: " + e.getMessage());
            return false; // Retornar false si hubo un error
        }
    }
    /*private static boolean createXML(File outputFile) {
        try {
            // Crear el directorio padre si no existe
            File parentDir = outputFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs(); // Crear todos los directorios intermedios necesarios
            }

            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // Crear un DocumentBuilder a partir de la fábrica
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // Crear un nuevo documento XML
            Document newDocument = dBuilder.newDocument();

            // Crear el nodo principal del documento
            Element rootElement = newDocument.createElement(ROOT_NODE);
            Element booksElement = newDocument.createElement("libros");
            rootElement.appendChild(booksElement);
            // Añadir el nodo principal al documento
            newDocument.appendChild(rootElement);
            InputStream inputStream = XMLService.class.getResourceAsStream("/xmlBase.xml");
            if (inputStream != null) {
                if (importXMLContent(inputStream, newDocument)) {
                    System.out.println("Contenido XML importado correctamente desde: xmlBase.xml");
                } else {
                    System.err.println("Error al importar contenido XML");
                }
            } else {
                System.err.println("El archivo xmlBase.xml no se encontró en resources.");
            }
            // Crear un Transformer para escribir el documento XML en el archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Definir el archivo de salida donde se guardará el documento
            Result output = new StreamResult(outputFile);
            // Definir la fuente del documento que se va a transformar (el nuevo documento)
            Source input = new DOMSource(newDocument);
            // Realizar la transformación y guardar el documento en el archivo
            transformer.transform(input, output);

            return true; // Retornar true si se creó correctamente
        } catch (Exception e) {
            // Manejar excepciones, imprimir un mensaje de error
            System.err.println("Error al crear XML: " + e.getMessage());
            return false; // Retornar false si hubo un error
        }
    }*/


    // Método que devuelve la ruta completa del archivo
    public static String getFilePath(String fileName) {
        return System.getProperty("user.home") + "/" + PROJECT_NAME + "/" + fileName;
    }
    public static boolean importXMLContent(InputStream inputStream, Document targetDocument) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document sourceDocument = dBuilder.parse(inputStream);

            Element rootElement = sourceDocument.getDocumentElement();
            Node importedNode = targetDocument.importNode(rootElement, true);
            targetDocument.getDocumentElement().appendChild(importedNode);

            return true;
        } catch (Exception e) {
            System.err.println("Error al importar contenido XML: " + e.getMessage());
            return false;
        }
    }
}