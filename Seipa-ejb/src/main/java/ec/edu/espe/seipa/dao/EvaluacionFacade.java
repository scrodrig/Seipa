/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Evaluacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ronny
 */
@Stateless
public class EvaluacionFacade extends AbstractFacade<Evaluacion> {

    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluacionFacade() {
        super(Evaluacion.class);
    }

    public String findID() {
        try {
            String codigoNuevo;
            Query qry = em.createNativeQuery("select max(IDEVALUACION) from BI.EVALUACION");
            if (qry.getSingleResult() == null) {
                codigoNuevo = "0";
            } else {
                codigoNuevo = qry.getSingleResult().toString();
            }
            return (codigoNuevo);
        } catch (NoResultException e) {
            return null;
        }
    }

}
