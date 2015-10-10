/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.EvaluacionFacade;
import ec.edu.espe.seipa.dao.OpcionFacade;
import ec.edu.espe.seipa.dao.PreguntaFacade;
import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Opcion;
import ec.edu.espe.seipa.model.Pregunta;
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
    
    public List<Evaluacion> getEvaluaciones()
    {
        return this.evaluacionFacade.findAll();
    }
    
    public List<Pregunta> getPreguntaByEvaluacion(Evaluacion evaluacion){
        return this.preguntaFacade.getPreguntaByEvaluacion(evaluacion);
    }
   
    public List<Opcion> getOpcionesByPregunta(Pregunta pregunta){
        return this.opcionFacade.getOpcionesByPregunta(pregunta);
    }
    
}
