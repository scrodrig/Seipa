/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.seipa.dao;

import ec.edu.espe.seipa.model.Opcion;
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
public class OpcionFacade extends AbstractFacade<Opcion> {

    @PersistenceContext(unitName = "ec.edu.espe.seipa_Seipa-ejb_ejb_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpcionFacade() {
        super(Opcion.class);
    }

    public List<Opcion> getOpcionesByPregunta(Pregunta pregunta) {
        try {
            String sql = "SELECT obj FROM Opcion obj join Pregunta pr WHERE pr.idpregunta=?1";
            Query qry = this.getEntityManager().createQuery(sql);
            qry.setParameter(1, pregunta.getIdpregunta());
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
