/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.service.EvaluacionServicio;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import ec.edu.espe.seipa.utils.EvaluacionUtils;
import ec.edu.espe.seipa.utils.MensajesGenericos;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author SchubertDavid
 */
@ManagedBean
@ViewScoped
public class EvaluacionBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of EvaluacionBean
     */
    public EvaluacionBean() {
    }

    @EJB
    private EvaluacionServicio evaluacionServicio;

    @EJB
    private TipoEvaluacionServicio tipoEvaluacionServicio;

    private List<Evaluacion> evaluaciones;
    private Evaluacion evaluacionSeleccionada;

    private List<Pregunta> preguntas;
    private Pregunta preguntaSelecciona;

    private Evaluacion evaluacion;
    private String tEvaluacion;

    private List<TipoEvaluacion> tipoEvaluaciones;
    
    private TipoEvaluacion tipoEvaluacionN;

    @PostConstruct
    public void postConstruct() {
        this.evaluaciones = this.evaluacionServicio.getEvaluaciones();
        this.tipoEvaluaciones = this.tipoEvaluacionServicio.obtener();
    }

    public void seleccionarTipoEvaluacion(Evaluacion evaluacion) {
        this.evaluacionSeleccionada = evaluacion;
        this.preguntas = EvaluacionUtils.getPreguntaListFromEvaluacion(evaluacion);
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (getEvaluacionSeleccionada() instanceof Evaluacion) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.setEvaluacion(new Evaluacion());
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        setEvaluacion(new Evaluacion());
        MensajesGenericos.infoCancelar();
    }

    public void guardar(ActionEvent evento) {
        try {
            this.setTipoEvaluacionN(new TipoEvaluacion());
            this.tipoEvaluacionN.setIdtipoevaluacion(new BigDecimal(gettEvaluacion().substring(57, 59).trim()));
            if(this.evaluacion.getIdtipoevaluacion() == null){
                this.evaluacion.setIdevaluacion(new BigDecimal(evaluacionServicio.codigoNuevoEvaluacion()).add(new BigDecimal("1")));
            }
            java.util.Date fecha = new Date();
            this.evaluacion.setFechaCreacion(fecha);
            this.evaluacion.setIdtipoevaluacion(tipoEvaluacionN);
            if (super.getEnRegistro()) {
                this.evaluacionServicio.crear(this.evaluacion);
                this.evaluaciones.add(this.evaluacion);
                MensajesGenericos.infoCrear("Evaluacion",this.evaluacion.getDescripcion().concat(" - Creada con exito"), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.evaluaciones.indexOf(this.evaluacion);
                this.evaluacionServicio.actualizar(this.evaluacion);
                evaluaciones.set(i, this.evaluacion);
                MensajesGenericos.infoModificar("Evaluacion", this.evaluacion.getDescripcion().concat(" - Modificada con exito "), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.setEvaluacion(new Evaluacion());
        try {
            this.evaluacion = (Evaluacion) BeanUtils.cloneBean(this.evaluacionSeleccionada);
            this.evaluacionServicio.actualizar(this.evaluacion);
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public Evaluacion getEvaluacionSeleccionada() {
        return evaluacionSeleccionada;
    }

    public void setEvaluacionSeleccionada(Evaluacion evaluacionSeleccionada) {
        this.evaluacionSeleccionada = evaluacionSeleccionada;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    /**
     * @return the evaluacion
     */
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the evaluacion to set
     */
    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * @return the preguntaSelecciona
     */
    public Pregunta getPreguntaSelecciona() {
        return preguntaSelecciona;
    }

    /**
     * @param preguntaSelecciona the preguntaSelecciona to set
     */
    public void setPreguntaSelecciona(Pregunta preguntaSelecciona) {
        this.preguntaSelecciona = preguntaSelecciona;
    }


    /**
     * @return the tipoEvaluacionN
     */
    public TipoEvaluacion getTipoEvaluacionN() {
        return tipoEvaluacionN;
    }

    /**
     * @param tipoEvaluacionN the tipoEvaluacionN to set
     */
    public void setTipoEvaluacionN(TipoEvaluacion tipoEvaluacionN) {
        this.tipoEvaluacionN = tipoEvaluacionN;
    }

    /**
     * @return the tEvaluacion
     */
    public String gettEvaluacion() {
        return tEvaluacion;
    }

    /**
     * @param tEvaluacion the tEvaluacion to set
     */
    public void settEvaluacion(String tEvaluacion) {
        this.tEvaluacion = tEvaluacion;
    }



}
