/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Usuario;
import ec.edu.espe.seipa.service.AutentificacionServicio;
import ec.edu.espe.seipa.service.UsuarioService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SchubertDavid
 */
@ManagedBean
@SessionScoped
public class InicioBean implements Serializable{

    /**
     * Creates a new instance of InicioBean
     */
    
    @EJB
    private AutentificacionServicio autentificacionServicio;
    @EJB
    private UsuarioService usuarioServicio;
    
    private String nombreUsuario;
    private String clave;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    
    
    
     public String validarUsuario() {
        this.nombreUsuario = nombreUsuario.toLowerCase();
         System.out.println(nombreUsuario);
        Usuario usuario = this.autentificacionServicio.usuarioAutentificar(nombreUsuario, clave);
        if (usuario != null) {
            //Empleado empleado = this.empleadoServicio.findByID(usuario.getCodigo());
            //if (empleado != null) {
                //if (empleado.getCodigo().equals(usuario.getCodigo())) {
                    //usuario.setFechaUltAcceso(new Date());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                    //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Empleado", empleado);
                    //nombreEmpleado = empleado.getNombre();
                    //usuario.setFechaUltAcceso(new Date());
                    //usuarioServicio.actualizar(usuario);
                    //return "seleccionrol";
                //} else {
                    return "home";
                //}
               //return "inicio";
            
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información"));
            return "index";
        }
    }
    
    public InicioBean() {
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
