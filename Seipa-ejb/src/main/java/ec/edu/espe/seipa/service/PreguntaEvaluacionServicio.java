/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.PreguntaevaluacionFacade;
import ec.edu.espe.seipa.model.Preguntaevaluacion;
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
public class PreguntaEvaluacionServicio {

    @EJB
    private PreguntaevaluacionFacade preguntaEvaluacionFacade;

    public void crear(Preguntaevaluacion preguntaEvaluacion) {
        this.preguntaEvaluacionFacade.create(preguntaEvaluacion);
    }
    
    public String codigoNuevoPreguntaEvaluacion(){
        return(this.preguntaEvaluacionFacade.findIdPreguntaEvaluacion());
    }

}
