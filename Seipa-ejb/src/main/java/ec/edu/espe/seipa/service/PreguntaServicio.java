/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.PreguntaFacade;
import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author ronny
 */
@Stateless
@LocalBean
public class PreguntaServicio {

    @EJB
    private PreguntaFacade preguntaFacade;

    public List<Pregunta> obtener() {
        return this.preguntaFacade.findAll();

    }
    
    public List<Pregunta> findByEvaluacion(Evaluacion evaluacion)
    {
        return this.preguntaFacade.getPreguntaByEvaluacion(evaluacion);
    }
            
    
    public Pregunta findByIdEvaluacion(String idEvaluacion){
        
        return this.preguntaFacade.findByIdEvaluacion(idEvaluacion);
    }
    
    public String findOrderNumberPregunta(Evaluacion evaluacion){
        String ordernumber;
        ordernumber = this.preguntaFacade.orderNumbrePregunta(evaluacion);
        return ordernumber;
    }
    
    public void crear(Pregunta pregunta) {
        this.preguntaFacade.create(pregunta);
    }
    
    public void actualizar(Pregunta pregunta){
        this.preguntaFacade.edit(pregunta);
    }
    
    public String codigoNuevoPregunta(){
        return(this.preguntaFacade.findIdPregunta());
    }

}
