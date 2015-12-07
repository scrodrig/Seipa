/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author ronny
 */
public class UtilImg {
    
    public static String guardarEnFicheroTemporal(byte[] bytes,String nombreArchivo){
        
        String ubicacionImagen = null;
        ServletContext servletContext = (ServletContext) FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("") + File.separatorChar 
                + "resources" + File.pathSeparatorChar + "img" + File.pathSeparatorChar 
                + "tmp" + File.pathSeparatorChar + nombreArchivo;
        
        File f = null;
        InputStream in = null;
        
        try{
        f = new File(path);
        in = new ByteArrayInputStream(bytes);
        FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
        
        int c = 0;
        
        while ((c = in.read())>= 0){
            out.write(c);
        }
        out.flush();
        out.close();
        ubicacionImagen="/images/tmp" + nombreArchivo;
        }catch(Exception e){
            System.err.println("No se pudo cargar la imagen");
        }
            
        return ubicacionImagen;
    }
    
}
