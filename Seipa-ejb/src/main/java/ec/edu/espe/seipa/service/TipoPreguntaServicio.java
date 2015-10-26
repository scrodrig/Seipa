/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.TipoPreguntaFacade;
import ec.edu.espe.seipa.model.TipoPregunta;
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
public class TipoPreguntaServicio {
    
    @EJB
    private TipoPreguntaFacade tipoPreguntaFacede;
    
    public List<TipoPregunta> obtener(){
        return this.tipoPreguntaFacede.findAll();
    }
    
    public TipoPregunta findByIdTipoEvaluacion(String idTipoPregunta){
        return this.tipoPreguntaFacede.findByIdTipoPregunta(idTipoPregunta);
    }
    
    /**
     * Función para crear nuevos registros
     *
     * @param tipoPregunta
     */
    public void crear(TipoPregunta tipoPregunta){
        this.tipoPreguntaFacede.create(tipoPregunta);
    }
    /**
     * Función para modificar registros existentes
     *
     * @param tipoPregunta
     */
    
    public void actualizar(TipoPregunta tipoPregunta){
        this.tipoPreguntaFacede.edit(tipoPregunta);
    }
    
     /**
     * Función para eliminar registros
     *
     * @param tipoPregunta
     */
    
    public void eliminar(TipoPregunta tipoPregunta){
        //Docente docenteTmp = this.tipoEvaluacionFacede.findByID(docente.getId());
        //this.tipoEvaluacionFacede.remove(docenteTmp);
    }
    
}
