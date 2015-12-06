/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Horasdocente;
import ec.edu.espe.seipa.service.HorasServicio;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class HorasBean extends BotonesBean implements Serializable {

    @EJB
    private HorasServicio horasServicio;

    private List<Horasdocente> horas;
    private Horasdocente horaDocente;
    private Horasdocente horaDocenteSeleccionado;

    private Docente docente;
    private String idDocente;

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        // id Variable de Session Docente
        this.setDocente((Docente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Docente"));
        //this.idDocente = docente.getId();
        this.horas = this.horasServicio.horasByDocente(docente);

    }

    public void filaSeleccionada(ActionEvent evento) {
        if (horaDocenteSeleccionado instanceof Horasdocente) {
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
        this.horaDocente = new Horasdocente();
        MensajesGenericos.infoCancelar();
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.horaDocente = new Horasdocente();
    }

    public void modificar(ActionEvent evento) {
        this.setDocente(new Docente());
        try {
            this.horaDocente = (Horasdocente) BeanUtils.cloneBean(this.horaDocenteSeleccionado);
            this.horasServicio.actualizar(this.horaDocente);
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        this.horasServicio.eliminar(this.horaDocenteSeleccionado);
        this.horas.remove(this.horaDocenteSeleccionado);
        MensajesGenericos.infoEliminar("Horas", this.horaDocente.getTipohoras().concat(" - Eliminado con éxito "), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void guardar(ActionEvent evento) {
        try {

            if (this.horaDocente == null) {
                this.horaDocente.setIdhorasdocente(new BigDecimal(horasServicio.idHorasDocente()).add(new BigDecimal("1")));
            }
            this.horaDocente.setIddocente(docente);
            if (super.getEnRegistro()) {
                this.horasServicio.crear(this.horaDocente);
                this.horas.add(this.horaDocente);
                MensajesGenericos.infoCrear("Horas Docente", this.horaDocente.getTipohoras().concat(" - Creada con éxito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.horas.indexOf(this.horaDocente);
                this.horasServicio.actualizar(this.horaDocente);
                horas.set(i, this.horaDocente);
                MensajesGenericos.infoModificar("Horas Docente", this.horaDocente.getTipohoras().concat(" - Modificada con éxito "), Boolean.TRUE);
                super.sinSeleccion();
            }

        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    /**
     * @return the horas
     */
    public List<Horasdocente> getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(List<Horasdocente> horas) {
        this.horas = horas;
    }

    /**
     * @return the horaDocente
     */
    public Horasdocente getHoraDocente() {
        return horaDocente;
    }

    /**
     * @param horaDocente the horaDocente to set
     */
    public void setHoraDocente(Horasdocente horaDocente) {
        this.horaDocente = horaDocente;
    }

    /**
     * @return the horaDocenteSeleccionado
     */
    public Horasdocente getHoraDocenteSeleccionado() {
        return horaDocenteSeleccionado;
    }

    /**
     * @param horaDocenteSeleccionado the horaDocenteSeleccionado to set
     */
    public void setHoraDocenteSeleccionado(Horasdocente horaDocenteSeleccionado) {
        this.horaDocenteSeleccionado = horaDocenteSeleccionado;
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
