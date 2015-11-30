/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.ArchivosFacade;
import ec.edu.espe.seipa.model.Archivos;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ronny
 */
public class LogroServicio {

    @EJB
    private ArchivosFacade archivosfacade;

    public List<Archivos> obtener() {
        return this.archivosfacade.findAll();
    }
    
    public List<Archivos> findByDocente(String idDocente)
    {
        return this.archivosfacade.findByIdDocente(idDocente);
    }
        

    public void crear(Archivos archivo) {
        this.archivosfacade.create(archivo);
    }

    public void actualizar(Archivos archivo) {
        this.archivosfacade.edit(archivo);
    }

    public void eliminar(Archivos archivo) {
        this.archivosfacade.remove(archivo);
    }

    public String idLogro() {
        return this.archivosfacade.findIDLogro();
    }

}
