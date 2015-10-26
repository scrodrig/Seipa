/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.TipoEvaluacion;
import ec.edu.espe.seipa.model.TipoPregunta;
import ec.edu.espe.seipa.service.TipoEvaluacionServicio;
import ec.edu.espe.seipa.service.TipoPreguntaServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ronny
 */
@ManagedBean
@ViewScoped
public class CreacionPreguntasBean extends BotonesBean implements Serializable {

    @EJB
    private TipoEvaluacionServicio tipoEvaluacionServicio;
    @EJB
    private TipoPreguntaServicio tipoPreguntaServicio;

    private String tipoEvaluacion;
    private List<TipoEvaluacion> tipoEvaluaciones;
    
    private String tipoPregunta;
    private List<TipoPregunta> tipoPreguntas;
    
    private Pregunta pregunta;
    private String textoPregunta;
    private String opcionTexto;
    

    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.tipoEvaluaciones= tipoEvaluacionServicio.obtener();
        this.tipoPreguntas = tipoPreguntaServicio.obtener();
    }

    /**
     * @return the tipoEvaluacion
     */
    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    /**
     * @param tipoEvaluacion the tipoEvaluacion to set
     */
    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    /**
     * @return the tipoEvaluaciones
     */
    public List<TipoEvaluacion> getTipoEvaluaciones() {
        return tipoEvaluaciones;
    }

    /**
     * @param tipoEvaluaciones the tipoEvaluaciones to set
     */
    public void setTipoEvaluaciones(List<TipoEvaluacion> tipoEvaluaciones) {
        this.tipoEvaluaciones = tipoEvaluaciones;
    }

    /**
     * @return the tipoPregunta
     */
    public String getTipoPregunta() {
        return tipoPregunta;
    }

    /**
     * @param tipoPregunta the tipoPregunta to set
     */
    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    /**
     * @return the tipoPreguntas
     */
    public List<TipoPregunta> getTipoPreguntas() {
        return tipoPreguntas;
    }

    /**
     * @param tipoPreguntas the tipoPreguntas to set
     */
    public void setTipoPreguntas(List<TipoPregunta> tipoPreguntas) {
        this.tipoPreguntas = tipoPreguntas;
    }

    /**
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the opcionTexto
     */
    public String getOpcionTexto() {
        return opcionTexto;
    }

    /**
     * @param opcionTexto the opcionTexto to set
     */
    public void setOpcionTexto(String opcionTexto) {
        this.opcionTexto = opcionTexto;
    }

    /**
     * @return the textoPregunta
     */
    public String getTextoPregunta() {
        return textoPregunta;
    }

    /**
     * @param textoPregunta the textoPregunta to set
     */
    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }


    /**
     * @return the tipoEvaluacion
     */

}
