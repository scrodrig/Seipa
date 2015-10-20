/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.service.DocenteServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class PerfilBean implements Serializable {

    @EJB
    private DocenteServicio docenteServicio;
    
    private String usuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String cedula;
    private String direccion;
    
    private Docente docente;

    
    @PostConstruct
    public void postConstructor() {
        //this.usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.setDocente((Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente"));
        datosDocente();
    }
    
    public void datosDocente() {

  
        if (docente != null) {
            this.usuario = docente.getIdUsuario().getUsuario();
            this.nombre = docente.getNombre();
            this.apellido = docente.getApellido();
            this.cedula = docente.getId();
            this.telefono = docente.getTelefono();
            this.direccion = docente.getDireccion();
            this.correo = docente.getCorreo();

        } else {
            
            usuario="S/N";
            nombre="S/N";
            apellido="S/N";
            cedula="S/N";
            telefono="S/N";
            direccion="S/N";
            correo="S/N";
                    

        }
        
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }


}
