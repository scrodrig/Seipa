/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SchubertDavid
 */
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

    
     private Usuario usuario;
     private Docente docente;
    

    @PostConstruct
    public void postConstructor() {
        //this.usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.setDocente((Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente"));
    }
    
    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
