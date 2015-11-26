/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.DatosformulasFacade;
import ec.edu.espe.seipa.dao.EvaluacionFacade;
import ec.edu.espe.seipa.dao.OpcionFacade;
import ec.edu.espe.seipa.dao.PreguntaFacade;
import ec.edu.espe.seipa.dao.SumarioFacade;
import ec.edu.espe.seipa.dao.SumarioopcionFacade;
import ec.edu.espe.seipa.model.Datosformulas;
import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.model.Pregunta;
import ec.edu.espe.seipa.model.Sumario;
import ec.edu.espe.seipa.model.Sumarioopcion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ronny
 */
@Stateless
@LocalBean
public class EvaluacionServicio {

    @EJB
    private EvaluacionFacade evaluacionFacade;

    @EJB
    private PreguntaFacade preguntaFacade;

    @EJB
    private OpcionFacade opcionFacade;
    
    @EJB
    private DatosformulasFacade datosformulasFacade;
    
    @EJB
    private SumarioopcionFacade sumarioopcionFacade;
    
    @EJB
    private SumarioFacade sumarioFacade;

    public void crear(Evaluacion evaluacion) {
        this.evaluacionFacade.create(evaluacion);
    }
    
    public void crearSumarioopcion(Sumarioopcion sumarioopcion) {
        this.sumarioopcionFacade.create(sumarioopcion);
    }
    
    public void crearSumario(Sumario sumario) {
        this.sumarioFacade.create(sumario);
    }
    
    public void actualizar(Evaluacion evaluacion) {
        this.evaluacionFacade.edit(evaluacion);
    }
    
    public void actualizarSumario(Sumario sumario) {
        this.sumarioFacade.edit(sumario);
    }
    
    public String codigoNuevoEvaluacion(){
        return(this.evaluacionFacade.findID());
    }
    
    public String codigoNuevoSumarioopcion(){
        return(this.sumarioopcionFacade.findID());
    }
    
    public String codigoNuevoSumario(){
        return(this.sumarioFacade.findID());
    }
        
    public Opcion getOpcionFind(String id)
    {
        return(this.opcionFacade.find(id));
    }

    
    public Datosformulas getDatosformulas(String idEstado){
        return(this.datosformulasFacade.findByEstado(idEstado));
    }

    public List<Evaluacion> getEvaluaciones() {
        return this.evaluacionFacade.findAll();
    }

    public List<Pregunta> getPreguntaByEvaluacion(Evaluacion evaluacion) {
        return this.preguntaFacade.getPreguntaByEvaluacion(evaluacion);
    }

    public List<Opcion> getOpcionesByPregunta(Pregunta pregunta) {
        return this.opcionFacade.getOpcionesByPregunta(pregunta);
    }

}
