/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.PreguntaopcionFacade;
import ec.edu.espe.seipa.model.Preguntaopcion;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ronnyestrella
 */

@Stateless
@LocalBean
public class PreguntaOpcionServicio {
    
    @EJB
    PreguntaopcionFacade preguntaOpcionFacade;
    
    
    /**
     * Función para crear nuevos registros
     *
     * @param preguntaOpcion
     */
    public void crear(Preguntaopcion preguntaOpcion){
        this.preguntaOpcionFacade.create(preguntaOpcion);
    }
    /**
     * Función para modificar registros existentes
     *
     * @param preguntaOpcion
     */
    
    public void actualizar(Preguntaopcion preguntaOpcion){
        this.preguntaOpcionFacade.edit(preguntaOpcion);
    }
    
     /**
     * Función para eliminar registros
     *
     * @param preguntaOpcion
     */
    
    public void eliminar(Preguntaopcion preguntaOpcion){
        //Docente docenteTmp = this.docenteFacede.findByID(docente.getId());
        //this.docenteFacede.remove(docenteTmp);
        this.preguntaOpcionFacade.remove(preguntaOpcion);
    }
    
    
    
}
