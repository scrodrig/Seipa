/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.TipoEvaluacionFacade;
import ec.edu.espe.seipa.model.TipoEvaluacion;
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
public class TipoEvaluacionServicio {
    
    @EJB
    private TipoEvaluacionFacade tipoEvaluacionFacede;
    
    public List<TipoEvaluacion> obtener(){
        return this.tipoEvaluacionFacede.findAll();
    }
    
    public TipoEvaluacion findByIdTipoEvaluacion(String idTipoEvaluacion){
        return this.tipoEvaluacionFacede.findByIdTipoEvaluacion(idTipoEvaluacion);
    }
    
    /**
     * Función para crear nuevos registros
     *
     * @param tipoEvaluacion
     */
    public void crear(TipoEvaluacion tipoEvaluacion){
        this.tipoEvaluacionFacede.create(tipoEvaluacion);
    }
    /**
     * Función para modificar registros existentes
     *
     * @param tipoEvaluacion
     */
    
    public void actualizar(TipoEvaluacion tipoEvaluacion){
        this.tipoEvaluacionFacede.edit(tipoEvaluacion);
    }
    
     /**
     * Función para eliminar registros
     *
     * @param tipoEvaluacion
     */
    
    public void eliminar(TipoEvaluacion tipoEvaluacion){
        //Docente docenteTmp = this.tipoEvaluacionFacede.findByID(docente.getId());
        //this.tipoEvaluacionFacede.remove(docenteTmp);
    }
}
