/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.service.UsuarioService;
import ec.edu.espe.seipa.service.DocenteServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class DocentesBean extends BotonesBean implements Serializable {
    
    @EJB
    private DocenteServicio docenteServicio;
    
    private List<Docente> docentes;
    private Docente docente;
    private Docente docenteSeleccionado;
    private Docente respaldo;
    
    @PostConstruct
    @Override
    public void postConstructor(){

        super.sinSeleccion();
        this.setDocentes(this.getDocenteServicio().obtener());  
    }
    
    public void nuevo(ActionEvent evento) {
        super.crear();
        this.setDocente(new Docente());
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        setDocente(new Docente());
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        setDocente(new Docente());
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.setDocente(new Docente());
            this.setDocente((Docente) BeanUtils.cloneBean(this.getDocenteSeleccionado()));
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getNombreEstado(String estado)
    {
        if(estado.equals("A") || estado.equals("a"))
            return "Activo";
        else
            return "Inactivo";
    }
    
//    public String getUsrAuditoria(String usr) {
//        if (usr == null || "".equals(usr)) {
//            return "";
//        } else {
//            try {
//                 usuarioServicio.findByID(usr);
//                 return usuarioServicio.findByID(usr).getUsuario();
//            } catch (NullPointerException e) {
//                return "";
//            }
//        }
//    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                //this.docente.setId(BigDecimal.ONE);
                this.docenteServicio.crear(this.docente);
                this.docentes.add(this.docente);
                MensajesGenericos.infoCrear("Docente", this.docente.getId().toString().concat(" - ").concat(this.docente.getNombre()).concat(" ").concat(this.docente.getApellido()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.docentes.indexOf(this.docente);
                this.docenteServicio.actualizar(this.docente);
                docentes.set(i, this.docente);
                MensajesGenericos.infoModificar("Usuario", this.docente.getId().toString().concat(" - ").concat(this.docente.getNombre()).concat(" ").concat(this.docente.getApellido()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.setDocente(new Docente());
        try {
//            this.docente = (Docente) BeanUtils.cloneBean(this.docenteSeleccionado);
//            //Invariable Objetos de Auditoria            
//            this.docente.setUsrModificacion(usrSesion.getCodigo());
//            this.docente.setFmodificacion(new Date());
//            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        //this.docenteServicio.eliminar(this.docenteSeleccionado);
//        this.docenteSeleccionado.setEstado("I");
//        this.docenteServicio.actualizar(docenteSeleccionado);
//        //this.docentes.remove(this.docenteSeleccionado);
//        MensajesGenericos.infoEliminar("Docente", this.docente.getCodigo().toString().concat(" - ").concat(this.docente.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (getDocenteSeleccionado() instanceof Docente) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    /**
     * @return the docenteServicio
     */
    public DocenteServicio getDocenteServicio() {
        return docenteServicio;
    }

    /**
     * @param docenteServicio the docenteServicio to set
     */
    public void setDocenteServicio(DocenteServicio docenteServicio) {
        this.docenteServicio = docenteServicio;
    }

    /**
     * @return the docentes
     */
    public List<Docente> getDocentes() {
        return docentes;
    }

    /**
     * @param docentes the docentes to set
     */
    public void setDocentes(List<Docente> docentes) {
        this.docentes = docentes;
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

    /**
     * @return the docenteSeleccionado
     */
    public Docente getDocenteSeleccionado() {
        return docenteSeleccionado;
    }

    /**
     * @param docenteSeleccionado the docenteSeleccionado to set
     */
    public void setDocenteSeleccionado(Docente docenteSeleccionado) {
        this.docenteSeleccionado = docenteSeleccionado;
    }

    /**
     * @return the respaldo
     */
    public Docente getRespaldo() {
        return respaldo;
    }

    /**
     * @param respaldo the respaldo to set
     */
    public void setRespaldo(Docente respaldo) {
        this.respaldo = respaldo;
    }
    
}
