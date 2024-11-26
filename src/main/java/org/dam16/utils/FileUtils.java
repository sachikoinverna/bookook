package org.dam16.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.dam16.services.XMLService.PROJECT_NAME;

public class FileUtils {

    public static String seleccionarRutaImagen() {
        // Crea una instancia de JFileChooser para permitir la selección de archivos.
        JFileChooser fileChooser = new JFileChooser();

        // Establece el título del diálogo de selección de archivos.
        fileChooser.setDialogTitle("Seleccionar una imagen");

        // Filtro para solo mostrar archivos de imagen en el diálogo.
        // Se permite la selección de imágenes con extensiones específicas: jpg, jpeg, png, gif, bmp.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);

        // Muestra el diálogo para seleccionar un archivo y espera la acción del usuario.
        int userSelection = fileChooser.showOpenDialog(null);

        // Verifica si el usuario aprobó la selección de un archivo.
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtiene el archivo seleccionado por el usuario.
            File archivoSeleccionado = fileChooser.getSelectedFile();

            // Obtiene la ruta absoluta del archivo seleccionado.
            String rutaImagen = archivoSeleccionado.getAbsolutePath();

            // Retorna la ruta de la imagen seleccionada.
            return rutaImagen;
        }

        // Si no se seleccionó un archivo, retorna null.
        return null;
    }
    public static BufferedImage reescalarImagen(File archivoImagen, int ancho, int alto) throws IOException {
        BufferedImage imagenOriginal = ImageIO.read(archivoImagen);
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        // Crear una nueva imagen con el tamaño especificado
        BufferedImage imagenReescalada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        // Dibujar la imagen escalada en el nuevo BufferedImage
        Graphics2D g2d = imagenReescalada.createGraphics();
        g2d.drawImage(imagenEscalada, 0, 0, null);
        g2d.dispose(); // Liberar recursos

        return imagenReescalada;
    }
    // Método estático para abrir el selector de archivos y guardar la imagen reescalada
    public static String guardarImagen(String rutaImagen) {
        if (rutaImagen.equals("default")){
            return rutaImagen;
        }
        String path = System.getProperty("user.home") + "\\" + PROJECT_NAME + "\\images";
        File carpetaDestino = new File(path);
        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();  // Crea la carpeta si no existe
        }

        // Crea el archivo de destino con el mismo nombre que el original
        File imagenOrigen = new File(rutaImagen);
        File imagenDestino = new File(carpetaDestino, imagenOrigen.getName());

        try {
            // Reescalar la imagen a 200x200
            BufferedImage imagenReescalada = reescalarImagen(imagenOrigen, 200, 200);

            // Guardar la imagen reescalada en la carpeta destino
            ImageIO.write(imagenReescalada, "png", imagenDestino);

            JOptionPane.showMessageDialog(null, "Imagen reescalada y copiada a: " + imagenDestino.getAbsolutePath());
            return imagenDestino.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al copiar la imagen: " + e.getMessage());
        }

        return null;
    }
}
