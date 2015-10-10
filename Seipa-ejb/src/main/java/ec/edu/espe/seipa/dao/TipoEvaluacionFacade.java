/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.TipoEvaluacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ronny
 */
@Stateless
public class TipoEvaluacionFacade extends AbstractFacade<TipoEvaluacion> {
    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEvaluacionFacade() {
        super(TipoEvaluacion.class);
    }
    
}
