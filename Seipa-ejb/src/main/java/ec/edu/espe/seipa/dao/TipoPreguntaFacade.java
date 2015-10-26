/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.TipoPregunta;
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
public class TipoPreguntaFacade extends AbstractFacade<TipoPregunta> {
    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPreguntaFacade() {
        super(TipoPregunta.class);
    }
    
    public TipoPregunta findByIdTipoPregunta(String idTipoPregunta) {
        try {
            String sql = "SELECT obj FROM TipoPregunta obj WHERE obj.idTipoPreguta=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, idTipoPregunta);
            return (TipoPregunta) qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
}
