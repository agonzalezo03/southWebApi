/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.southweb.models;

import es.southweb.beans.Usuario;
import es.southweb.rest.SouthWebRestConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author AntoManueh
 */
public class ComprobarFormulario {
    private static String projectDirectory = System.getProperty("user.dir");

    private static final String UPLOAD_DIR = "C:/Users/AntoManueh/Documents/GitHub/south-web/server/southWeb/src/main/webapp/img/";

     public static boolean CamposRellenos(Usuario user) {
        boolean relleno = true;
        Enumeration<String> nombresParametros = (Enumeration<String>) user;

        while (nombresParametros.hasMoreElements()) {
            String nombreParametro = nombresParametros.nextElement();
        System.out.println(nombreParametro);
        

        }
         return relleno;
}
     
     public static boolean saveToFile(InputStream uploadedInputStream, String fileName) {
         System.out.println(UPLOAD_DIR);
        try {
            File file = new File(UPLOAD_DIR + fileName);
            System.out.println("es.apisouthweb.controllers.Registro.saveToFile()");
            try (OutputStream out = new FileOutputStream(file)) {
                int read;
                byte[] bytes = new byte[1024];
                while ((read = uploadedInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     
}
