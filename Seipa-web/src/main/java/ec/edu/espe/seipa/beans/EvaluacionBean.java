/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.beans;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.service.EvaluacionServicio;
import ec.edu.espe.seipa.utils.EvaluacionUtils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author SchubertDavid
 */
@ManagedBean
@RequestScoped
public class EvaluacionBean {

    /**
     * Creates a new instance of EvaluacionBean
     */
    public EvaluacionBean() {
    }
    
     @EJB
    private EvaluacionServicio evaluacionServicio;
     
     private List<Evaluacion> evaluaciones;
     private Evaluacion evaluacionSeleccionada;
     private List<Pregunta> preguntas;

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
     
     @PostConstruct
     public void postConstruct(){
         this.evaluaciones=this.evaluacionServicio.getEvaluaciones();
     }
     
     public void seleccionarTipoEvaluacion(Evaluacion evaluacion){
         this.evaluacionSeleccionada=evaluacion;
         this.preguntas=EvaluacionUtils.getPreguntaListFromEvaluacion(evaluacion);
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
     
     
}
