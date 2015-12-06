/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.HorasdocenteFacade;
import ec.edu.espe.seipa.model.Docente;
import ec.edu.espe.seipa.model.Horasdocente;
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
public class HorasServicio {
 
    @EJB
    private HorasdocenteFacade horasDocenteFacade;
    
    public List<Horasdocente> obtener() {
        return this.horasDocenteFacade.findAll();
    }
    
    public List<Horasdocente> horasByDocente(Docente docente)
    {
        return this.horasDocenteFacade.HorasbyDocente(docente);
    }
        

    public void crear(Horasdocente archivo) {
        this.horasDocenteFacade.create(archivo);
    }

    public void actualizar(Horasdocente archivo) {
        this.horasDocenteFacade.edit(archivo);
    }

    public void eliminar(Horasdocente archivo) {
        this.horasDocenteFacade.remove(archivo);
    }

    public String idHorasDocente() {
        return this.horasDocenteFacade.findIdHorasDocente();
    }
    
}
