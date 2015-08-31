/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

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
    

    @PostConstruct
    public void postConstructor() {
        this.usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
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
    
    
}
