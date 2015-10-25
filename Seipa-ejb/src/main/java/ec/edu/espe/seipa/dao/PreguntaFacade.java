/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Evaluacion;
import ec.edu.espe.seipa.model.Pregunta;
import java.util.List;
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
public class PreguntaFacade extends AbstractFacade<Pregunta> {
    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaFacade() {
        super(Pregunta.class);
    }
    
    public List<Pregunta> getPreguntaByEvaluacion(Evaluacion evaluacion) {
        try {
            String sql = "SELECT obj FROM Pregunta obj join Evaluacion ev WHERE ev.idevaluacion=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, evaluacion.getIdevaluacion());
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
