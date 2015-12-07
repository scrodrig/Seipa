/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.service.DocenteServicio;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class CargaImagenBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private File file;
    private Docente docente;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    DocenteServicio docenteServicio;
    

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("Uploaded: {}" + event.getFile().getFileName());

        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void GuardarImagen(FileUploadEvent event) {

        // Para cargar la imagen en la Entidad
        try {
            InputStream fi = new FileInputStream(file);
            // creamos el buffer
            byte[] buffer = new byte[(int) file.length()];
            // Leer al buffer
            int readers = fi.read(buffer);
            this.docente.setImagenperfil(buffer);
            docenteServicio.actualizar(this.docente);
            //setData(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

}
