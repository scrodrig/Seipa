/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.service;

import ec.edu.espe.seipa.dao.OpcionFacade;
import ec.edu.espe.seipa.model.Opcion;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ronny
 */
@Stateless
@LocalBean
public class OpcionServicio {

    @EJB
    private OpcionFacade opcionFacede;

    public void crear(Opcion opcion) {
        this.opcionFacede.create(opcion);
    }

    public void actualizar(Opcion opcion) {
        this.opcionFacede.edit(opcion);
    }

}
