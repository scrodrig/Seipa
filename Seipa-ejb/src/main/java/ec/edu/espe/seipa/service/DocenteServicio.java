/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.DocenteFacade;
import ec.edu.espe.seipa.model.Docente;
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
public class DocenteServicio {
    
    @EJB
    private DocenteFacade docenteFacede;
    
    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Docente> obtener(){
        return this.docenteFacede.findAll();
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param cedula
     * @return
     */
    public Docente findByIdUsuario(String idUsuario){
        return this.docenteFacede.findByIdUsuario(idUsuario);
    }
    
    /**
     * Función para crear nuevos registros
     *
     * @param docente
     */
    public void crear(Docente docente){
        this.docenteFacede.create(docente);
    }
    /**
     * Función para modificar registros existentes
     *
     * @param docente
     */
    
    public void actualizar(Docente docente){
        this.docenteFacede.edit(docente);
    }
    
     /**
     * Función para eliminar registros
     *
     * @param docente
     */
    
    public void eliminar(Docente docente){
        //Docente docenteTmp = this.docenteFacede.findByID(docente.getId());
        //this.docenteFacede.remove(docenteTmp);
    }
}
