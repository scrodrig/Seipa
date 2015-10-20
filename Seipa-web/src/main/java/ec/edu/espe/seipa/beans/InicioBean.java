/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Usuario;
import ec.edu.espe.seipa.service.AutentificacionServicio;
import ec.edu.espe.seipa.service.DocenteServicio;
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
    @EJB
    private DocenteServicio docenteServicio;
    
    
    private String nombreUsuario;
    private String idUsuario;
    private String nombreDocente;
    private String clave;
    private Docente docente;
    //public String a;
    
    //PerfilBean perfilBean;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

        
    public String validarUsuario(){
        this.nombreUsuario = nombreUsuario;
        System.out.println(nombreUsuario);
        try{
            Usuario usuario = this.autentificacionServicio.usuarioAutentificar(nombreUsuario, clave);
            //System.out.println(usuario.getId());
            if (usuario != null){
                Docente docente = this.docenteServicio.findByIdUsuario(usuario.getId());
                //System.out.println(docente.getNombre().concat(" ").concat(docente.getApellido()));
                if (docente != null){
                    System.out.println(docente.getIdUsuario().getId());
                    if (docente.getIdUsuario().getId().equals(usuario.getId())){
                        //usuario.setFechaUltAcceso(new Date());
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Docente", docente);
                        this.nombreDocente = docente.getNombre().concat(" ").concat(docente.getApellido());
                        this.docente = docente;
                        //a = perfilBean.datosDocente(nombreUsuario);
                        //System.out.println(nombreDocente);
                        //nombreEmpleado = empleado.getNombre();
                        //usuario.setFechaUltAcceso(new Date());
                        //usuarioServicio.actualizar(usuario);
                        //return "seleccionrol";
                    } else {
                        return "home";
                    }
                    return "home";

                } else {
                FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información"));
                return "index";
                }
            }else{
            FacesContext.getCurrentInstance().addMessage(
            null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información"));
            return "index";            
            }
        }catch(Exception e)
        {
                System.out.println(e);
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

    /**
     * @return the nombreDocente
     */
    public String getNombreDocente() {
        return nombreDocente;
    }

    /**
     * @param nombreDocente the nombreDocente to set
     */
    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
