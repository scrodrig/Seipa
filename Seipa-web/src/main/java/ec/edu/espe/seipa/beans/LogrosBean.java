/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Archivos;
import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.service.LogroServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class LogrosBean extends BotonesBean implements Serializable {

    @EJB
    private LogroServicio logroServicio;

    private List<Archivos> logros;
    private Archivos logro;
    private Archivos logroSeleccionado;

    private Docente docente;
    private String idDocente;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        // id Variable de Session Docente
        this.setDocente((Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente"));
        this.idDocente = docente.getId();
        this.logros = this.logroServicio.findByDocente(idDocente);
        this.logro = new Archivos();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (logroSeleccionado instanceof Archivos) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        this.logro = new Archivos();
        MensajesGenericos.infoCancelar();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.logro = new Archivos();
    }

    public void modificar(ActionEvent evento) {
        this.setDocente(new Docente());
        try {
            this.logro = (Archivos) BeanUtils.cloneBean(this.logroSeleccionado);
            this.logroServicio.actualizar(this.logro);
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        this.logroServicio.eliminar(this.logroSeleccionado);
        this.logros.remove(this.logroSeleccionado);
        MensajesGenericos.infoEliminar("Registro Académico", this.logro.getNombre().concat(" - Eliminado con éxito "), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void guardar(ActionEvent evento) {
        try {
            super.crear();
            if (super.getEnRegistro()) {
                if (this.logro.getId() == null) {
                    this.logro.setId(new BigDecimal(logroServicio.idLogro()).add(new BigDecimal("1")));
                }
                this.logro.setIddocente(idDocente);
                this.logroServicio.crear(this.logro);
                this.logros.add(this.logro);
                MensajesGenericos.infoCrear("Registro Académico", this.logro.getNombre().concat(" - Creada con éxito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.logros.indexOf(this.logro);
                this.logroServicio.actualizar(this.logro);
                logros.set(i, this.logro);
                MensajesGenericos.infoModificar("Registro Académico", this.logro.getNombre().concat(" - Modificada con éxito "), Boolean.TRUE);
                super.sinSeleccion();
            }

        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    /**
     * @return the logros
     */
    public List<Archivos> getLogros() {
        return logros;
    }

    /**
     * @param logros the logros to set
     */
    public void setLogros(List<Archivos> logros) {
        this.logros = logros;
    }

    /**
     * @return the logro
     */
    public Archivos getLogro() {
        return logro;
    }

    /**
     * @param logro the logro to set
     */
    public void setLogro(Archivos logro) {
        this.logro = logro;
    }

    /**
     * @return the logroSeleccionado
     */
    public Archivos getLogroSeleccionado() {
        return logroSeleccionado;
    }

    /**
     * @param logroSeleccionado the logroSeleccionado to set
     */
    public void setLogroSeleccionado(Archivos logroSeleccionado) {
        this.logroSeleccionado = logroSeleccionado;
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
     * @return the idDocente
     */
    public String getIdDocente() {
        return idDocente;
    }

    /**
     * @param idDocente the idDocente to set
     */
    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

}
